package com.example.bubbleskhu;

import org.springframework.web.bind.annotation.GetMapping;

@org.springframework.stereotype.Controller
public class Controller {
    @GetMapping("/main")
    public String main() {
        return "index";
    }
}
