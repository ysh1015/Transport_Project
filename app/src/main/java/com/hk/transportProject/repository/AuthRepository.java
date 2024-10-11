package com.hk.transportProject.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;


import com.hk.transportProject.model.User;
import com.hk.transportProject.model.LoginResponse;
import com.hk.transportProject.network.RetrofitClient;
import com.hk.transportProject.network.AuthService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AuthRepository {

    private AuthService authService;
    // 생성자 주입
    public AuthRepository() {
        authService = RetrofitClient.getRetrofitInstance().create(AuthService.class);
    }

    // 로그인 요청 User 객체 -> 서버
    public LiveData<LoginResponse> login(User user) {
        MutableLiveData<LoginResponse> loginResponse = new MutableLiveData<>();

        // Retrofit으로 로그인 요청
        authService.login(user).enqueue(new Callback<LoginResponse>() {
            // 요청 성공, 실패 처리
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    loginResponse.setValue(response.body());
                } else {
                    loginResponse.setValue(null);
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                loginResponse.setValue(null);
            }
        });

        return loginResponse;
    }
}
