package com.codex.kioom.controller;

import com.codex.kioom.config.security.auth.PrincipalDetails;
import com.codex.kioom.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/checkUserId.ajax", method = RequestMethod.POST)
    public int checkUser(@RequestParam Map<String, Object> param) {
        return userService.getUserIdYn(param);
    }

    // 병원 등록
    @RequestMapping(value = "/account_proc", method = { RequestMethod.GET, RequestMethod.POST })
    public ModelAndView account_proc(HttpServletRequest request, HttpServletResponse response, @RequestParam Map<String, Object> param,
                                     ModelAndView modelView, @AuthenticationPrincipal PrincipalDetails authUser) {

        // 사용자 등록
        userService.insertUser(param);

        modelView.setViewName("/web/user/account");
        return modelView;
    }

    // 나의 정보 관리
    @RequestMapping(value = "/myData", method = { RequestMethod.GET, RequestMethod.POST })
    public ModelAndView myDataView(Model model, HttpServletRequest request, HttpServletResponse response, @RequestParam Map<String, Object> param,
                                   ModelAndView modelView, @AuthenticationPrincipal PrincipalDetails authUser) {

        modelView.setViewName("/web/user/myData");
        return modelView;
    }

}
