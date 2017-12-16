package com.todo.app.demo

import io.vertx.core.Vertx

/**
 * Created by abhinav on 16/12/17.
 */
class Starting {
    public static void main(String[] args){
        Vertx vertx=Vertx.vertx()

        vertx.deployVerticle(new Lay())

    }
}
