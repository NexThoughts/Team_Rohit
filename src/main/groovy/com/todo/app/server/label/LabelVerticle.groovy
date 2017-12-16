package com.todo.app.server.label

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
        Router router = Router.router(vertx)
        router.route().handler(BodyHandler.create())
        JsonObject query = new JsonObject()
        router.get("/label/list").handler({ ctx ->
            mongoClient.find(DEFAULT_COLLECTION, query, { response ->
                if (response.succeeded()) {
                    engine.render(ctx, "templates/label/list", { res ->
                        if (res.succeeded()) {
                            ctx.response().putHeader(HttpHeaders.CONTENT_TYPE, "text/html").end(res.result())
                        } else {
                            ctx.fail(res.cause())
                        }
                    })
                }
            })
        })
//        router.get("/label/list").handler(this.&list)
//        router.get("/label/edit").handler(this.&edit)
//        router.get("/label/delete").handler(this.&delete)
//        router.post("/label/update").handler(this.&update)
        vertx.createHttpServer().requestHandler(router.&accept).listen(8085)

    }





}