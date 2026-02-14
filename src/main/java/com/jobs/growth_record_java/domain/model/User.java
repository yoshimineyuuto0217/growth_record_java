package com.jobs.growth_record_java.domain.model;


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
    private String profile_image;
    private String self_introduction;

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
    public String getEmail() {
        return email;
    }
    public String getName() {
        return name;
    }
    public String getProfile_image() {
        return profile_image;
    }
    public String getSelf_introduction() {
        return self_introduction;
    }

    public Long getId() {
        return id;
    }

    // ユーザー情報の更新
    public void updateProfile(
        String name,
        String profileImage,
        String selfIntroduction
    ) {
        if (name != null) {
            this.name = name;
        }
        if (profileImage != null) {
            this.profile_image = profileImage;
        }
        if (selfIntroduction != null) {
            this.self_introduction = selfIntroduction;
        }
    }
}
