package com.hk.transportProject.model;

public class User {
    private String email;
    private String password;
    private String id;

    // 기본 생성자 (Hilt를 위해 필요할 수 있음)
    //public User() {}

    // 생성자
    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    // 회원가입에 필요한 추가 정보 생성자
    public User(String email, String password, String id) {
        this.email = email;
        this.password = password;
        this.id = id;

    }
    // Getters and Setters
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
