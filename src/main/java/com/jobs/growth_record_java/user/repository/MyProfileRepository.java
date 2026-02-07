package com.jobs.growth_record_java.user.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jobs.growth_record_java.domain.model.User;


@Repository
// JpaRepositoryはsave・updateなどを使用できるDI
public interface MyProfileRepository extends JpaRepository<User, Long> {
// ユーザー検索
    Optional<User> findByEmail(String email);
}
