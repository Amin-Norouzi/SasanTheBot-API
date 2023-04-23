package dev.aminnorouzi.userservice.controller;

import dev.aminnorouzi.userservice.dto.UserRequest;
import dev.aminnorouzi.userservice.dto.UserResponse;
import dev.aminnorouzi.userservice.mapper.UserMapper;
import dev.aminnorouzi.userservice.model.User;
import dev.aminnorouzi.userservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/users")
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponse createUser(@Valid @RequestBody UserRequest request) {
        User user = userService.create(request);
        return userMapper.mapFromUser(user);
    }

    @GetMapping("/{id}")
    public UserResponse getUserById(@PathVariable Long id) {
        User user = userService.getById(id);
        return userMapper.mapFromUser(user);
    }

    @GetMapping
    public UserResponse.Pageable getAllUsers(@RequestParam(defaultValue = "0") Integer page,
                                             @RequestParam(defaultValue = "5") Integer size,
                                             @RequestParam(defaultValue = "id") String sort) {
        Page<User> users = userService.getAll(page, size, sort);
        return userMapper.mapFromUser(users);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable Long id) {
        userService.delete(id);
    }

    @PostMapping("/{id}")
    public UserResponse changeUserStatus(@PathVariable Long id, @RequestParam String status) {
        User user = userService.change(id, status);
        return userMapper.mapFromUser(user);
    }
}
