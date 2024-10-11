package com.hk.transportProject.model;

// 서버 응답을 받아 getter로 저장
public class LoginResponse {
    private boolean success;
    private String message;

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }
}
