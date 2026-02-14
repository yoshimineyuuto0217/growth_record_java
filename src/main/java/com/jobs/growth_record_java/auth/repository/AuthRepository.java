package com.jobs.growth_record_java.auth.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jobs.growth_record_java.domain.model.User;


@Repository
// JpaRepositoryはsave・updateなどを使用できるDI
public interface AuthRepository extends JpaRepository<User, Long> {
    // 処理の中身を書かなくても走る理由は裏側でSQLを作成してくれてる
    // 注意点は名前の付け方
    // 動詞 + By + 条件で命名することが大事
    boolean existsByEmail(String email);

    // ユーザー検索
    Optional<User> findByEmail(String email);
}
