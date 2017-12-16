package com.todo.app.model

import io.vertx.core.json.JsonObject
import io.vertx.ext.auth.User

public class Task {

    Project project
    User createdBy
    String uuid = UUID?.randomUUID()?.toString()

    Task(){}

    Task(JsonObject jsonObject){
        this.p
    }
}
