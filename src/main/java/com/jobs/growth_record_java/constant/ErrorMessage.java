package com.jobs.growth_record_java.constant;

public enum ErrorMessage {
    // 認証関係(400系)
    NAME_REQUIRED("名前は必須です"),
    EMAIL_REQUIRED("メールは必須です"),
    EMAIL_INVALID("無効なメールアドレスです"),
    EMAIL_ALREADY_EXISTS("すでに登録されているメールアドレスです"),
    EMAIL_NOT_FOUND("メールアドレスが存在しません"),
    PASSWORD_REQUIRED("パスワードは必須です"),
    PASSWORD_INVALID("パスワードが合致しません"),

    // ユーザー情報関係(400系)
    USER_UNDEFINED("ユーザーが存在しません"),
    IMAGE_NOT_FOUND("画像が存在しません"),
    IMAGE_TYPE_INVALID("jpg, pngのみアップロード可能"),

    // サーバーエラー（500)
    IMAGE_FETCH_FAILED("画像取得に失敗しました"),
    IMAGE_SAVE_FAILED("画像保存に失敗しました");
    private final String message;

    ErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
