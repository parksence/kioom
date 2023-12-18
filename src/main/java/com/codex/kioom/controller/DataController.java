package com.codex.kioom.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class DataController {

    @RequestMapping(value = "/data/manage", method = { RequestMethod.GET, RequestMethod.POST })
    public ModelAndView data(ModelAndView modelView, Model model) throws Exception {

        modelView.setViewName("/web/data/manage");

        return modelView;
    }

}
