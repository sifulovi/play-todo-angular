package services;

import com.fasterxml.jackson.databind.JsonNode;
import dtos.request.TodoRequest;
import dtos.request.TodoStatusRequest;
import models.Todo;
import models.TodoStatus;
import org.springframework.beans.BeanUtils;
import play.libs.Json;

import javax.inject.Singleton;
import java.util.*;
import java.util.stream.Collectors;

@Singleton
public class TodoService {


    static List<Todo> todoList = new ArrayList<>();

    static {
        for (int i = 1; i <= 5; i++) {
            todoList.add(new Todo().setId(i).setTodoTitle("Oneeee" + i).setTodoDescription("One").setTodoStatus(TodoStatus.ACTIVE));
        }
    }


    public List<Todo> getAllTodos() {
        todoList.sort(Comparator.comparing(Todo::getId));
        return todoList;
    }


    public List<Todo> getAllInactiveTodos() {
        return todoList.stream()
                .filter(todo -> todo.getTodoStatus().equals(TodoStatus.INACTIVE))
                .collect(Collectors.toList());
    }

    public JsonNode saveTodo(TodoRequest todoRequest) {
        Todo todo = new Todo();
        BeanUtils.copyProperties(todoRequest, todo);
        int id = todoList.size() + 1;
        todo.setId(id);
        todoList.add(todo);
        return Json.toJson(todo);
    }


    public JsonNode updateStatus(TodoStatusRequest todoStatusRequest, int id) {
        Todo todo = findById(id);
        if (todo != null) {
            todoList.remove(todo);
            todo.setTodoStatus(todoStatusRequest.getTodoStatus());
            todoList.add(todo);
            return Json.toJson(todo);
        }

        return null;
    }

    public JsonNode deleteStatus(int id) {
        Todo todo = findById(id);
        if (todo != null) {
            todoList.remove(todo);
            String status = "Delete TOdo";
            return Json.toJson(status);
        }

        return null;
    }


    public JsonNode todoValidation(TodoRequest todoRequest) {
        Map<String, String> validations = new HashMap<>();

        if (todoRequest.getTodoTitle().equals("")) {
            validations.put("todoTitle", "Todo Title must not be empty");
        }

        for (Todo todo : todoList) {
            if (todo.getTodoTitle().equals(todoRequest.getTodoTitle())) {
                validations.put("unique", "Todo Title must be unique");
            }
        }
        return Json.toJson(validations);
    }

    public Todo findById(int id) {
        return todoList.stream().filter(todo -> todo.getId() == id).findFirst().orElse(null);
    }


}
