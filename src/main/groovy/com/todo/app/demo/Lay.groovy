package com.todo.app.demo

import io.vertx.core.AbstractVerticle
import io.vertx.core.http.HttpHeaders
import io.vertx.core.json.JsonObject
import io.vertx.ext.jdbc.JDBCClient
import io.vertx.ext.web.Router
import io.vertx.ext.web.handler.BodyHandler
import io.vertx.ext.web.templ.FreeMarkerTemplateEngine

/**
 * Created by abhinav on 16/12/17.
 */
class Lay extends AbstractVerticle {
    FreeMarkerTemplateEngine engine = null
    void start() {


        engine = FreeMarkerTemplateEngine.create()
        Router router = Router.router(vertx)

        router.route().handler(BodyHandler.create())
        router.get("/").handler({ ctx ->
            ctx.put("title", "Vert.x Web")
            ctx.put("name", "User List")

            engine.render(ctx, "templates/home.ftl", { res ->
                if (res.succeeded()) {
                    ctx.response().putHeader(HttpHeaders.CONTENT_TYPE, "text/html").end(res.result())
                } else {
                    ctx.fail(res.cause())
                }
            })
        })
//        router.get("/").handler(this.&loginCheck)
        vertx.createHttpServer().requestHandler(router.&accept).listen(8082)
    }

}
