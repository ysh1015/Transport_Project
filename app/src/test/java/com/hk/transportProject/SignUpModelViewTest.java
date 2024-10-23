package com.hk.transportProject;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import com.hk.transportProject.repository.AuthRepository;
import com.hk.transportProject.viewmodel.SignUpViewModel;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

public class SignUpModelViewTest {

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    private SignUpViewModel signUpViewModel;
    private AuthRepository authRepository;

    @Before
    public void setUp(){
        authRepository = mock(AuthRepository.class);
        signUpViewModel = new SignUpViewModel(authRepository);
    }

    @Test
    public void testSignUpSuccess() {
        // 준비: Mock된 성공 데이터
        MutableLiveData<Boolean> liveData = new MutableLiveData<>();
        liveData.setValue(true);  // 성공 데이터 설정
        when(authRepository.signUp("testId", "testPassword", "testEmail")).thenReturn(liveData);

        // Observer를 등록
        Observer<Boolean> observer = mock(Observer.class);
        signUpViewModel.signUp("testId", "testPassword", "testEmail").observeForever(observer);

        // ArgumentCaptor로 실제 전달된 인자 캡처
        ArgumentCaptor<Boolean> captor = ArgumentCaptor.forClass(Boolean.class);
        Mockito.verify(observer).onChanged(captor.capture());

        // 캡처한 인자 값 확인
        Boolean actualResponse = captor.getValue();
        assert actualResponse;  // 성공 여부 확인
    }

    @Test
    public void testSignUpFailure() {
        // 준비: Mock된 실패 데이터
        MutableLiveData<Boolean> liveData = new MutableLiveData<>();
        liveData.setValue(false);  // 실패 데이터 설정
        when(authRepository.signUp("testId", "testPassword", "testEmail")).thenReturn(liveData);

        // Observer를 등록
        Observer<Boolean> observer = mock(Observer.class);
        signUpViewModel.signUp("testId", "testPassword", "testEmail").observeForever(observer);

        // ArgumentCaptor로 실제 전달된 인자 캡처
        ArgumentCaptor<Boolean> captor = ArgumentCaptor.forClass(Boolean.class);
        Mockito.verify(observer).onChanged(captor.capture());

        // 캡처한 인자 값 확인
        Boolean actualResponse = captor.getValue();
        assert !actualResponse;  // 실패 여부 확인
    }
}
