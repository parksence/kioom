package com.codex.kioom.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class HomeController {

    @RequestMapping(value = "/", method = { RequestMethod.GET, RequestMethod.POST })
    public ModelAndView home(ModelAndView modelView, Model model) throws Exception {

        modelView.setViewName("/web/user/dashboard");

        return modelView;
    }

    @RequestMapping(value = "/layout", method = { RequestMethod.GET, RequestMethod.POST })
    public ModelAndView layout(ModelAndView modelView, Model model) throws Exception {

        modelView.setViewName("/layout");

        return modelView;
    }

    @RequestMapping(value = "/siteMap", method = { RequestMethod.GET, RequestMethod.POST })
    public ModelAndView siteMap(ModelAndView modelView, Model model) throws Exception {

        modelView.setViewName("/siteMap");

        return modelView;
    }

    @RequestMapping(value = "/login", method = { RequestMethod.GET, RequestMethod.POST })
    public ModelAndView login(ModelAndView modelView, Model model) throws Exception {

        modelView.setViewName("/login");

        return modelView;
    }

    @RequestMapping(value = "/user/account", method = { RequestMethod.GET, RequestMethod.POST })
    public ModelAndView account(ModelAndView modelView, Model model) throws Exception {

        modelView.setViewName("/web/user/account");

        return modelView;
    }

    @RequestMapping(value = "/user/patient", method = { RequestMethod.GET, RequestMethod.POST })
    public ModelAndView patient(ModelAndView modelView, Model model) throws Exception {

        modelView.setViewName("/web/user/patient");

        return modelView;
    }

    @RequestMapping(value = "/user/hospital", method = { RequestMethod.GET, RequestMethod.POST })
    public ModelAndView hospital(ModelAndView modelView, Model model) throws Exception {

        modelView.setViewName("/web/user/hospital");

        return modelView;
    }

}
