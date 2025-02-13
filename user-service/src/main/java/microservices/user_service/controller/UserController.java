package microservices.user_service.controller;

import org.springframework.web.bind.annotation.RestController;

import microservices.core_service.dto.UserDto;
import microservices.user_service.service.UserService;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
public class UserController {
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public ResponseEntity<List<UserDto>> getUserPath() {
        return ResponseEntity.ok(userService.getUsers());
    }

}
