package com.todo.app.server.mail

import com.todo.app.util.HtmlUtil
import io.vertx.core.AbstractVerticle
import io.vertx.core.Future
import io.vertx.core.Handler
import io.vertx.core.eventbus.Message
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
        String sendTo = ""
        vertx.eventBus().consumer("singup" , new Handler<Message<String>>() {
            @Override
            void handle(Message<String> event) {
                System.out.println("received message: " + event.body());
                sendTo = event.body()
                message.setFrom("rohit@fintechlabs.in");
                message.setTo(sendTo);
                //message.setText("this is the plain message text");
                message.setHtml(HtmlUtil.mailHtml);
            }
        });

        /*message.setFrom("rohit@fintechlabs.in");
        message.setTo(sendTo);
        message.setText("this is the plain message text");
        message.setHtml("this is html text <a href=\"http://vertx.io\">vertx.io</a>");*/

        mailClient.sendMail(message, { result ->
            if (result.succeeded()) {
                System.out.println(result.result());
            } else {
                result.cause().printStackTrace();
            }
        });

    }
}