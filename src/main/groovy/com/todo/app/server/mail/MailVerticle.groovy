package com.todo.app.server.mail

import io.vertx.core.AbstractVerticle
import io.vertx.core.Future
import io.vertx.ext.mail.MailClient
import io.vertx.ext.mail.MailConfig
import io.vertx.ext.mail.MailMessage
import io.vertx.ext.mail.StartTLSOptions

/**
 * Created by nexthought on 12/16/17.
 */
class MailVerticle extends AbstractVerticle {

    public void start(Future<Void> startFuture) {
        MailConfig config = new MailConfig();
        config.setHostname("smtp.gmail.com");
        config.setPort(587);
        config.setStarttls(StartTLSOptions.REQUIRED);
        config.setUsername("rohit@fintechlabs.in");
        config.setPassword("team_rohit");

        MailClient mailClient = MailClient.createNonShared(vertx, config);


        MailMessage message = new MailMessage();

        message.setFrom("rohit@fintechlabs.in");
        message.setTo("rohit@fintechlabs.in");
        message.setText("this is the plain message text");
        message.setHtml("this is html text <a href=\"http://vertx.io\">vertx.io</a>");

        mailClient.sendMail(message, { result ->
            if (result.succeeded()) {
                System.out.println(result.result());
            } else {
                result.cause().printStackTrace();
            }
        });

    }
}