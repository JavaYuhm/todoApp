package com.todoapp;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {

    @GetMapping("/vue")
    public String vueHello(){
        return "vue/index";
    }
}
