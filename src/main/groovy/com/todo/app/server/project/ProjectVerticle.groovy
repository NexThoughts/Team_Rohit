package com.todo.app.server.project

import com.todo.app.model.Label
import com.todo.app.model.Project
import com.todo.app.model.Task
import com.todo.app.model.User
import com.todo.app.util.BaseUtil
import io.vertx.core.AbstractVerticle
import io.vertx.core.http.HttpHeaders
import io.vertx.core.json.JsonArray
import io.vertx.core.json.JsonObject
import io.vertx.ext.web.Router
import io.vertx.ext.web.RoutingContext
import io.vertx.ext.web.Session
import io.vertx.ext.web.templ.FreeMarkerTemplateEngine

class ProjectVerticle extends AbstractVerticle {
    FreeMarkerTemplateEngine engine = null
    def mongoClient = BaseUtil.mongoClient
    public static DEFAULT_COLLECTION = 'project'

    void start() {
        engine = FreeMarkerTemplateEngine.create()
        Router router = BaseUtil.router
//        router.route().handler(BodyHandler.create())
        router.get("/project/list").handler(this.&list)
        router.get("/project/add").handler(this.&add)
        router.post("/project/save").handler(this.&save)
        router.get("/project/edit").handler(this.&edit)
        router.get("/project/edit/:projectId").handler(this.&edit)
        router.get("/project/manage/:projectId").handler(this.&manage)
        router.get("/project/delete/:projectId").handler(this.&delete)
//        router.get("/project/delete").handler(this.&delete)
//        router.post("/project/update").handler(this.&update)
        vertx.createHttpServer().requestHandler(router.&accept).listen(8085)

    }


    void list(RoutingContext ctx) {

        if(!BaseUtil.isSession){
            println "====URL SECURED============="
            ctx.response().putHeader("location", "/").setStatusCode(302).end();
        }


        ctx.put("title", "Project")
        ctx.put("name", "Project List")
        JsonArray array = new JsonArray()
        List<Project> projectList = []
        JsonObject query = new JsonObject()
        mongoClient.find(DEFAULT_COLLECTION, query, { response ->
            if (response.succeeded()) {
                for (JsonObject project : response.result()) {
                    projectList.add(new Project(project))
                }
                ctx.put("projectList", projectList)
                engine.render(ctx, "templates/project/list", { res ->
                    if (res.succeeded()) {
                        ctx.response().putHeader(HttpHeaders.CONTENT_TYPE, "text/html").end(res.result())
                    } else {
                        ctx.fail(res.cause())
                    }
                })
            }
        })

    }

    void add(RoutingContext ctx) {


        if(!BaseUtil.isSession){
            println "====URL SECURED============="
            ctx.response().putHeader("location", "/").setStatusCode(302).end();
        }

        JsonObject query = new JsonObject()
        mongoClient.find(DEFAULT_COLLECTION, query, { response ->
            if (response.succeeded()) {
                engine.render(ctx, "templates/project/add", { res ->
                    if (res.succeeded()) {
                        ctx.response().putHeader(HttpHeaders.CONTENT_TYPE, "text/html").end(res.result())
                    } else {
                        ctx.fail(res.cause())
                    }
                })
            }
        })


    }

    void save(RoutingContext ctx) {

        if(!BaseUtil.isSession){
            println "====URL SECURED============="
            ctx.response().putHeader("location", "/").setStatusCode(302).end();
        }



        println "111111111111111111111111111"
        println "========Going to save project=============" + ctx.request().getFormAttribute("name")
        JsonObject project = new JsonObject().put("name", ctx.request().getFormAttribute("name"))
        project.put("uuid", UUID.randomUUID().toString())
        mongoClient.save(DEFAULT_COLLECTION, project, { res ->
            if (res.succeeded()) {
                String id = res.result();
                System.out.println("Saved Project With Id" + id);
                ctx.response().putHeader("location", "/project/list").setStatusCode(302).end();
            } else {
                res.cause().printStackTrace();
            }
        });

    }

    void edit(RoutingContext ctx) {
        List<User> users = []

        if(!BaseUtil.isSession){
            println "====URL SECURED============="
            ctx.response().putHeader("location", "/").setStatusCode(302).end();
        }
        println "==========Editing the Project===================="
        println "==========Editing the Project====================" + ctx.request().getParam("projectId")
        String projectId = ctx.request().getParam("projectId")
        JsonObject query = new JsonObject().put("_id", projectId)
        Project project = null
        mongoClient.find(DEFAULT_COLLECTION, query, { res ->
            if (res.succeeded()) {
                for (JsonObject json : res.result()) {
                    println "json  " + json
                    project = new Project(json)
                    ctx.put("projectName", project?.name)
                    ctx.put("projectId", project?.id)
                }
                mongoClient.find(BaseUtil.USER_COLLECTION, query, { uResp ->
                    if (uResp.succeeded()) {
                        for (JsonObject user : uResp.result()) {
                            users.add(new User(user))
                        }
                        ctx.put("users", users)
                    }

                    println("########################" + users*.id)
                    engine.render(ctx, "templates/project/edit", { response ->
                        if (response.succeeded()) {
                            ctx.response().putHeader(HttpHeaders.CONTENT_TYPE, "text/html").end(response.result())
                        } else {
                            ctx.fail(response.cause())
                        }
                    })
                })

            } else {
                res.cause().printStackTrace();

            }

        })


    }

    void update(RoutingContext ctx) {
        if(!BaseUtil.isSession){
            println "====URL SECURED============="
            ctx.response().putHeader("location", "/").setStatusCode(302).end();
        }



        println("" + ctx.request().getFormAttribute("projectId"))

        JsonObject query = new JsonObject().put("_id", ctx.request().getFormAttribute("projectId"));
        def update = [
                $set: [
                        name: ctx.request().getFormAttribute("name")
                ]
        ]
        mongoClient.update(BaseUtil.PROJECT_COLLECTION, query, update, { res ->
            if (res.succeeded()) {
                System.out.println("Book updated !");
            } else {
                res.cause().printStackTrace();
            }
        })
        String projectId = ctx.request().getFormAttribute("projectId")
        println "==========Editing the Project===================="
        println "==========Editing the Project====================" + ctx.request().getParam("projectId")
        mongoClient.find(DEFAULT_COLLECTION, query, { res ->
            if (res.succeeded()) {
                for (JsonObject json : res.result()) {
                    println "json  " + json
                    Project project = new Project(json)
                    ctx.put("project", project)
                }


            } else {

                res.cause().printStackTrace();

            }

        })

        engine.render(ctx, "templates/project/list", { res ->
            if (res.succeeded()) {
                ctx.response().putHeader(HttpHeaders.CONTENT_TYPE, "text/html").end(res.result())
            } else {
                ctx.fail(res.cause())
            }
        })

    }


    void delete(RoutingContext ctx) {


        if(!BaseUtil.isSession){
            println "====URL SECURED============="
            ctx.response().putHeader("location", "/").setStatusCode(302).end();
        }

        println "==========Deleting the Project===================="
        String projectId = ctx.request().getParam("projectId")
        println "projectId   " + projectId
        println "projectId   " + projectId
        JsonObject query = new JsonObject().put("_id", projectId)
        mongoClient.remove(DEFAULT_COLLECTION, query, { res ->
            if (res.succeeded()) {
                System.out.println(" deleted !!!!");
                ctx.response().putHeader("location", "/project/list").setStatusCode(302).end();
            } else {

                res.cause().printStackTrace();
            }

        });
    }


    void manage(RoutingContext ctx) {




        if(!BaseUtil.isSession){
            println "====URL SECURED============="
            ctx.response().putHeader("location", "/").setStatusCode(302).end();
        }
//        Session session = ctx.session()
//        User user = session.get("user")
        String projectId = ctx.request().getParam("projectId")
        ctx.put("title", "Label")
        ctx.put("name", "Label List")
        JsonArray array = new JsonArray()
        println "projectId  " + projectId
        println "projectId  " + projectId
        println "projectId  " + projectId
        List<Task> taskList = []
        JsonObject query = new JsonObject().put("_id", projectId)
        Project project = null
        mongoClient.find(DEFAULT_COLLECTION, query, { res ->
            if (res.succeeded()) {
                for (JsonObject json : res.result()) {
                    println "json  " + json
                    project = new Project(json)
                    ctx.put("project", project)
                }
                query = new JsonObject().put("_id", projectId)
                mongoClient.find("task", query, { response ->
                    if (response.succeeded()) {
                        for (JsonObject task : response.result()) {
                            taskList.add(new Task(task))
                        }
                        ctx.put("taskList", taskList)
                        engine.render(ctx, "templates/project/manage", { engRes ->
                            if (engRes.succeeded()) {
                                ctx.response().putHeader(HttpHeaders.CONTENT_TYPE, "text/html").end(engRes.result())
                            } else {
                                ctx.fail(engRes.cause())
                            }
                        })
                    }
                })

            } else {
                res.cause().printStackTrace();

            }

        })

    }

    void createTask(RoutingContext ctx) {




        if(!BaseUtil.isSession){
            println "====URL SECURED============="
            ctx.response().putHeader("location", "/").setStatusCode(302).end();
        }
        println "111111111111111111111111111"
        println "========Going to save task=============" + ctx.request().getFormAttribute("name")
        println "========Going to save projectId=============" + ctx.request().getFormAttribute("projectId")
        JsonObject task = new JsonObject().put("name", ctx.request().getFormAttribute("name"))
        task.put("uuid", UUID.randomUUID().toString())
        mongoClient.save(DEFAULT_COLLECTION, task, { res ->
            if (res.succeeded()) {
                String id = res.result();
                System.out.println("Saved Task With Id" + id);
                ctx.response().putHeader("location", "/task/list").setStatusCode(302).end();
            } else {
                res.cause().printStackTrace();
            }
        });

    }
}