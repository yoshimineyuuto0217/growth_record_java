package com.jobs.growth_record_java.user.domain;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.jobs.growth_record_java.constant.ErrorMessage;
import com.jobs.growth_record_java.domain.model.User;
import com.jobs.growth_record_java.user.dto.MyProfileImageResponse;
import com.jobs.growth_record_java.user.dto.MyprofileRequest;
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


            String imageUrl = null;

    if (user.getProfile_image() != null) {
        imageUrl = "http://host.docker.internal:8080/images/" 
                   + user.getProfile_image();
    }
        return new MyprofileResponse(
            user.getName(),
            imageUrl,
            user.getSelf_introduction()
        );
    }

    // ユーザの情報を更新
    public MyprofileResponse patchMe(MyprofileRequest request){

        Authentication auth = SecurityContextHolder
                .getContext()
                .getAuthentication();

        String email = auth.getName();

        User user = myProfileRepository.findByEmail(email).orElseThrow(() -> new RuntimeException(ErrorMessage.USER_UNDEFINED.getMessage()));

        String profileImagePath = null;

        user.updateProfile(request.getName(), profileImagePath, request.getSelf_introduction());

        return new MyprofileResponse(
            user.getName(),
            user.getProfile_image(),
            user.getSelf_introduction()
    );
}
    //自分の画像を更新
    public MyProfileImageResponse postProfileImage(MultipartFile file) {

    try {

        String contentType = file.getContentType();

        if (contentType == null || !(
                contentType.equals("image/jpeg") ||
                contentType.equals("image/png") 
        )) {
            throw new RuntimeException("jpg, pngのみアップロード可能");
        }

        Authentication auth = SecurityContextHolder
                .getContext()
                .getAuthentication();

        String email = auth.getName();

        User user = myProfileRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException(ErrorMessage.USER_UNDEFINED.getMessage()));

        String originalName = file.getOriginalFilename();
        String extension = "";

        if (originalName != null && originalName.contains(".")) {
            extension = originalName.substring(originalName.lastIndexOf("."));
        }

        String safeFileName = UUID.randomUUID() + extension;

        // 保存ディレクトリ作成
        Path uploadDir = Paths.get("upload/images/");
        if (!Files.exists(uploadDir)) {
            Files.createDirectories(uploadDir);
        }

        // ファイル保存
        Path savePath = uploadDir.resolve(safeFileName);
        Files.copy(
                file.getInputStream(),
                savePath,
                StandardCopyOption.REPLACE_EXISTING
        );

        //  DB更新
        user.updateProfile(null, safeFileName, null);
        myProfileRepository.save(user);

        String imageUrl = "http://host.docker.internal:8080/images/" + safeFileName;

        return new MyProfileImageResponse(imageUrl);

    } catch (IOException e) {
        throw new RuntimeException("画像保存失敗", e);
    }
}
// 画像の更新後に再取得
public Resource getImage(String fileName) {

        try {
            Path path = Paths.get("upload/images/").resolve(fileName);

            if (!Files.exists(path)) {
                throw new RuntimeException("画像が存在しません");
            }

            return new UrlResource(path.toUri());

        } catch (IOException e) {
            throw new RuntimeException("画像取得失敗", e);
        }
    }

}