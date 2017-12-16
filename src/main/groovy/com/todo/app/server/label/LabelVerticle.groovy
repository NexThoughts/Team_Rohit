package com.todo.app.server.label

import com.todo.app.model.Label
import com.todo.app.util.BaseUtil
import io.vertx.core.AbstractVerticle
import io.vertx.core.http.HttpHeaders
import io.vertx.core.json.JsonArray
import io.vertx.core.json.JsonObject
import io.vertx.ext.mongo.MongoClient
import io.vertx.ext.web.Router
import io.vertx.ext.web.RoutingContext
import io.vertx.ext.web.handler.BodyHandler
import io.vertx.ext.web.templ.FreeMarkerTemplateEngine


class LabelVerticle extends AbstractVerticle {
    FreeMarkerTemplateEngine engine = null
    def mongoClient = BaseUtil.mongoClient
    public static DEFAULT_COLLECTION = 'label'

    void start() {
        engine = FreeMarkerTemplateEngine.create()
        Router router = BaseUtil.router
//        router.route().handler(BodyHandler.create())
        router.get("/label/list").handler(this.&list)
        router.get("/label/add").handler(this.&add)
        router.post("/label/save").handler(this.&save)
        router.get("/label/edit").handler(this.&edit)
        router.get("/label/edit/:labelId").handler(this.&edit)
        router.get("/label/delete/:labelId").handler(this.&delete)
//        router.get("/label/delete").handler(this.&delete)
//        router.post("/label/update").handler(this.&update)
        vertx.createHttpServer().requestHandler(router.&accept).listen(8085)

    }


    void list(RoutingContext ctx) {
        ctx.put("title", "Label")
        ctx.put("name", "Label List")
        JsonArray array = new JsonArray()
        List<Label> labelList = []
        JsonObject query = new JsonObject()
        mongoClient.find(DEFAULT_COLLECTION, query, { response ->
            if (response.succeeded()) {
                for (JsonObject label : response.result()) {
                    labelList.add(new Label(label))
                }
                ctx.put("labelList", labelList)
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

    void save(RoutingContext ctx) {
        println "111111111111111111111111111"
        println "========Going to save label=============" + ctx.request().getFormAttribute("name")
        JsonObject label = new JsonObject().put("name", ctx.request().getFormAttribute("name"))
        label.put("uuid", UUID.randomUUID().toString())
        mongoClient.save(DEFAULT_COLLECTION, label, { res ->
            if (res.succeeded()) {
                String id = res.result();
                System.out.println("Saved Label With Id" + id);
                ctx.response().putHeader("location", "/label/list").setStatusCode(302).end();
            } else {
                res.cause().printStackTrace();
            }
        });

    }

    void edit(RoutingContext ctx) {


        println "==========Editing the Label===================="
        println "==========Editing the Label====================" + ctx.request().getParam("labelId")
        String labelId = ctx.request().getParam("labelId")
        JsonObject query = new JsonObject().put("_id", labelId)
        Label label = null
        mongoClient.find(DEFAULT_COLLECTION, query, { res ->
            if (res.succeeded()) {
                for (JsonObject json : res.result()) {
                    println "json  " + json
                    label = new Label(json)
                    ctx.put("labelName", label?.name)
                    ctx.put("labelId", label?.id)
                }
                engine.render(ctx, "templates/label/edit", { engRes ->
                    if (engRes.succeeded()) {
                        ctx.response().putHeader(HttpHeaders.CONTENT_TYPE, "text/html").end(engRes.result())
                    } else {
                        ctx.fail(engRes.cause())
                    }
                })

            } else {
                res.cause().printStackTrace();

            }

        })


    }

    void update(RoutingContext ctx) {
        println "==========Editing the Label===================="
        println "==========Editing the Label====================" + ctx.request().getParam("labelId")
        String labelId = ctx.request().getParam("labelId")
        JsonObject query = new JsonObject().put("_id", labelId)
        mongoClient.find(DEFAULT_COLLECTION, query, { res ->
            if (res.succeeded()) {
                for (JsonObject json : res.result()) {
                    println "json  " + json
                    Label label = new Label(json)
                    ctx.put("label", label)
                }


            } else {

                res.cause().printStackTrace();

            }

        })

        engine.render(ctx, "templates/label/edit", { res ->
            if (res.succeeded()) {
                ctx.response().putHeader(HttpHeaders.CONTENT_TYPE, "text/html").end(res.result())
            } else {
                ctx.fail(res.cause())
            }
        })

    }


    void delete(RoutingContext ctx) {
        println "==========Deleting the Label===================="
        String labelId = ctx.request().getParam("labelId")
        println "labelId   " + labelId
        println "labelId   " + labelId
        JsonObject query = new JsonObject().put("_id", labelId)
        mongoClient.remove(DEFAULT_COLLECTION, query, { res ->
            if (res.succeeded()) {
                System.out.println(" deleted !!!!");
                ctx.response().putHeader("location", "/label/list").setStatusCode(302).end();
            } else {

                res.cause().printStackTrace();
            }

        });
    }
}