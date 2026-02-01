package com.jobs.growth_record_java.auth.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

// LaravelでいうModelみたいなもの
// 他のテーブルとの関係もここに記載
@Entity
@Table(name = "users")
public class User {

    @Id //主キーの意味
    @GeneratedValue(strategy = GenerationType.IDENTITY) //IDを自動再番しますの意味
    // 以下4つは使われてないように見えるがJPAが使用してるから必須
    private Long id;

    private String email;
    private String password;
    private String name;

    // protectedは他のパッケージでは呼び出せない
    protected User() {
        // JPA用だから何も書かなくて正解
    }

    public User(String email, String name, String hashPassword) {
        this.email = email;
        this.password = hashPassword;
        this.name = name;
    }
    // パスワード照合用に必要
    public String getPassword() {
    return password;
}
}
