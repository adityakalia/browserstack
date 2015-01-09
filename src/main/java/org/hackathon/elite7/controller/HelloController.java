package org.hackathon.elite7.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
public class HelloController {

    @RequestMapping("/node/register")
    public String index() {
        return "Greetings from Spring Boot!";
    }

}