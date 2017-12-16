package com.todo.app.model

import io.vertx.core.json.JsonObject
import io.vertx.ext.auth.User


class Comment {
    String description
    String uuid=UUID.randomUUID()?.toString()
    Task task
    User createdBy
    Date dateCreated
    Date lastUpdated

    Comment(){}
    Comment (JsonObject jsonObject){
        this.description=jsonObject.getString("description")
        this.uuid=jsonObject.getString("uuid")
    }
}
