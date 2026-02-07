package com.jobs.growth_record_java.user.domain;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.jobs.growth_record_java.constant.ErrorMessage;
import com.jobs.growth_record_java.domain.model.User;
import com.jobs.growth_record_java.user.dto.MyprofileResponse;
import com.jobs.growth_record_java.user.repository.MyProfileRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class MyProfileDomainService {

    private final MyProfileRepository myProfileRepository;

    public MyProfileDomainService(MyProfileRepository myProfileRepository){
        this.myProfileRepository = myProfileRepository;
    }

    // ログイン中ユーザーのプロフィール取得
    public MyprofileResponse me(){

        Authentication auth = SecurityContextHolder
                .getContext()
                .getAuthentication();

        String email = auth.getName();

        User user = myProfileRepository.findByEmail(email).orElseThrow(() -> new RuntimeException(ErrorMessage.USER_UNDEFINED.getMessage()));

        return new MyprofileResponse(
            user.name(),
            user.profile_image(),
            user.self_introduction()
        );
    }
}
