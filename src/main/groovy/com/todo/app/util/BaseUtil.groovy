package com.todo.app.util

import com.todo.app.model.Label
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

class BaseUtil extends AbstractVerticle {

    public static def mongoClient = null
    public static def mongoAuth = null
    public static Router router = null
    public static FreeMarkerTemplateEngine engine = FreeMarkerTemplateEngine.create()
    public static List<String> roles = ['ROLE_USER']

    private final static String DEFAULT_DATABASE = "todo"
    private final static String DEFAULT_MONGO_URL = "mongodb://localhost:27017"
    public static COMMENT_COLLECTION = 'comment'
    public static TASK_COLLECTION = 'task'
    public static PROJECT_COLLECTION = 'project'
    public static USER_COLLECTION = 'user'
    private static JsonObject DEFAULT_QUERY = new JsonObject()


    void start() {
        JsonObject mongoClientConfig = new JsonObject()
        mongoClientConfig.put("connection_string", DEFAULT_MONGO_URL)
        mongoClientConfig.put("db_name", DEFAULT_DATABASE)
        mongoClient = MongoClient.createShared(vertx, mongoClientConfig)
        mongoAuth = MongoAuth.create(mongoClient,new JsonObject())
        router =  Router.router(vertx)
        router.route().handler(BodyHandler.create())
    }

    static Boolean doSignup(JsonObject user){
        println user
        if(!user)
            return;
        mongoAuth.insertUser(user?.getString("username"), user?.getString("password") , roles , null , {result->
            if(result.succeeded()){
                println "================ User Signup done==============="+result.result()
                return  true
            }else{
                println "===========User signup not done==========="
                return false
            }
        })
    }
    static long getDocuments(String collectionName){
        println "COllection Name"+collectionName
        JsonObject query = new JsonObject()
        mongoClient.count(collectionName, query, {res ->

            if (res.succeeded()) {

                long num = res.result();
                println num

            } else {

                res.cause().printStackTrace();

            }
        });
    }

    static void listCollections(String collection , RoutingContext ctx , String templateName , String keyname , String titleName , String titleListName) {
        List collectionList = []
        mongoClient.find(collection, DEFAULT_QUERY, { response ->
            if (response.succeeded()) {
                for (JsonObject object : response.result()) {
                    collectionList.add(new Label(object))
                }
                println "collectionList Size"+collectionList?.size()
                ctx.put(keyname, collectionList)
                ctx.put("title", titleName)
                ctx.put("name", titleListName)
                if(collectionList?.size() > 0){
                    engine.render(ctx, templateName, { res ->
                        if (res.succeeded()) {
                            ctx.response().putHeader("location", templateName).setStatusCode(302).end();
                        } else {
                            ctx.fail(res.cause())
                        }
                    })
                }
            }
        })
    }


}
