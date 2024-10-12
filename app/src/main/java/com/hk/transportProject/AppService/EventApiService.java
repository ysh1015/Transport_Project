package com.hk.transportProject.AppService;

import com.hk.transportProject.Model.EventResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface EventApiService {
    @GET("event-info")
    Call<EventResponse> getEventInfo(@Query("location") String location);
}

