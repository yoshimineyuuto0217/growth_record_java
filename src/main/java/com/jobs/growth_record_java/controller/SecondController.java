package com.jobs.growth_record_java.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

// 初回で作成したcontroller
@RestController
public class SecondController {

    @GetMapping("/second")
    public String second() {
        return "テスト";
    }
}
