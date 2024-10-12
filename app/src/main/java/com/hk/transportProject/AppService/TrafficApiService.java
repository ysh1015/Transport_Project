package com.hk.transportProject.AppService;

import com.hk.transportProject.Model.TrafficResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface TrafficApiService {
    @GET("traffic-info")
    Call<TrafficResponse> getTrafficInfo(@Query("location") String location);
}

