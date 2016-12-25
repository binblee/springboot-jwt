package com.example.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by libin on 25/12/2016.
 */

@RestController
public class UserController {

    @RequestMapping("/")
    String index(){
        return "Hello World!";
    }

    @RequestMapping("/users")
    public @ResponseBody  String getUsers(){
        return "{\"users\":[{\"firstname\":\"Richard\", \"lastname\":\"Feynman\"}," +
                "{\"firstname\":\"Marie\",\"lastname\":\"Curie\"}]}";
    }
}
