package com.jobs.growth_record_java.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

//初期設定
@RestController
public class InitialController {

    @GetMapping("/")
    public String hello() {
        return "変";
    }
}

