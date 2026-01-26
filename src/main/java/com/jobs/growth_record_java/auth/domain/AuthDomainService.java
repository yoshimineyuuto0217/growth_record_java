package com.jobs.growth_record_java.auth.domain;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jobs.growth_record_java.auth.repository.UserRepository;

@Service
@Transactional
public class AuthDomainService {

    // リポジトリ層の呼び出して渡せるように
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthDomainService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }
    public void register(String email, String password, String name){
    
    // メール必須チェック
    if (email == null || email.isEmpty() ){
        throw new IllegalArgumentException("メールは必須です");
    }
    // メール重複チェック
    if (userRepository.existsByEmail(email)) {
        throw new IllegalArgumentException("すでに登録されています");
    }
    // バリデーション処理
    String emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
    if (!Pattern.matches(emailRegex, email)) {
        throw new IllegalArgumentException("無効なメールアドレスです");
    }
    // パスワードのハッシュ化
    String hashPassword = passwordEncoder.encode(password);
    // ここでインスタンス化して状態保持をEntityのprotected User で保持してる
    User user = new User(email,name,hashPassword);
    // ここでリポジトリそうに投げてる
    userRepository.save(user);
}}
