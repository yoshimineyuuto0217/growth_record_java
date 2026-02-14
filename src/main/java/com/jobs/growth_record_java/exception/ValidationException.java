// レスポンスの“意味のある中身（何が悪いか）”を決めるのはドメイン層

package com.jobs.growth_record_java.exception;

import java.util.Map;


public class ValidationException extends RuntimeException {

    private final Map<String, String> errors;

    public ValidationException(Map<String, String> errors) {
        this.errors = errors;
    }

    public Map<String, String> getErrors() {
        return errors;
    }
}

