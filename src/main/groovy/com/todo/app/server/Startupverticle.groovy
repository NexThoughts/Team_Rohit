package com.todo.app.server

import io.vertx.core.AbstractVerticle
import io.vertx.core.json.JsonObject
import io.vertx.ext.web.templ.FreeMarkerTemplateEngine

class Startupverticle extends AbstractVerticle{
    FreeMarkerTemplateEngine engine = null
    def mongoClient = null
    private final static String DEFAULT_COLLECTION = "project"
    void start() {

        println "Hello Hurrah Started well!!!!!"
        JsonObject mongoConfig = new JsonObject()
    }
}
