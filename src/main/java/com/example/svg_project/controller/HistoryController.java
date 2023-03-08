package com.example.svg_project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HistoryController {

    @RequestMapping(value = "/history", method = RequestMethod.GET)
    public ModelAndView historyPage() {
        ModelAndView mav = new ModelAndView("history");
        return mav;
    }
}