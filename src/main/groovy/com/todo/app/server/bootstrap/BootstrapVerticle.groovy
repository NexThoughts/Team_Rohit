package com.todo.app.server.bootstrap

import com.todo.app.server.Startupverticle
import io.vertx.core.AbstractVerticle
import io.vertx.core.json.JsonObject

class BootstrapVerticle extends AbstractVerticle{

    private static final MAIL_CLIENT = "@gmail.com"
    private static final DEFAULT_PASSWORD = "user_"
    JsonObject authInfo = null


    public void start(){
        String container = null
        (1..10).each {index ->
            container = randomIdentifier()+MAIL_CLIENT
            authInfo.put("username",container)
            authInfo.put("password",DEFAULT_PASSWORD+${it})

        }
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
}
