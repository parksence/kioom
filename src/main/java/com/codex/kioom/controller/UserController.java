package com.codex.kioom.controller;

import com.codex.kioom.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/checkUserId.ajax", method = RequestMethod.POST)
    public int checkUser(@RequestParam Map<String, Object> param) {
        return userService.getUserIdYn(param);
    }

}
