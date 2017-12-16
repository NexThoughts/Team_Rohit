package com.todo.app.server

import com.todo.app.util.BaseUtil
import com.todo.app.co.TaskCo
import com.todo.app.model.Task
import com.todo.app.co.TaskCo
import com.todo.app.model.Task
import io.vertx.core.AbstractVerticle
import io.vertx.core.http.HttpHeaders
import io.vertx.core.json.JsonArray
import io.vertx.core.json.JsonObject
import io.vertx.ext.auth.mongo.MongoAuth
import io.vertx.ext.mongo.MongoClient
import io.vertx.ext.web.Router
import io.vertx.ext.web.RoutingContext
import io.vertx.ext.web.handler.BodyHandler
import io.vertx.ext.web.templ.FreeMarkerTemplateEngine

class Startupverticle extends AbstractVerticle{

    Router router = BaseUtil.router
    def mongoClient = BaseUtil.mongoClient
    def mongoAuth = BaseUtil.mongoAuth

    void start() {
        println "Hello Hurrah Started well!!!!!"

        println "Got the mongo CLient"+BaseUtil.mongoClient



        router.get("/").handler({ ctx ->
            println "=========Landing Page================"
            ctx.put("title", "Todo")
            BaseUtil.engine.render(ctx, "templates/landingpage.ftl", { res ->
                if (res.succeeded()) {
                    ctx.response().putHeader(HttpHeaders.CONTENT_TYPE, "text/html").end(res.result())
                } else {
                    ctx.fail(res.cause())
                }
            })
        })
        router.post("/login").handler(this.&doLogin)
        router.post("/signup").handler(this.&doSignup)
        vertx.createHttpServer().requestHandler(router.&accept).listen(8085)
        router.post("/TaskCrud/saveTask").handler(this.&saveTask)
        router.post("/TaskCrud/editTask").handler(this.&editTask)
        router.post("/TaskCrud/showTask").handler(this.&showTaskById)
        router.post("/TaskCrud/deleteTask").handler(this.&deleteTask)
        vertx.createHttpServer().requestHandler(router.&accept).listen(8085)
    }

    void doLogin(RoutingContext ctx){
        println "==============In the login========================"
        JsonObject authInfo = new JsonObject()
        authInfo.put("username",ctx.request().getFormAttribute("username"))
        authInfo.put("password",ctx.request().getFormAttribute("password"))
        println authInfo

        mongoAuth.authenticate(authInfo,{res ->
            if(res.succeeded()){
                println "========================User Logged in================================"
                ctx.response().putHeader("location", "/").setStatusCode(302).end();
            }else{
                println "===============A" +
                        "uthentication not provided====================="
            }
        })
    }


    void doSignup(RoutingContext ctx){
        JsonObject authInfo = new JsonObject()
        authInfo.put("username",ctx.request().getFormAttribute("username"))
        authInfo.put("password",ctx.request().getFormAttribute("password"))
        authInfo.put("name",ctx.request().getFormAttribute("name"))
        if(BaseUtil.doSignup(authInfo)){

        }else{
            ctx.response().putHeader("location", "/").setStatusCode(302).end();
        }

    }
    void saveTask(RoutingContext ctx) {
        println "========Going to save book============="
        TaskCo taskCo = new TaskCo(name: ctx.request().getFormAttribute("taskName"),createdBy: ctx.request().getFormAttribute("creadtedBy"), projectId: ctx.request().getFormAttribute("projectId"), dueDate: ctx.request().getFormAttribute("dueDate"))
        Task task = new Task(taskCo)
        JsonObject taskDocument = new JsonObject()
        if (task.validator()) {
            taskDocument.put("name", task.name)
            taskDocument.put("_id",task._id)
            taskDocument.put("assignTo", task.assignTo)
            taskDocument.put("dateCreated", task.dateCreated)
            taskDocument.put("status" , task.status)
            if (task.dueDate)
                taskDocument.put("dueDate",task.dueDate)
            taskDocument.put("creadtedBy", task.createdBy)
            taskDocument.put("projectId", task.projectId)
            //bookDocument.put("bookPrice", ctx.request().getFormAttribute("bookPrice"))
            mongoClient.save("tasks", taskDocument, { res ->
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
        mongoClient.update("tasks", query, update, { res ->
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


        mongoClient.find("tasks", query, { res ->
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
        mongoClient.remove("tasks", query, { res ->
            if (res.succeeded()) {
                System.out.println(" deleted !!!!");
                //ctx.response().putHeader("location", "/").setStatusCode(302).end();
            } else {

                res.cause().printStackTrace();
            }

        });
    }

}
