package com.jobs.growth_record_java.auth.application;

import org.springframework.stereotype.Service;

import com.jobs.growth_record_java.auth.domain.AuthDomainService;
import com.jobs.growth_record_java.auth.dto.LoginRequest;
import com.jobs.growth_record_java.auth.dto.LoginResponse;
import com.jobs.growth_record_java.auth.dto.RegisterRequest;
import com.jobs.growth_record_java.auth.dto.RegisterResponse;

// 処理の流れを組み立てる所
// Domain層を呼ぶ
// 処理は書かない
@Service
public class AuthApplicationService {

    //Domain層の呼び出して渡せるように
    private final AuthDomainService authDomainService;

    public AuthApplicationService(AuthDomainService authDomainService) {
        this.authDomainService = authDomainService;
    }
    // 新規登録処理
    public RegisterResponse register(RegisterRequest request){
        String token = authDomainService.register(request.getEmail(), request.getPassword(), request.getName());
        // レスポンスの型は文字列のmessageをしてしたからこれで良い
        return new RegisterResponse("登録成功",token);
    }

    // ログイン処理
    public LoginResponse login(LoginRequest request){
        String token = authDomainService.login(request.getEmail(),request.getPassword());
        return new LoginResponse("ログイン成功",token);
    }
}
