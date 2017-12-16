package com.todo.app.model

import io.vertx.ext.auth.User


class Project {
    String name
    User createdBy
    String uuid = UUID?.randomUUID()?.toString()
    Date dateCreated
    Date lastUpdated



}
