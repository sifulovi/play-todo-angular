package models;

public class Todo {
    private int id;
    private String todoTitle;
    private String todoDescription;
    private TodoStatus todoStatus;


    public int getId() {
        return id;
    }

    public Todo setId(int id) {
        this.id = id;
        return this;
    }

    public String getTodoTitle() {
        return todoTitle;
    }

    public Todo setTodoTitle(String todoTitle) {
        this.todoTitle = todoTitle;
        return this;
    }

    public String getTodoDescription() {
        return todoDescription;
    }

    public Todo setTodoDescription(String todoDescription) {
        this.todoDescription = todoDescription;
        return this;
    }

    public TodoStatus getTodoStatus() {
        return todoStatus;
    }

    public Todo setTodoStatus(TodoStatus todoStatus) {
        this.todoStatus = todoStatus;
        return this;
    }
}
