package com.jobs.growth_record_java.exception;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

// アプリ全体の例外をまとめて処理するクラス
// throwされると勝手に動く
// バリデーション用の例外処理
@RestControllerAdvice
public class GlobalExceptionHandler {
    // バリデーションエラー（400）
    @ExceptionHandler(ValidationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, Object> handleValidationException(ValidationException e){
        return Map.of("errors", e.getErrors());
    }
    // NotFound(404)
    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Map<String, Object> handleNotFound(NotFoundException e){
        return Map.of("error", e.getMessage());
    }
    // サーバーエラー（500）
    @ExceptionHandler(InternalServerErrorException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Map<String, Object> handleUserNotFound(InternalServerErrorException e){
    return Map.of("error", e.getMessage());
    }
    // バットリクエスト（400)
    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, Object> handleBadRequest(BadRequestException e){
        return Map.of("error", e.getMessage());
}
}


