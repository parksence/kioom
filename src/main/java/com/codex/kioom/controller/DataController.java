package com.codex.kioom.controller;

import com.codex.kioom.config.security.auth.PrincipalDetails;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class DataController {

    @RequestMapping(value = "/data/manage", method = { RequestMethod.GET, RequestMethod.POST })
    public ModelAndView data(ModelAndView modelView, Model model, @AuthenticationPrincipal PrincipalDetails authUser) throws Exception {

        // 병원 이름
        modelView.addObject("h_name", authUser.getUsername().toString());

        // 권한 코드
        modelView.addObject("role_cd", authUser.getRoleCd().toString());

        modelView.setViewName("/web/data/manage");
        return modelView;
    }

}
