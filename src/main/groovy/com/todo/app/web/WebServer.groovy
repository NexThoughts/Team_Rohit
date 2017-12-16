package com.todo.app.web

import com.todo.app.server.Startupverticle
import io.vertx.core.Vertx

class WebServer {
    public static void main(String... args) {
        Vertx vertx = Vertx.vertx()
        vertx.deployVerticle(new Startupverticle())
    }
}
