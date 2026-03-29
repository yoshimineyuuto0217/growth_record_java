package com.jobs.growth_record_java.auth.domain;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jobs.growth_record_java.auth.repository.AuthRepository;
import com.jobs.growth_record_java.constant.ErrorMessage;
import com.jobs.growth_record_java.domain.model.User;
import com.jobs.growth_record_java.exception.ValidationException;
import com.jobs.growth_record_java.security.service.JwtService;

@Service
// このクラスに対してトランザクション当ててくれる
// リードオンリー
@Transactional
public class AuthDomainService {

    private final AuthRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AuthDomainService(AuthRepository userRepository, PasswordEncoder passwordEncoder, JwtService jwtService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    // 新規登録
    public String register(String email, String password, String name) {

    Map<String, String> errors = new HashMap<>();

    if (name == null || name.isEmpty()) {
        errors.put("name",ErrorMessage.NAME_REQUIRED.getMessage());
    }
    if (email == null || email.isEmpty()) {
        errors.put("email",ErrorMessage.EMAIL_REQUIRED.getMessage());
    }
    if (email != null && !email.isEmpty()) {
        String emailRegex ="^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        if (!Pattern.matches(emailRegex, email)) {errors.put("email",ErrorMessage.EMAIL_INVALID.getMessage());}
    }
    if (email != null && userRepository.existsByEmail(email)) {
        errors.put("email",ErrorMessage.EMAIL_ALREADY_EXISTS.getMessage());
    }
    if (password == null || password.isEmpty()) {
        errors.put("password",ErrorMessage.PASSWORD_REQUIRED.getMessage());
    }
    if (!errors.isEmpty()) {throw new ValidationException(errors);}

    String hashPassword = passwordEncoder.encode(password);
    User user = new User(email, name, hashPassword);
    userRepository.save(user);
    // token 発行（JWT）
    String token = jwtService.generateToken(user);

    return token;
}
    
    // ログイン処理
    public String login(String email, String password) {

    Map<String, String> errors = new HashMap<>();

    if (email == null || email.isBlank()) {
        errors.put("email", ErrorMessage.EMAIL_REQUIRED.getMessage());
    }
    if (password == null || password.isBlank()) {
        errors.put("password", ErrorMessage.PASSWORD_REQUIRED.getMessage());
    }
    if (!errors.isEmpty()) {throw new ValidationException(errors);}

    // ユーザー取得
    User user = userRepository.findByEmail(email)
        .orElseThrow(() -> new ValidationException(
            Map.of("email", ErrorMessage.EMAIL_NOT_FOUND.getMessage())
        ));

    // パスワード照合
    if (!passwordEncoder.matches(password, user.getPassword())) {
        throw new ValidationException(Map.of("password", ErrorMessage.PASSWORD_INVALID.getMessage()));
    }
    // token 発行（JWT）
    String token = jwtService.generateToken(user);

    return token;
}
}