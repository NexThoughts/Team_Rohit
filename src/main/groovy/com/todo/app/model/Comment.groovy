package com.todo.app.model

import io.vertx.core.json.JsonObject
import io.vertx.ext.auth.User


class Comment {
    String description
    String id
    User createdBy
    Task task


    Comment() {}

    Comment(JsonObject jsonObject) {
        this.description = jsonObject.getString("description")
        this.id = jsonObject.getString("_id")
    }
}
