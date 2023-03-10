package com.example.svg_project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ManagementController {

    @RequestMapping(value = "/managements", method = RequestMethod.GET)
    public ModelAndView managementsPage() {
        ModelAndView mav = new ModelAndView("moves");
        return mav;
    }
}