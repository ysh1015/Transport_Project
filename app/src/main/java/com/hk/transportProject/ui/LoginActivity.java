package com.hk.transportProject.ui;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.hk.transportProject.R;
import com.hk.transportProject.databinding.ActivityLoginBinding;
import com.hk.transportProject.viewmodel.LoginViewModel;

import androidx.lifecycle.ViewModelProvider;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class LoginActivity extends AppCompatActivity {

    private ActivityLoginBinding binding;
    private LoginViewModel loginViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // View Binding 초기화
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // ViewModel 초기화
        loginViewModel= new ViewModelProvider(this).get(LoginViewModel.class);

        binding.btnLogin.setOnClickListener(v -> {
            String userId = binding.etUserId.getText().toString();
            String password = binding.etPassword.getText().toString();

            loginViewModel.login(userId, password).observe(this, loginResponse-> {
                if (loginResponse != null && loginResponse.isSuccess()) {
                    Toast.makeText(LoginActivity.this, "로그인 성공", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(LoginActivity.this,"아이디와 비밀번호를 다시 확인하세요.", Toast.LENGTH_SHORT).show();
                }
            });
        });
    }
}
