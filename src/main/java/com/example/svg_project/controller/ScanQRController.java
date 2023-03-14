package com.example.svg_project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ScanQRController {

    @RequestMapping(value = "/scan-qr", method = RequestMethod.GET)
    public ModelAndView scanQRPage() {
        ModelAndView mav = new ModelAndView("scan-qr");
        return mav;
    }
}