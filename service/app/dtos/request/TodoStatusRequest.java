package dtos.request;

import models.TodoStatus;

public class TodoStatusRequest {

    TodoStatus todoStatus;

    public TodoStatus getTodoStatus() {
        return todoStatus;
    }

    public void setTodoStatus(TodoStatus todoStatus) {
        this.todoStatus = todoStatus;
    }
}
