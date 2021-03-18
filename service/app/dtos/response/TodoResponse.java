package dtos.response;

public class TodoResponse {
    private int id;
    private String todoTitle;
    private String todoDescription;
    private boolean todoStatus;

    public TodoResponse(int id, String todoTitle, String todoDescription, boolean todoStatus) {
        this.id = id;
        this.todoTitle = todoTitle;
        this.todoDescription = todoDescription;
        this.todoStatus = todoStatus;
    }

    public TodoResponse(String todoTitle, String todoDescription, boolean todoStatus) {
        this.todoTitle = todoTitle;
        this.todoDescription = todoDescription;
        this.todoStatus = todoStatus;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTodoTitle() {
        return todoTitle;
    }

    public void setTodoTitle(String todoTitle) {
        this.todoTitle = todoTitle;
    }

    public String getTodoDescription() {
        return todoDescription;
    }

    public void setTodoDescription(String todoDescription) {
        this.todoDescription = todoDescription;
    }

    public boolean isTodoStatus() {
        return todoStatus;
    }

    public void setTodoStatus(boolean todoStatus) {
        this.todoStatus = todoStatus;
    }
}
