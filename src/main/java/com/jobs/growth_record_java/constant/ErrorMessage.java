package com.jobs.growth_record_java.constant;

public enum ErrorMessage {
    // 認証関係
    EMAIL_REQUIRED("メールは必須です"),
    EMAIL_INVALID("無効なメールアドレスです"),
    EMAIL_ALREADY_EXISTS("すでに登録されています"),
    PASSWORD_REQUIRED("パスワードは必須です");

    private final String message;

    ErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
