package com.otus.example.otus_architect_app_1;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class GreetingController {

  @RequestMapping(value = "/health", method = RequestMethod.GET)
  @ResponseBody
  public String greeting() {
    return "{\"status\": \"OK\"}";
  }

}