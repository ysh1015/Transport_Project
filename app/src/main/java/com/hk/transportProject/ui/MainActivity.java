package com.hk.transportProject.ui;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.hk.transportProject.R;
import com.hk.transportProject.databinding.ActivityMainBinding;
import com.hk.transportProject.network.WeatherApiService;
import com.hk.transportProject.network.WeatherClient;
import com.hk.transportProject.repository.WeatherResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private TextView weatherTextView;
    private Button checkWeatherButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);  // activity_main.xml을 레이아웃으로 설정

        weatherTextView = findViewById(R.id.weatherTextView);
        checkWeatherButton = findViewById(R.id.checkWeatherButton);

        checkWeatherButton.setOnClickListener(v -> fetchWeatherData());




        /*
        // findViewById로 버튼을 찾고, 클릭 리스너를 설정
        Button buttonGoToLogin = findViewById(R.id.buttonGoToLogin);

        buttonGoToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // LoginActivity로 이동하는 Intent
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);  // LoginActivity 실행
            }
        });

        */
    }

    public void fetchWeatherData(){
        WeatherApiService apiService = WeatherClient.getClient().create(WeatherApiService.class);

        // 동적 base_time 설정
        String baseDate = "20241119"; // 예: 오늘 날짜
        String baseTime = "1700";     // 예: 05시 기준
        int nx = 60;                  // 예: 서울 X 좌표
        int ny = 127;                 // 예: 서울 Y 좌표

        Call<WeatherResponse> call = apiService.getWeather(
                "xZVyEXB5g2v3CEyT4QnoWdABt2ZHH4jLNuEHT2R7ivPWW6oBFrBUDOdw6mAt9dohXYqIpHc5SPlF5pmgRZ2dFg==",
                10,
                1,
                "JSON",
                baseDate,
                baseTime,
                nx,
                ny
        );

        call.enqueue(new Callback<WeatherResponse>() {
            @Override
            public void onResponse(Call<WeatherResponse> call, Response<WeatherResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    WeatherResponse weatherResponse = response.body();

                    if ("00".equals(weatherResponse.response.header.resultCode)) {
                        StringBuilder weatherData = new StringBuilder();
                        for (WeatherResponse.Response.Body.Items.Item item :
                                weatherResponse.response.body.items.item) {
                            String description = getDescription(item.category, item.fcstValue);
                            weatherData.append(description).append("\n");
                        }
                        weatherTextView.setText(weatherData.toString());
                    } else {
                        weatherTextView.setText("API Error: " + weatherResponse.response.header.resultMsg);
                    }
                } else {
                    weatherTextView.setText("HTTP Error: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<WeatherResponse> call, Throwable t) {
                weatherTextView.setText("Network Error: " + t.getMessage());
                Log.e("WeatherAPI", "Error: " + t.getMessage());
            }
        });
    }
    private String getDescription(String category, String value) {
        switch (category) {
            case "TMP":
                return "기온: " + value + "℃";
            case "UUU":
                return "동서 바람 속도: " + value + " m/s";
            case "VVV":
                return "남북 바람 속도: " + value + " m/s";
            case "VEC":
                return "풍향: " + value + "°";
            case "WSD":
                return "풍속: " + value + " m/s";
            case "SKY":
                return "하늘 상태: " + getSkyDescription(value);
            case "PTY":
                return "강수 형태: " + getPtyDescription(value);
            case "POP":
                return "강수 확률: " + value + "%";
            case "WAV":
                return "파고: " + value + " m";
            case "PCP":
                return "강수량: " + (value.equals("강수없음") ? value : value + " mm");
            default:
                return category + ": " + value;
        }
    }

    private String getSkyDescription(String value) {
        switch (value) {
            case "1":
                return "맑음";
            case "3":
                return "구름 많음";
            case "4":
                return "흐림";
            default:
                return "알 수 없음";
        }
    }

    private String getPtyDescription(String value) {
        switch (value) {
            case "0":
                return "없음";
            case "1":
                return "비";
            case "2":
                return "비/눈";
            case "3":
                return "눈";
            case "4":
                return "소나기";
            default:
                return "알 수 없음";
        }
    }
}