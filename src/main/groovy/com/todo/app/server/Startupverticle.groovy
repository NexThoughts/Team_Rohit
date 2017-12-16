package com.todo.app.server

import io.vertx.core.AbstractVerticle
import io.vertx.core.http.HttpHeaders
import io.vertx.core.json.JsonArray
import io.vertx.core.json.JsonObject
import io.vertx.ext.mongo.MongoClient
import io.vertx.ext.web.Router
import io.vertx.ext.web.handler.BodyHandler
import io.vertx.ext.web.templ.FreeMarkerTemplateEngine

class Startupverticle extends AbstractVerticle{
    FreeMarkerTemplateEngine engine = null
    def mongoClient = null
    private final static String DEFAULT_DATABASE = "todo"
    private final static String DEFAULT_MONGO_URL = "mongodb://localhost:27017"


    void start() {
        println "Hello Hurrah Started well!!!!!"

        JsonObject mongoClientConfig = new JsonObject()
        mongoClientConfig.put("connection_string", DEFAULT_MONGO_URL)
        mongoClientConfig.put("db_name", DEFAULT_DATABASE)

        mongoClient = MongoClient.createShared(vertx, mongoClientConfig)

        println "Got the mongo CLient"+mongoClient

        engine = FreeMarkerTemplateEngine.create()

        Router router = Router.router(vertx)

        router.route().handler(BodyHandler.create())


        router.get("/").handler({ ctx ->
            println "=========Landing Page================"
            ctx.put("title", "Todo")
            engine.render(ctx, "templates/landingpage.ftl", { res ->
                if (res.succeeded()) {
                    ctx.response().putHeader(HttpHeaders.CONTENT_TYPE, "text/html").end(res.result())
                } else {
                    ctx.fail(res.cause())
                }
            })
        })

        vertx.createHttpServer().requestHandler(router.&accept).listen(8085)
    }
}
