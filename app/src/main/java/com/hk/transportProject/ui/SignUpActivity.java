package com.hk.transportProject.ui;

import android.os.Bundle;
import android.widget.Toast;

import androidx.lifecycle.ViewModelProvider;
import androidx.appcompat.app.AppCompatActivity;

import com.hk.transportProject.databinding.ActivitySignUpBinding;
import com.hk.transportProject.util.InputValidator;
import com.hk.transportProject.viewmodel.SignUpViewModel;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class SignUpActivity extends AppCompatActivity {

    private ActivitySignUpBinding binding;
    private SignUpViewModel signUpViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        binding = ActivitySignUpBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnSignUp.setOnClickListener(v -> {
            String userId = binding.editTextUserId.getText().toString();
            String email = binding.editTextEmail.getText().toString();
            String password = binding.editTextPassword.getText().toString();
            String confirmPassword = binding.editTextConfirmPassword.getText().toString();

            // 입력 검증
            if (!InputValidator.isValidEmail(email)) {
                Toast.makeText(this, "올바른 이메일을 입력하세요.", Toast.LENGTH_SHORT).show();
                return;
            }
            if (!InputValidator.isValidPassword(password)) {
                Toast.makeText(this, "비밀번호는 6자 이상이어야 합니다.", Toast.LENGTH_SHORT).show();
                return;
            }
            if (!InputValidator.doPasswordsMatch(password, confirmPassword)) {
                Toast.makeText(this, "비밀번호가 일치하지 않습니다.", Toast.LENGTH_SHORT).show();
                return;
            }
            if (email.isEmpty() || userId.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "모든 필드를 입력하세요.", Toast.LENGTH_SHORT).show();
                return;
            }

            // 회원가입 요청
            signUpViewModel.signUp(userId, password, email).observe(this, success -> {
                if (success) {
                    Toast.makeText(this, "회원가입 성공", Toast.LENGTH_SHORT).show();
                    finish();  // 회원가입 후 로그인 화면으로 돌아감
                } else {
                    Toast.makeText(this, "회원가입 실패", Toast.LENGTH_SHORT).show();
                }
            });
        });

        binding.btnGoToLogin.setOnClickListener(v ->{
            finish();
        });
    }
}
