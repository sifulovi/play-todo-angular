package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import dtos.request.LoginRequest;
import dtos.response.LoginResponse;
import models.User;
import org.springframework.beans.BeanUtils;
import play.libs.Json;
import play.libs.concurrent.HttpExecutionContext;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;
import services.UserService;

import javax.inject.Inject;

public class UserController extends Controller {

    UserService userService;
    private HttpExecutionContext ec;


    @Inject
    public UserController(UserService userService, HttpExecutionContext ec) {
        this.userService = userService;
        this.ec = ec;
    }

    public Result login(Http.Request payload) {
        JsonNode json = payload.body().asJson();
        LoginRequest resource = Json.fromJson(json, LoginRequest.class);
        System.out.println(resource);

        if (userService.validation(resource).size() > 0) {
            JsonNode result = userService.validation(resource);
            return badRequest(result);
        } else {
            User user = userService.findUserByUsernameAndPassword(resource);
            if (user != null) {
                LoginResponse loginResponse = new LoginResponse();
                BeanUtils.copyProperties(user, loginResponse);
                //loginResponse.setUserRole(user.getUserRole());
                return ok(Json.toJson(loginResponse));

            } else {
                return badRequest("Not Found");
            }
        }

    }
}
