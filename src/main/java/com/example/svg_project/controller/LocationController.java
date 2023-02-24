package com.example.svg_project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LocationController {

    @RequestMapping(value = "/locations", method = RequestMethod.GET)
    public ModelAndView locationsPage() {
        ModelAndView mav = new ModelAndView("locations");
        return mav;
    }
}