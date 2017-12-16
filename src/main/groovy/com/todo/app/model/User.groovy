package com.todo.app.model

import io.vertx.core.json.JsonObject

class User {

    String username
    String password
    String id

    User(String username, String password) {
        this.username = username
        this.password = password
    }

    User(JsonObject user) {
        this.username = user.getString("username")
        this.id = user.getString("_id")
        println("#######################################")
        println("#######################################")
        println(user.getString("_id"))
        println(user.getString("username"))
        println("#######################################")

    }
}
