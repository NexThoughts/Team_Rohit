package com.todo.app.util

import io.vertx.core.AbstractVerticle
import io.vertx.core.json.JsonObject
import io.vertx.ext.mongo.MongoClient
import io.vertx.ext.web.templ.FreeMarkerTemplateEngine

class BaseUtil extends AbstractVerticle {

    public static def mongoClient = null
    public static FreeMarkerTemplateEngine engine = FreeMarkerTemplateEngine.create()
    private final static String DEFAULT_DATABASE = "todo"
    private final static String DEFAULT_MONGO_URL = "mongodb://localhost:27017"

    void start() {
        JsonObject mongoClientConfig = new JsonObject()
        mongoClientConfig.put("connection_string", DEFAULT_MONGO_URL)
        mongoClientConfig.put("db_name", DEFAULT_DATABASE)

        mongoClient = MongoClient.createShared(vertx, mongoClientConfig)
    }

}
