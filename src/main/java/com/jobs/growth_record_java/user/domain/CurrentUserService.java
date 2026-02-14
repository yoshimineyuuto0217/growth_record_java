package com.jobs.growth_record_java.user.domain;

import org.springframework.stereotype.Service;

import com.jobs.growth_record_java.domain.model.User;
import com.jobs.growth_record_java.user.repository.MyProfileRepository;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.jobs.growth_record_java.constant.ErrorMessage;
import com.jobs.growth_record_java.exception.NotFoundException;

@Service
public class CurrentUserService {

    private final MyProfileRepository myProfileRepository;

    public CurrentUserService(MyProfileRepository myProfileRepository) {
        this.myProfileRepository = myProfileRepository;
    }

    public User getCurrentUser() {
        Authentication auth = SecurityContextHolder
                .getContext()
                .getAuthentication();

        String email = auth.getName();

        return myProfileRepository.findByEmail(email)
                .orElseThrow(() ->
                        new NotFoundException(ErrorMessage.USER_UNDEFINED.getMessage())
                );
    }
}
