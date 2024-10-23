package com.hk.transportProject.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;


import com.hk.transportProject.model.User;
import com.hk.transportProject.model.LoginResponse;
import com.hk.transportProject.network.RetrofitClient;
import com.hk.transportProject.network.AuthService;

import java.io.IOException;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AuthRepository {

    private final AuthService authService;

    // 생성자 DI
    @Inject
    public AuthRepository(AuthService authService) {
        this.authService = authService;
    }

    // 로그인 요청
    public LiveData<LoginResponse> login(String id, String password) {

        MutableLiveData<LoginResponse> loginResponseLiveData = new MutableLiveData<>();
        User user = new User(id, password, null);

        authService.login(user).enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    loginResponseLiveData.setValue(response.body());
                } else {
                    loginResponseLiveData.setValue(new LoginResponse(false, "로그인 실패"));
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                loginResponseLiveData.setValue(new LoginResponse(false, "네트워크 오류"));
            }
        });

        return loginResponseLiveData;
    }

    // 회원가입 요청
    public LiveData<Boolean> signUp(String id, String password, String email) {
        MutableLiveData<Boolean> resultLiveData = new MutableLiveData<>();
        User user = new User(id, password, email);

        authService.signUp(user).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                resultLiveData.setValue(response.isSuccessful());
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                resultLiveData.setValue(false);
            }
        });
        return resultLiveData;
    }
}
