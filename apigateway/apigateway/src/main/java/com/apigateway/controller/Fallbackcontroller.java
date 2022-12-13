package com.apigateway.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Fallbackcontroller {
    @GetMapping("/accountSerivceFallback")
    public String accountServiceFallback(){
        return "Account Service is down";
    }
}
