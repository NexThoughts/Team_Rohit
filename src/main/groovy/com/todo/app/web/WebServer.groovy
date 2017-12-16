package com.todo.app.web

import com.todo.app.server.Startupverticle
import com.todo.app.server.bootstrap.BootstrapVerticle
import com.todo.app.server.label.LabelVerticle
import com.todo.app.server.project.ProjectVerticle
import com.todo.app.server.task.TaskVerticle
import com.todo.app.util.BaseUtil
import io.vertx.core.Vertx

class WebServer {
    public static void main(String... args) {
        Vertx vertx = Vertx.vertx()
        vertx.deployVerticle(new BaseUtil())
        Thread.sleep(3000);
        vertx.deployVerticle(new BootstrapVerticle())
        Thread.sleep(3000);
        vertx.deployVerticle(new ProjectVerticle())
        vertx.deployVerticle(new LabelVerticle())
        vertx.deployVerticle(new TaskVerticle())
        Thread.sleep(3000);

    }
}
