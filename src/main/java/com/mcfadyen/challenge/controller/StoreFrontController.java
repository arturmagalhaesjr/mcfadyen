package com.mcfadyen.challenge.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

@Controller
public class StoreFrontController {

    @GetMapping("/")
    public String indexAction (HttpSession session) {
        return "index";
    }
}
