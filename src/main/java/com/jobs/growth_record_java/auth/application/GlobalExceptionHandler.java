// レスポンスの“形（HTTP・JSON）”を決めるのはアプリケーション層
package com.jobs.growth_record_java.auth.application;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.jobs.growth_record_java.auth.domain.ValidationException;

// アプリ全体の例外をまとめて処理するクラス
@RestControllerAdvice
public class GlobalExceptionHandler {
    // ValidationException が投げられたら、このメソッドを使う
    @ExceptionHandler(ValidationException.class)
    // レスポンスのステータスを決める
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    // handleValidationExceptionはValidationExceptionのアノテーションになってる
    public Map<String, Object> handleValidationException(
        ValidationException e
    ) {
        return Map.of(
            "success", false,
            "errors", e.getErrors()
        );
    }
}
