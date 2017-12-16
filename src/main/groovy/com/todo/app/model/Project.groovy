package com.todo.app.model

import io.vertx.core.json.JsonObject
import io.vertx.ext.auth.User


class Project {
    String id
    String name
    User createdBy
    Date dateCreated
    Date lastUpdated

    Project(JsonObject label) {
        this.id = label.getString("_id")
        this.name = label.getString("name")
    }

    Project(String name, User createdBy, Date dateCreated, Date lastUpdated) {
        this.name = name
        this.createdBy = createdBy
        this.dateCreated = dateCreated
        this.lastUpdated = lastUpdated
    }
}
