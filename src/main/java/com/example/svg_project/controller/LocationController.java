package com.example.svg_project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class LocationController {

    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping(value = "/locations", method = RequestMethod.GET)
    public ModelAndView locationsPage() {
        ModelAndView mav = new ModelAndView("locations");
        mav.addObject("locations", restTemplate.getForObject("/locations", List.class));
        return mav;
    }
}