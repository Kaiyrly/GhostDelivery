package com.milestone2.app.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RouteController {
    @RequestMapping({ "/login", "/signup", "/home" })
    public String index() {
        return "forward:/";
    }
}
