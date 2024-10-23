package com.hk.transportProject;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import com.hk.transportProject.model.LoginResponse;
import com.hk.transportProject.repository.AuthRepository;
import com.hk.transportProject.viewmodel.LoginViewModel;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

public class LoginViewModelTest {

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    private LoginViewModel loginViewModel;
    private AuthRepository authRepository;

    @Before
    public void setUp(){
        authRepository = mock(AuthRepository.class);
        loginViewModel = new LoginViewModel(authRepository);
    }

    @Test
    public void testLoginSuccess(){

        //given
        MutableLiveData<LoginResponse> liveData = new MutableLiveData<>();
        LoginResponse expectedResponse = new LoginResponse(true, "로그인 성공");
        liveData.setValue(expectedResponse);
        when(authRepository.login("testId", "testPassword")).thenReturn(liveData);

        //when
        Observer<LoginResponse> observer = mock(Observer.class);
        loginViewModel.login("testId", "testPassword").observeForever(observer);

        //then
        // ArgumentCaptor로 실제 전달된 인자 캡처
        ArgumentCaptor<LoginResponse> captor = ArgumentCaptor.forClass(LoginResponse.class);
        Mockito.verify(observer).onChanged(captor.capture());

        // 캡처한 인자 값 확인
        LoginResponse actualResponse = captor.getValue();
        assert actualResponse.isSuccess();  // 성공 여부 확인
        assert actualResponse.getMessage().equals("로그인 성공");
    }

    @Test
    public void testLoginFailure() {
        // 준비: Mock된 실패 데이터
        MutableLiveData<LoginResponse> liveData = new MutableLiveData<>();
        LoginResponse expectedResponse = new LoginResponse(false, "로그인 실패");
        liveData.setValue(expectedResponse);

        when(authRepository.login("testId", "wrongPassword")).thenReturn(liveData);

        // Observer를 등록
        Observer<LoginResponse> observer = mock(Observer.class);
        loginViewModel.login("testId", "wrongPassword").observeForever(observer);

        ArgumentCaptor<LoginResponse> captor = ArgumentCaptor.forClass(LoginResponse.class);
        Mockito.verify(observer).onChanged(captor.capture());

        // 캡처한 인자 값 확인
        LoginResponse actualResponse = captor.getValue();
        assert !actualResponse.isSuccess();  // 실패 여부 확인
        assert actualResponse.getMessage().equals("로그인 실패");
    }
}
