package com.todo.app.model

import com.todo.app.co.TaskCo
import com.todo.app.enums.Enums
import io.vertx.core.json.JsonObject

public class Task {
    String name
    String id
    String _id = UUID.randomUUID().toString()
    String projectId //to be replaced by project class's object
    String createdBy //to be replaced with user class object
    String assignTo //to be replaced with user class object
    String dateCreated = new Date().toString()
    String dueDate
    Enums.Task_status status = Enums.Task_status.PENDING
    List<String> labels
    List<String> comments


    public Task() {}
    Task(JsonObject task) {
        this.id = task.getString("_id")
        this.name = task.getString("name")
    }
    public Task (TaskCo taskCo) {
        name = taskCo.name
        projectId = taskCo.projectId
        createdBy = taskCo.createdBy
        assignTo = createdBy
        dueDate = taskCo.dueDate
        labels = taskCo.labels

    }
    public Boolean validator() {
        if (name !=null && projectId!=null && createdBy!=null)
            true
        else
            false
    }
}
