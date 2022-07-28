package com.raisetech.springtestsample.controller;

import com.raisetech.springtestsample.repository.entity.User;
import com.raisetech.springtestsample.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api")
public class UserRestController {

  private final UserService userService;

  public UserRestController(UserService userService) {
    this.userService = userService;
  }

  @GetMapping("/users")
  public List<User> users() {
    return userService.findAllUsers();
  }

  @PostMapping("/users")
  public ResponseEntity<Map> create(@RequestBody CreateForm form, HttpServletRequest request) {
    int id = userService.saveUser(form.getName());
    URI uri = ServletUriComponentsBuilder.fromContextPath(request).path("/users/" + id).build().toUri();
    return ResponseEntity.created(uri).body(Map.of("message", "user created"));
  }

  static class CreateForm {
    private String name;

    public String getName() {
      return name;
    }

    public void setName(String name) {
      this.name = name;
    }
  }
}
