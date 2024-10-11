package com.hk.transportProject.network;

import com.hk.transportProject.model.LoginResponse;
import com.hk.transportProject.model.User;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;


public interface AuthService {

    @POST("로그인 할 곳")
    Call<LoginResponse> login(@Body User user);
}
