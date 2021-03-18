package services;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.util.BeanUtil;
import dtos.request.LoginRequest;
import dtos.response.LoginResponse;
import models.User;
import models.UserRole;
import org.springframework.beans.BeanUtils;
import play.libs.Json;

import javax.xml.transform.Result;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserService {

    List<User> users = Arrays.asList(
            new User().setFullName("Saiful Islam").setPassword("12345").setUsername("admin").setUserRole(UserRole.ADMIN_USER),
            new User().setFullName("Ovi Islam").setPassword("12345").setUsername("custom1").setUserRole(UserRole.COMMON_USER),
            new User().setFullName("Islam Saiful").setPassword("12345").setUsername("custom2").setUserRole(UserRole.COMMON_USER)
    );


    public JsonNode validation(LoginRequest loginRequest) {
        Map<String, String> validations = new HashMap<>();

        if (loginRequest.getUsername().equals("")) {
            validations.put("username", "Username must not be empty");
        }

        if (loginRequest.getPassword().equals("")) {
            validations.put("password", "Password must not be empty");
        }

        return Json.toJson(validations);
    }

    public User findUserByUsernameAndPassword(LoginRequest loginRequest) {
        return users.stream()
                .filter(customer -> customer.getUsername().equals(loginRequest.getUsername())
                        && customer.getPassword().equals(loginRequest.getPassword()))
                .findAny()
                .orElse(null);
    }

}
