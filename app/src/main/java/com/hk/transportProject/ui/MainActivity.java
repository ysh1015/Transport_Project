package com.hk.transportProject.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.hk.transportProject.R;
import com.hk.transportProject.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);  // activity_main.xml을 레이아웃으로 설정

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
    }
}