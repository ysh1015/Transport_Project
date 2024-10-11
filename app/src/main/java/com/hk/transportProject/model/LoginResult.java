package com.hk.transportProject.model;

public class LoginResult {

    private LoginResponse success;
    private String error;

    public LoginResult(LoginResponse success, String error) {
        this.success = success;
        this.error = error;
    }

    public LoginResponse getSuccess(){
        return success;
    }

    public String getError(){
        return error;
    }

}
