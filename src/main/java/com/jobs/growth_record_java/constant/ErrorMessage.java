package com.jobs.growth_record_java.constant;

public enum ErrorMessage {
    // 認証関係
    NAME_REQUIRED("名前は必須です"),
    EMAIL_REQUIRED("メールは必須です"),
    EMAIL_INVALID("無効なメールアドレスです"),
    EMAIL_ALREADY_EXISTS("すでに登録されているメールアドレスです"),
    EMAIL_NOT_FOUND("メールアドレスが存在しません"),
    PASSWORD_REQUIRED("パスワードは必須です"),
    PASSWORD_INVALID("パスワードが合致しません");

    private final String message;

    ErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
