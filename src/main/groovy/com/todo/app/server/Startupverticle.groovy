package com.todo.app.server

import com.todo.app.util.BaseUtil
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

    void start() {
        println "Hello Hurrah Started well!!!!!"

        println "Got the mongo CLient"+BaseUtil.mongoClient

        Router router = Router.router(vertx)

        router.route().handler(BodyHandler.create())


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
        router.get("/login").handler(this.&doLogin)
        vertx.createHttpServer().requestHandler(router.&accept).listen(8085)
    }

    void doLogin(RoutingContext ctx){
        MongoAuth authProvider = MongoAuth.create(mongoClient,new JsonObject())
        JsonObject authInfo = new JsonObject()
        authInfo.put("username",ctx.request().getFormAttribute("username"))
        authInfo.put("password",ctx.request().getFormAttribute("password"))
        authProvider.authenticate(authInfo,{res ->
            if(res.succeeded()){
                println "========================User Signup================================"
                ctx.response().putHeader("location", "/").setStatusCode(302).end();
            }else{
                println "===============Authentication not provided====================="
            }
        })
    }
}
