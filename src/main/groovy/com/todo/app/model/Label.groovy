package com.todo.app.model

import io.vertx.core.json.JsonObject

/**
 * Created by nexthought on 12/16/17.
 */
class Label {

    String name
    String id

    Label() {
    }

    Label(JsonObject label) {
        this.id = label.getString("_id")
        this.name = label.getString("name")
    }
}
