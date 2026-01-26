package com.jobs.growth_record_java.auth.application;

import org.springframework.stereotype.Service;
import com.jobs.growth_record_java.auth.domain.AuthDomainService;
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
    public RegisterResponse register(RegisterRequest request){
        authDomainService.register(request.getEmail(),request.getPassword(),request.getName());
        return new RegisterResponse("登録成功");
    }
}
