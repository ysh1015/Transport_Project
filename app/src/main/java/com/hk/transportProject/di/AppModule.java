package com.hk.transportProject.di;

import com.hk.transportProject.network.AuthService;
import com.hk.transportProject.network.RetrofitClient;
import com.hk.transportProject.repository.AuthRepository;

import javax.inject.Singleton;

import dagger.Module;
import dagger.hilt.InstallIn;
import dagger.Provides;
import dagger.hilt.components.SingletonComponent;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
@InstallIn(SingletonComponent.class)
public class AppModule {

    @Singleton
    @Provides
    public Retrofit provideRetrofit() {
        return RetrofitClient.getRetrofitInstance();
    }

    @Singleton
    @Provides
    public AuthService provideApiService(Retrofit retrofit) {
        return retrofit.create(AuthService.class);
    }

    @Singleton
    public static OkHttpClient provideOkHttpClient() {
        return new OkHttpClient.Builder().build();  // 필요 시 추가 설정 가능
    }

}
