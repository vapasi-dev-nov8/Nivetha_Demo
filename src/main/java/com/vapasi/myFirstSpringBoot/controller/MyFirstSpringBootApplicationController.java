package com.vapasi.myFirstSpringBoot.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/welcome")
public class MyFirstSpringBootApplicationController {

    @GetMapping("/")
    public String sayWelcome() {
        return String.format("Welcome Nithya !!");
    }
}
