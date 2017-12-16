package com.todo.app.web

import com.todo.app.server.Startupverticle
import com.todo.app.util.BaseUtil
import io.vertx.core.Vertx

class WebServer {
    public static void main(String... args) {
        Vertx vertx = Vertx.vertx()
        vertx.deployVerticle(new BaseUtil())
        Thread.sleep(3000);
        vertx.deployVerticle(new Startupverticle())
    }
}
