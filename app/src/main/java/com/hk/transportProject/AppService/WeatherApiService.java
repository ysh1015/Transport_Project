package com.hk.transportProject.AppService;

import com.hk.transportProject.Model.WeatherResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WeatherApiService {
    @GET("weather-info")
    Call<WeatherResponse> getWeatherInfo(@Query("location") String location);
}

