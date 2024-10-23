package com.hk.transportProject.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.hk.transportProject.model.LoginResponse;
import com.hk.transportProject.model.User;
import com.hk.transportProject.repository.AuthRepository;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class LoginViewModel extends ViewModel {

    private final AuthRepository authRepository;
    private final MutableLiveData<LoginResponse> loginResponse = new MutableLiveData<>();

    @Inject
    public LoginViewModel(AuthRepository authRepository) {
        this.authRepository = authRepository;
    }

    public LiveData<LoginResponse> getLoginResponse(){
        return loginResponse;
    }

    //public LiveData<LoginResponse> login(String id, String password) {
    //    return authRepository.login(id, password);
    //}

    public void login(String username, String password){
        authRepository.login(new User(username, password)).observeForever(response ->{
            loginResponse.setValue(response);
        });
    }
}
