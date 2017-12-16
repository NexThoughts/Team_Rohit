package com.todo.app.server.bootstrap

import com.todo.app.server.Startupverticle
import com.todo.app.util.BaseUtil
import io.vertx.core.AbstractVerticle
import io.vertx.core.json.JsonObject

class BootstrapVerticle extends AbstractVerticle{

    private static final MAIL_CLIENT = "@gmail.com"
    private static final DEFAULT_PASSWORD = "user_"
    private def mongoClient = BaseUtil.mongoClient
    JsonObject authInfo = null
    private static  mongoAuth = BaseUtil.mongoAuth


    public void start(){
        println "===================In the bootstrap================="

        JsonObject query = new JsonObject().put("user", new JsonObject());
//        if(getDocuments("user") == 0){
            String container = null , psssword = null
            authInfo = new JsonObject()
            List <String> user_role = ['ROLE_USER']
            (1..10).each {index ->
                container = randomIdentifier()+MAIL_CLIENT
                psssword = DEFAULT_PASSWORD+index
                mongoAuth.insertUser(container , psssword , user_role , null , {result->
                    if(result.succeeded()){
                        println "================ User Signup done==============="+result.result()
                    }else{
                        println "===========User signup not done==========="
                    }
                })
            }
            List <String> admin_role = ['ROLE_ADMIN']
            (1..2)?.reverse?.each {index ->
                container = randomIdentifier()+MAIL_CLIENT
                psssword = DEFAULT_PASSWORD+index
                mongoAuth.insertUser(container , psssword , admin_role , null , {result->
                    if(result.succeeded()){
                        println "================ Admin Signup done==============="+result.result()
                    }else{
                        println "===========User signup not done==========="
                    }
                })
            }
//        }else{
//            println "users already exist========================"
//        }
    }
    public String randomIdentifier() {
        final Set<String> identifiers = new HashSet<String>();
        final String lexicon = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        final java.util.Random rand = new java.util.Random();
        StringBuilder builder = new StringBuilder();
        while (builder.toString().length() == 0) {
            int length = rand.nextInt(5) + 5;
            for (int i = 0; i < length; i++) {
                builder.append(lexicon.charAt(rand.nextInt(lexicon.length())));
            }
            if (identifiers.contains(builder.toString())) {
                builder = new StringBuilder();
            }
        }
        return builder.toString();
    }

    public long getDocuments(String collectionName){
        println "COllection Name"+collectionName
        JsonObject query = new JsonObject()
        mongoClient.count(collectionName, query, {res ->

            if (res.succeeded()) {

                def num = res.result();
                println num

            } else {

                res.cause().printStackTrace();

            }
        });
    }
}
