package com.hk.transportProject.Model;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.hk.transportProject.Repository.CulturalHeritageRepository;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/*
public class CulturalHeritageViewModel extends ViewModel {
    private final MutableLiveData<List<JsonObject>> heritageList = new MutableLiveData<>();
    private final CulturalHeritageRepository repository;

    public CulturalHeritageViewModel() {
        repository = new CulturalHeritageRepository();
    }

    public LiveData<List<JsonObject>> getHeritageList() {
        return heritageList;
    }

    public void fetchNearbyHeritageData(double latitude, double longitude, int radius) {
        repository.fetchHeritageData(latitude, longitude, radius, new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                if (response.isSuccessful() && response.body() != null) {
                    // Cultural heritage data processing
                    List<JsonObject> heritageData = parseHeritageData(response.body());
                    heritageList.setValue(heritageData);
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                Log.e("ViewModel", "Failed to fetch heritage data", t);
            }
        });
    }

    private List<JsonObject> parseHeritageData(JsonObject response) {
        List<JsonObject> heritageList = new ArrayList<>();
        JsonArray dataArray = response.getAsJsonArray("data");

        if (dataArray != null) {
            for (JsonElement element : dataArray) {
                heritageList.add(element.getAsJsonObject());
            }
        }
        return heritageList;
    }
}

*/