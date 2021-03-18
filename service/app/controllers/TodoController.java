package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import dtos.request.TodoRequest;
import dtos.request.TodoStatusRequest;
import play.libs.Json;
import play.libs.concurrent.HttpExecutionContext;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;
import services.TodoService;

import javax.inject.Inject;

public class TodoController extends Controller {


    private HttpExecutionContext ec;
    private final TodoService todoService;

    @Inject
    public TodoController(HttpExecutionContext ec, TodoService todoService) {
        this.ec = ec;
        this.todoService = todoService;
    }


    public Result create(Http.Request payload) {
        JsonNode json = payload.body().asJson();
        TodoRequest resource = Json.fromJson(json, TodoRequest.class);
        if (todoService.todoValidation(resource).size() > 0) {
            return badRequest(todoService.todoValidation(resource));
        }
        // todoService.saveTodo(resource);
        return created(todoService.saveTodo(resource));
        //  return created();
    }


    public Result statusUpdate(Http.Request payload ,String id) {
        JsonNode json = payload.body().asJson();
        TodoStatusRequest resource = Json.fromJson(json, TodoStatusRequest.class);
        int todoId = Integer.parseInt(id);
        // todoService.saveTodo(resource);
        return ok(todoService.updateStatus(resource, todoId));
        //  return created();
    }


    public Result deleteTodo(String id) {
        int todoId = Integer.parseInt(id);
        return ok(todoService.deleteStatus(todoId));
    }


    public Result allTodos() {
        return ok(Json.toJson(todoService.getAllTodos()));
    }

    public Result allTodosByInactive() {
        return ok(Json.toJson(todoService.getAllInactiveTodos()));

    }

    public Result create() {
        int x = 5656;
        return ok(views.html.index.render());
    }

    public Result update() {
        int x = 5656;
        return ok(views.html.index.render());
    }

    public Result delete() {
        int x = 5656;
        return ok(views.html.index.render());
    }

}
