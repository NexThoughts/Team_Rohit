package com.todo.app.server.task

import com.todo.app.co.TaskCo
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
//        router.get("/label/delete").handler(this.&delete)
//        router.post("/label/update").handler(this.&update)
        vertx.createHttpServer().requestHandler(router.&accept).listen(8085)

    }

    void save(RoutingContext ctx) {
        println "========Going to save book============="
        TaskCo taskCo = new TaskCo(name: ctx.request().getFormAttribute("name"), dueDate: ctx.request().getFormAttribute("dueDate"), _id: ctx.request().getFormAttribute("taskId"))
        Task task = new Task(taskCo)
        JsonObject taskDocument = new JsonObject()
        if (task.validator()) {
            taskDocument.put("name", task.name)
            taskDocument.put("_id", task._id)
            taskDocument.put("assignTo", task.assignTo)
            taskDocument.put("dateCreated", task.dateCreated)
            taskDocument.put("status", task.status)
            if (task.dueDate)
                taskDocument.put("dueDate", task.dueDate)
            taskDocument.put("creadtedBy", task.createdBy)
            taskDocument.put("projectId", task.projectId)
            mongoClient.save("task", taskDocument, { res ->
                if (res.succeeded()) {
                    String id = res.result();
                    System.out.println("Saved task with id " + id);
                    //ctx.response().putHeader("location", "/").setStatusCode(302).end();
                } else {
                    res.cause().printStackTrace();
                }
            });
        } else
            println "validation failed"
    }

    void editTask(RoutingContext ctx) {
        println "==========Editing the new Book===================="
        String taskID = ctx.request().getFormAttribute("taskID")
        String fieldName = ctx.request().getFormAttribute("fieldName")
        String fieldValue = ctx.request().getFormAttribute("fieldValue")
        println taskID + "   " + fieldName + "    " + fieldValue
        JsonObject query = new JsonObject().put("_id", taskID)
        JsonObject update = new JsonObject().put("$set", new JsonObject().put(fieldName, fieldValue));
        mongoClient.update("task", query, update, { res ->
            if (res.succeeded()) {
                System.out.println("task updated !");
                //ctx.response().putHeader("location", "/").setStatusCode(302).end();
            } else {
                res.cause().printStackTrace();
            }

        });
    }

    void showTaskById(RoutingContext ctx) {
        println "==========Editing the new Book===================="
        String taskID = ctx.request().getFormAttribute("taskID")
        /*String fieldName = ctx.request().getFormAttribute("fieldName")
        String fieldValue = ctx.request().getFormAttribute("fieldValue")*/
        println "taskID" + taskID
        JsonObject query = new JsonObject().put("_id", taskID)


        mongoClient.find("task", query, { res ->
            println "111111111111111111111111111111111111111"
            if (res.succeeded()) {

                for (JsonObject json : res.result()) {

                    System.out.println(json.encodePrettily());

                }
                //ctx.response().putHeader("location", "/").setStatusCode(302).end();


            } else {

                res.cause().printStackTrace();

            }

        })
    }

    void deleteTask(RoutingContext ctx) {
        println "==========Editing the new Book===================="
        String taskID = ctx.request().getFormAttribute("taskID")
        JsonObject query = new JsonObject().put("_id", taskID)
        mongoClient.remove("task", query, { res ->
            if (res.succeeded()) {
                System.out.println(" deleted !!!!");
                //ctx.response().putHeader("location", "/").setStatusCode(302).end();
            } else {

                res.cause().printStackTrace();
            }

        });
    }

    void list(RoutingContext ctx) {
        ctx.put("title", "task")
        ctx.put("name", "task List")
        JsonArray array = new JsonArray()
        List<Task> taskList = []
        JsonObject query = new JsonObject()
        mongoClient.find(DEFAULT_COLLECTION, query, { response ->
            if (response.succeeded()) {
                for (JsonObject label : response.result()) {
                    taskList.add(new Task(label))
                }
                ctx.put("taskList", taskList)
                engine.render(ctx, "templates/label/list", { res ->
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
                engine.render(ctx, "templates/label/add", { res ->
                    if (res.succeeded()) {
                        ctx.response().putHeader(HttpHeaders.CONTENT_TYPE, "text/html").end(res.result())
                    } else {
                        ctx.fail(res.cause())
                    }
                })
            }
        })
    }

}
