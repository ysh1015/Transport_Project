package com.hk.transportProject.AppService;

import com.google.gson.JsonObject;
import com.hk.transportProject.Data.Traffic.TrafficResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface TrafficApiService {
    @GET("getBusStationAroundList")
    Call<TrafficResponse> getTrafficInfo(
            @Query("serviceKey") String servicekey,     // 인증키
            @Query("x") float x,    // X좌표
            @Query("y") float y     // Y좌표
    );
}

