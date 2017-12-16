package com.todo.app.model

import io.vertx.ext.auth.User


class Project {
    String name
    User createdBy
    Date dateCreated
    Date lastUpdated

    Project(String name, User createdBy, Date dateCreated, Date lastUpdated) {
        this.name = name
        this.createdBy = createdBy
        this.dateCreated = dateCreated
        this.lastUpdated = lastUpdated
    }
}
