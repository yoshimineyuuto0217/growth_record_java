package com.jobs.growth_record_java.user.domain;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.jobs.growth_record_java.constant.ErrorMessage;
import com.jobs.growth_record_java.domain.model.User;
import com.jobs.growth_record_java.exception.BadRequestException;
import com.jobs.growth_record_java.exception.InternalServerErrorException;
import com.jobs.growth_record_java.exception.NotFoundException;
import com.jobs.growth_record_java.user.dto.MyProfileImageResponse;
import com.jobs.growth_record_java.user.dto.MyprofileRequest;
import com.jobs.growth_record_java.user.dto.MyprofileResponse;
import com.jobs.growth_record_java.user.repository.MyProfileRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class MyProfileDomainService {

    private final MyProfileRepository myProfileRepository;
    private final CurrentUserService currentUserService;

    public MyProfileDomainService(MyProfileRepository myProfileRepository,CurrentUserService currentUserService){
        this.myProfileRepository = myProfileRepository;
        this.currentUserService = currentUserService;
    }

    // ログイン中ユーザーのプロフィール取得
    public MyprofileResponse me(){

    User user = currentUserService.getCurrentUser();

    String imageUrl = null;

    if (user.getProfile_image() != null) {imageUrl = "http://host.docker.internal:8080/images/" + user.getProfile_image();
    }

    return new MyprofileResponse(user.getName(),imageUrl,user.getSelf_introduction());
    }

    // ログイン中ユーザーの情報を更新
    public MyprofileResponse patchMe(MyprofileRequest request){

        User user = currentUserService.getCurrentUser();

        String profileImagePath = null;

        user.updateProfile(request.getName(), profileImagePath, request.getSelf_introduction());

        return new MyprofileResponse(user.getName(),user.getProfile_image(),user.getSelf_introduction());
    }

    // ログイン中ユーザーの画像を更新
    public MyProfileImageResponse postProfileImage(MultipartFile file) {

    try {
        String contentType = file.getContentType();

        if (contentType == null || !(contentType.equals("image/jpeg") ||contentType.equals("image/png") )) {
            throw new BadRequestException(ErrorMessage.IMAGE_TYPE_INVALID.getMessage());
        }
        
        User user = currentUserService.getCurrentUser();
        String originalName = file.getOriginalFilename();
        String extension = "";

        if (originalName != null && originalName.contains(".")) { extension = originalName.substring(originalName.lastIndexOf(".")); }

        String safeFileName = UUID.randomUUID() + extension;

        // 保存ディレクトリ作成
        Path uploadDir = Paths.get("upload/images/");

        if (!Files.exists(uploadDir)) { Files.createDirectories(uploadDir); }

        // ファイル保存
        Path savePath = uploadDir.resolve(safeFileName);
        Files.copy( file.getInputStream(), savePath, StandardCopyOption.REPLACE_EXISTING);

        //  DB更新
        user.updateProfile(null, safeFileName, null);
        myProfileRepository.save(user);

        String imageUrl = "http://host.docker.internal:8080/images/" + safeFileName;

        return new MyProfileImageResponse(imageUrl);

    } catch (IOException e) {
        throw new InternalServerErrorException(ErrorMessage.IMAGE_SAVE_FAILED.getMessage());
    }
}

    // 画像の更新後に再取得
    public Resource getImage(String fileName) {

        try {
            Path path = Paths.get("upload/images/").resolve(fileName);

            if (!Files.exists(path)) { throw new NotFoundException(ErrorMessage.IMAGE_NOT_FOUND.getMessage()); }

            return new UrlResource(path.toUri());

            } catch (IOException e) {
            throw new InternalServerErrorException(ErrorMessage.IMAGE_FETCH_FAILED.getMessage());
            }
    }
}