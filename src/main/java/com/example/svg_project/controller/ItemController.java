package com.example.svg_project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ItemController {

    @RequestMapping(value = "/items", method = RequestMethod.GET)
    public ModelAndView itemsPage() {
        ModelAndView mav = new ModelAndView("items");
        return mav;
    }
}