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

    @RequestMapping(value = "/doLogin", method = { RequestMethod.GET, RequestMethod.POST })
    public Map<String, Object> userLogin(@RequestParam Map<String, Object> param) {
        Map<String, Object> result = userService.userLogin(param);
        return result;
    }

}
