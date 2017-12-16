package com.todo.app.server

import com.todo.app.util.BaseUtil
import io.vertx.core.AbstractVerticle
import io.vertx.core.http.HttpHeaders
import io.vertx.core.json.JsonObject
import io.vertx.ext.auth.User
import io.vertx.ext.auth.mongo.MongoAuth
import io.vertx.ext.mongo.MongoClient
import io.vertx.ext.web.Router
import io.vertx.ext.web.RoutingContext
import io.vertx.ext.web.Session
import io.vertx.ext.web.handler.BodyHandler
import io.vertx.ext.web.templ.FreeMarkerTemplateEngine

class Startupverticle extends AbstractVerticle {

    Router router = BaseUtil.router
    def mongoClient = BaseUtil.mongoClient
    def mongoAuth = BaseUtil.mongoAuth
    def engine = BaseUtil.engine

    void start() {
        println "Hello Hurrah Started well!!!!!"

        println "Got the mongo CLient" + BaseUtil.mongoClient

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
        router.get("/projects/list").handler(this.&listProjects)
        vertx.createHttpServer().requestHandler(router.&accept).listen(8085)
        router.get("/logout").handler(this.&doLogout)
    }

    void doLogout(RoutingContext ctx){
        if(ctx.session()){
            BaseUtil.isSession = Boolean.FALSE
        }
    }

    void doLogin(RoutingContext ctx) {
        println "==============In the login========================"
        JsonObject authInfo = new JsonObject()
        authInfo.put("username", ctx.request().getFormAttribute("username"))
        authInfo.put("password", ctx.request().getFormAttribute("password"))
        println authInfo

        mongoAuth.authenticate(authInfo, { res ->
            if (res.succeeded()) {
                println "========================User Logged in================================"
                User user = res.result()
                Session session = ctx.session();
                session.put("user", user);
                BaseUtil.isSession = true
                ctx.response().putHeader("location", "/").setStatusCode(302).end();
            } else {
                println "===============A" +
                        "uthentication not provided====================="
            }
        })
    }


    void doSignup(RoutingContext ctx) {
        JsonObject authInfo = new JsonObject()
        authInfo.put("username", ctx.request().getFormAttribute("username"))
        authInfo.put("password", ctx.request().getFormAttribute("password"))
        authInfo.put("name", ctx.request().getFormAttribute("name"))
        if (BaseUtil.doSignup(authInfo, vertx)) {

        } else {
            ctx.response().putHeader("location", "/").setStatusCode(302).end();
        }

    }

    void listProjects(RoutingContext ctx){
        BaseUtil.listCollections("user" ,ctx , "templates/label/list" , "labelList" ,"Label","Label List")
    }
}
