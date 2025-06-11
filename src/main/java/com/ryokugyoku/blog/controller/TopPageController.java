package com.ryokugyoku.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TopPageController {

    @GetMapping("/")
    public String index() {
        return "index";
    }
}
