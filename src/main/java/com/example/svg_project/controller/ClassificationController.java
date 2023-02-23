package com.example.svg_project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class ClassificationController {

    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping(value = "/classifications", method = RequestMethod.GET)
    public ModelAndView classificationsPage() {
        ModelAndView mav = new ModelAndView("classifications");
        mav.addObject("classifications", restTemplate.getForObject("/classifications", List.class));
        return mav;
    }
}