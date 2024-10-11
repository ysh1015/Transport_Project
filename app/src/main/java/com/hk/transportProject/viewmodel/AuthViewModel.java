package com.hk.transportProject.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.hk.transportProject.model.User;
import com.hk.transportProject.model.LoginResponse;
import com.hk.transportProject.repository.AuthRepository;

public class AuthViewModel extends ViewModel {

    private AuthRepository authRepository;

    public AuthViewModel() {
        authRepository = new AuthRepository();
    }

    public LiveData<LoginResponse> login(String userId, String password) {
        User user = new User(userId, password);
        return authRepository.login(user);
    }
}
