package com.otus.example.otus_architect_task_5_app.controller;

import com.otus.example.otus_architect_task_5_app.model.UserDTO;
import com.otus.example.otus_architect_task_5_app.model.UserEntity;
import com.otus.example.otus_architect_task_5_app.service.UserService;
import java.util.UUID;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

//mark class as Controller
@RestController
public class UserController {

  public static final String ARCH_HOMEWORK_AUTH_COOKIE = "arch_homework_auth_cookie";
  @Autowired
  private UserService userService;

  @PostMapping("/register")
  private ResponseEntity registerUser(@RequestBody UserDTO userDTO) {
    UserEntity userEntity = new UserEntity();
    userEntity.setLogin(userDTO.getLogin());
    userEntity.setPassword(userDTO.getPassword());
    userService.saveOrUpdate(userEntity);
    return new ResponseEntity(HttpStatus.OK);
  }

  @PostMapping("/login")
  public ResponseEntity loginUser(@RequestBody UserDTO userDTO, HttpServletResponse response) {
    UserEntity userEntity = userService.findByLogin(userDTO.getLogin());
    if (userEntity.getPassword().equals(userDTO.getPassword())) {
      String sessionId = UUID.randomUUID().toString();
      userEntity.setSession(sessionId);
      userService.update(userEntity);
      response.addCookie(new Cookie(ARCH_HOMEWORK_AUTH_COOKIE, sessionId));
      return new ResponseEntity(HttpStatus.OK);
    }
    return new ResponseEntity(HttpStatus.UNAUTHORIZED);
  }

  @PostMapping("/logout")
  public ResponseEntity logoutUser(
      @CookieValue(name = ARCH_HOMEWORK_AUTH_COOKIE, required = false) String sessionId,
      HttpServletResponse response) {
    if (sessionId == null) {
      return new ResponseEntity(HttpStatus.UNAUTHORIZED);
    }
    UserEntity userEntity = userService.findBySession(sessionId);
    if (userEntity != null) {
      userEntity.setSession(null);
      userService.update(userEntity);
      Cookie cookie = new Cookie(ARCH_HOMEWORK_AUTH_COOKIE, "");
      cookie.setMaxAge(0);
      response.addCookie(cookie);
      return new ResponseEntity(HttpStatus.OK);
    }
    return new ResponseEntity(HttpStatus.UNAUTHORIZED);
  }

  @PostMapping("/auth")
  public ResponseEntity authPost(
      @CookieValue(name = ARCH_HOMEWORK_AUTH_COOKIE, required = false) String sessionId,
      HttpServletResponse response) {
    return doAuth(sessionId, response);
  }

  @GetMapping("/auth")
  public ResponseEntity authGet(
      @CookieValue(name = ARCH_HOMEWORK_AUTH_COOKIE, required = false) String sessionId,
      HttpServletResponse response) {
    return doAuth(sessionId, response);
  }

  private ResponseEntity doAuth(
      @CookieValue(name = ARCH_HOMEWORK_AUTH_COOKIE, required = false) String sessionId,
      HttpServletResponse response) {
    if (sessionId == null) {
      return new ResponseEntity(HttpStatus.UNAUTHORIZED);
    }
    UserEntity userEntity = userService.findBySession(sessionId);
    if (userEntity != null) {
      response.addHeader("X-User", String.valueOf(userEntity.getUserid()));
      return new ResponseEntity(HttpStatus.OK);
    }
    return new ResponseEntity(HttpStatus.UNAUTHORIZED);
  }

  @GetMapping(value = "/signin", produces = MediaType.APPLICATION_JSON_VALUE)
  private ResponseEntity signInUser() {
    String body = "{\"message\": \"Please go to login and provide Login/Password\"}";
    return new ResponseEntity<>(body, HttpStatus.UNAUTHORIZED);
  }
}
