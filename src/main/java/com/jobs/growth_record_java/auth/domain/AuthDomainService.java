package com.jobs.growth_record_java.auth.domain;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jobs.growth_record_java.auth.domain.model.User;
import com.jobs.growth_record_java.auth.repository.UserRepository;
import com.jobs.growth_record_java.constant.ErrorMessage;

@Service
@Transactional
public class AuthDomainService {

    // リポジトリ層の呼び出して渡せるように
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthDomainService(UserRepository userRepository, PasswordEncoder passwordEncoder ) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // 新規登録
    public void register(String email, String password, String name) {

    Map<String, String> errors = new HashMap<>();

    // 名前チェック
        if (name == null || name.isEmpty()) {
        errors.put("name",ErrorMessage.NAME_REQUIRED.getMessage());
    }
    // メール必須
    if (email == null || email.isEmpty()) {
        errors.put("email",ErrorMessage.EMAIL_REQUIRED.getMessage());
    }

    // メール形式
    if (email != null && !email.isEmpty()) {
        String emailRegex ="^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        if (!Pattern.matches(emailRegex, email)) {
            errors.put("email",ErrorMessage.EMAIL_INVALID.getMessage());
        }
    }

    // メール重複
    if (email != null && userRepository.existsByEmail(email)) {
        errors.put("email",ErrorMessage.EMAIL_ALREADY_EXISTS.getMessage());
    }

    // パスワード必須
    if (password == null || password.isEmpty()) {
        errors.put("password",ErrorMessage.PASSWORD_REQUIRED.getMessage());
    }

    if (!errors.isEmpty()) {
        throw new ValidationException(errors);
    }

    String hashPassword = passwordEncoder.encode(password);
    User user = new User(email, name, hashPassword);
    userRepository.save(user);
}
    
public void login(String email, String password) {

    Map<String, String> errors = new HashMap<>();

    // メール必須
    if (email == null || email.isBlank()) {
        errors.put("email", ErrorMessage.EMAIL_REQUIRED.getMessage());
    }

    // パスワード必須
    if (password == null || password.isBlank()) {
        errors.put("password", ErrorMessage.PASSWORD_REQUIRED.getMessage());
    }

    // 必須エラーがあれば即終了
    if (!errors.isEmpty()) {
        throw new ValidationException(errors);
    }

    // ユーザー取得
    User user = userRepository.findByEmail(email)
        .orElseThrow(() -> new ValidationException(
            Map.of("email", ErrorMessage.EMAIL_NOT_FOUND.getMessage())
        ));

    // パスワード照合
    if (!passwordEncoder.matches(password, user.getPassword())) {
        throw new ValidationException(
            Map.of("password", ErrorMessage.PASSWORD_INVALID.getMessage())
        );
    }
}

}