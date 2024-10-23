package com.hk.transportProject;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.Observer;

import com.hk.transportProject.model.LoginResponse;
import com.hk.transportProject.network.AuthService;
import com.hk.transportProject.repository.AuthRepository;

import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class AuthRepositoryTest {

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    private MockWebServer mockWebServer;
    private AuthService authService;
    private AuthRepository authRepository;

    @Before
    public void setUp() throws Exception {
        mockWebServer = new MockWebServer();
        mockWebServer.start();

        // OkHttpClient 생성 (필요할 경우 추가 설정 가능)
        OkHttpClient okHttpClient = new OkHttpClient.Builder().build();

        // Retrofit 생성
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(mockWebServer.url("/"))  // MockWebServer URL 사용
                .client(okHttpClient)  // OkHttp 클라이언트 연결
                .addConverterFactory(GsonConverterFactory.create())  // Gson 변환기 추가
                .build();

        authService = retrofit.create(AuthService.class);  // Retrofit 인터페이스 구현
        authRepository = new AuthRepository(authService);  // Repository 생성
    }

    @After
    public void tearDown() throws Exception {
        mockWebServer.shutdown();  // MockWebServer 종료
    }

    @Test
    public void testLoginSuccess() throws Exception {
        // Mock된 서버 응답 (로그인 성공)
        MockResponse mockResponse = new MockResponse()
                .setBody("{\"success\":true,\"message\":\"로그인 성공\"}")
                .setResponseCode(200);
        mockWebServer.enqueue(mockResponse);

        // Observer 등록
        Observer<LoginResponse> observer = mock(Observer.class);
        authRepository.login("testId", "testPassword").observeForever(observer);

        // Observer에 대한 검증
        verify(observer).onChanged(new LoginResponse(true, "로그인 성공"));
    }

    @Test
    public void testLoginFailure() throws Exception {
        // Mock된 서버 응답 (로그인 실패)
        MockResponse mockResponse = new MockResponse()
                .setBody("{\"success\":false,\"message\":\"로그인 실패\"}")
                .setResponseCode(401);
        mockWebServer.enqueue(mockResponse);

        // Observer 등록
        Observer<LoginResponse> observer = mock(Observer.class);
        authRepository.login("testId", "wrongPassword").observeForever(observer);

        // Observer에 대한 검증
        verify(observer).onChanged(new LoginResponse(false, "로그인 실패"));
    }
}
