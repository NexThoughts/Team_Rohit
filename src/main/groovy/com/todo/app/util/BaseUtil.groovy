package com.todo.app.util

import io.vertx.core.AbstractVerticle
import io.vertx.core.json.JsonObject
import io.vertx.ext.auth.mongo.MongoAuth
import io.vertx.ext.mongo.MongoClient
import io.vertx.ext.web.Router
import io.vertx.ext.web.handler.BodyHandler
import io.vertx.ext.web.templ.FreeMarkerTemplateEngine

class BaseUtil extends AbstractVerticle {

    public static def mongoClient = null
    public static def mongoAuth = null
    public static Router router = null
    public static FreeMarkerTemplateEngine engine = FreeMarkerTemplateEngine.create()

    private final static String DEFAULT_DATABASE = "todo"
    private final static String DEFAULT_MONGO_URL = "mongodb://localhost:27017"
    public static COMMENT_COLLECTION = 'comment'
    public static TASK_COLLECTION = 'comment'
    public static PROJECT_COLLECTION = 'comment'
    public static USER_COLLECTION = 'user'


    void start() {
        JsonObject mongoClientConfig = new JsonObject()
        mongoClientConfig.put("connection_string", DEFAULT_MONGO_URL)
        mongoClientConfig.put("db_name", DEFAULT_DATABASE)
        mongoClient = MongoClient.createShared(vertx, mongoClientConfig)
        mongoAuth = MongoAuth.create(mongoClient,new JsonObject())
        router =  Router.router(vertx)
        router.route().handler(BodyHandler.create())
    }


}
