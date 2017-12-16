package com.todo.app.server.task

import com.todo.app.model.Label
import com.todo.app.model.Project
import com.todo.app.model.Task
import com.todo.app.util.BaseUtil
import io.vertx.core.AbstractVerticle
import io.vertx.core.http.HttpHeaders
import io.vertx.core.json.JsonArray
import io.vertx.core.json.JsonObject
import io.vertx.ext.web.Router
import io.vertx.ext.web.RoutingContext
import io.vertx.ext.web.templ.FreeMarkerTemplateEngine


class TaskVerticle extends AbstractVerticle {
    FreeMarkerTemplateEngine engine = null
    def mongoClient = BaseUtil.mongoClient
    public static DEFAULT_COLLECTION = 'task'

    void start() {
        engine = FreeMarkerTemplateEngine.create()
        Router router = BaseUtil.router
//        router.route().handler(BodyHandler.create())
        router.get("/task/list").handler(this.&list)
        router.get("/task/add").handler(this.&add)
        router.post("/task/save").handler(this.&save)
        router.get("/task/edit").handler(this.&edit)
        router.get("/task/edit/:taskId").handler(this.&edit)
        router.get("/task/manage/:taskId").handler(this.&manage)
        router.get("/task/delete/:taskId").handler(this.&delete)
//        router.get("/task/delete").handler(this.&delete)
//        router.post("/task/update").handler(this.&update)
        vertx.createHttpServer().requestHandler(router.&accept).listen(8085)

    }


    void list(RoutingContext ctx) {
        ctx.put("title", "Task")
        ctx.put("name", "Task List")
        JsonArray array = new JsonArray()
        List<Task> taskList = []
        JsonObject query = new JsonObject()
        mongoClient.find(DEFAULT_COLLECTION, query, { response ->
            if (response.succeeded()) {
                for (JsonObject task : response.result()) {
                    taskList.add(new Task(task))
                }
                ctx.put("taskList", taskList)
                engine.render(ctx, "templates/task/list", { res ->
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
        JsonObject query = new JsonObject()
        mongoClient.find(DEFAULT_COLLECTION, query, { response ->
            if (response.succeeded()) {
                engine.render(ctx, "templates/task/add", { res ->
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
        println "111111111111111111111111111"
        println "========Going to save task=============" + ctx.request().getFormAttribute("name")
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

    void edit(RoutingContext ctx) {


        println "==========Editing the Task===================="
        println "==========Editing the Task====================" + ctx.request().getParam("taskId")
        String taskId = ctx.request().getParam("taskId")
        JsonObject query = new JsonObject().put("_id", taskId)
        Task task = null
        mongoClient.find(DEFAULT_COLLECTION, query, { res ->
            if (res.succeeded()) {
                for (JsonObject json : res.result()) {
                    println "json  " + json
                    task = new Task(json)
                    ctx.put("taskName", task?.name)
                    ctx.put("taskId", task?.id)
                }

            } else {
                res.cause().printStackTrace();

            }

        })

        engine.render(ctx, "templates/task/edit", { res ->
            if (res.succeeded()) {
                ctx.response().putHeader(HttpHeaders.CONTENT_TYPE, "text/html").end(res.result())
            } else {
                ctx.fail(res.cause())
            }
        })

    }

    void update(RoutingContext ctx) {
        println "==========Editing the Task===================="
        println "==========Editing the Task====================" + ctx.request().getParam("taskId")
        String taskId = ctx.request().getParam("taskId")
        JsonObject query = new JsonObject().put("_id", taskId)
        mongoClient.find(DEFAULT_COLLECTION, query, { res ->
            if (res.succeeded()) {
                for (JsonObject json : res.result()) {
                    println "json  " + json
                    Task task = new Task(json)
                    ctx.put("task", task)
                }


            } else {

                res.cause().printStackTrace();

            }

        })

        engine.render(ctx, "templates/task/edit", { res ->
            if (res.succeeded()) {
                ctx.response().putHeader(HttpHeaders.CONTENT_TYPE, "text/html").end(res.result())
            } else {
                ctx.fail(res.cause())
            }
        })

    }


    void delete(RoutingContext ctx) {
        println "==========Deleting the Task===================="
        String taskId = ctx.request().getParam("taskId")
        println "taskId   " + taskId
        println "taskId   " + taskId
        JsonObject query = new JsonObject().put("_id", taskId)
        mongoClient.remove(DEFAULT_COLLECTION, query, { res ->
            if (res.succeeded()) {
                System.out.println(" deleted !!!!");
                ctx.response().putHeader("location", "/task/list").setStatusCode(302).end();
            } else {

                res.cause().printStackTrace();
            }

        });
    }


    void manage(RoutingContext ctx) {
        String taskId = ctx.request().getParam("taskId")
        ctx.put("title", "Label")
        ctx.put("name", "Label List")
        JsonArray array = new JsonArray()
        println "taskId  " + taskId
        println "taskId  " + taskId
        println "taskId  " + taskId
        List<Label> labelList = []
        JsonObject query = new JsonObject().put("_id", taskId)
        Task task = null
        mongoClient.find(DEFAULT_COLLECTION, query, { res ->
            if (res.succeeded()) {
                for (JsonObject json : res.result()) {
                    println "json  " + json
                    task = new Task(json)
                    ctx.put("task", task)
                }

                println "task name   " + task?.name
                println "task id   " + task?.id
                query = new JsonObject().put("_id", taskId)
                mongoClient.find("label", query, { response ->
                    if (response.succeeded()) {
                        for (JsonObject label : response.result()) {
                            labelList.add(new Label(label))
                        }
                        engine.render(ctx, "templates/task/manage", { engRes ->
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
}
