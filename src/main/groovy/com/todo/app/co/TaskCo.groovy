package com.todo.app.co

import com.todo.app.model.Comment
import com.todo.app.model.Label

class TaskCo {
    String name
    String projectId //to be replaced by project class's object
    String createdBy //to be replaced with user class object
    String assignTo
    String dueDate
    List<Label> labels
    String _id
    List<Comment> comments

}
