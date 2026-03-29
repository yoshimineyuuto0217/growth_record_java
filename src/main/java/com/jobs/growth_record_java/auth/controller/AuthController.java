package com.jobs.growth_record_java.auth.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jobs.growth_record_java.auth.application.AuthApplicationService;
import com.jobs.growth_record_java.auth.dto.LoginRequest;
import com.jobs.growth_record_java.auth.dto.LoginResponse;
import com.jobs.growth_record_java.auth.dto.RegisterRequest;
import com.jobs.growth_record_java.auth.dto.RegisterResponse;

// HTTPのメソッド受け取るだけの層
// DTOフォルダから型を受け取る
// Applicationに処理を渡す
// レイヤードアーキテクチャのプレゼンテーション層（窓口役）
@RestController
@RequestMapping("/api/auth")
public class  AuthController {
    // ここでアプリケーションを読んで渡せるように
    private final AuthApplicationService authApplicationService;
    
    public AuthController(AuthApplicationService authApplicationService){
        this.authApplicationService = authApplicationService;
    }
    // 新規登録
    @PostMapping("/register")
    public RegisterResponse register(@RequestBody RegisterRequest request){
        return authApplicationService.register(request);
    }

    // ログイン
    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest request){
    return authApplicationService.login(request);
    }
}
