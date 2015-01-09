package org.hackathon.elite7.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
public class BrowserStackController {

   
    @RequestMapping("/register")
    public String register() {
        return "Greetings from Spring Boot!";
    }
    

}