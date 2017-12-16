package com.todo.app.model

import io.vertx.core.json.JsonObject
import io.vertx.ext.auth.User


class Comment {
    String description
    String id


    Comment(){}
    Comment (JsonObject jsonObject){
          this.description=jsonObject.getString("description")
        this.id=jsonObject.getString("_id")
    }
}
