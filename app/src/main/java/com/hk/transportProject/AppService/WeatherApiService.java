package com.hk.transportProject.AppService;

import com.hk.transportProject.Data.Weather.WeatherData;
import com.hk.transportProject.Model.WeatherResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WeatherApiService {
    @GET("https://apis.data.go.kr/1360000/VilageFcstInfoService_2.0" +
            "/getUltraSrtNcst?serviceKey=g3s%2FsU96JysexYpblDXIc4%2BV33peeadeoSi2BpBF5ej8XHRQtmPphiSA4dkF3s7b0CF5gDDO6%2FN2%2FweFSIDgCA%3D%3D&" +
            "pageNo=1&numOfRows=1000&dataType=JSON&base_date=20241015&base_time=0600&nx=55&ny=127")
    Call<WeatherData> getWeatherInfo(
            @Query("serviceKey") String serviceKey,
            @Query("numOfRows") int numOfRows,
            @Query("base_date") int base_date,
            @Query("nx") int nx,
            @Query("ny") int ny

    );
}

