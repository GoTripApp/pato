package com.example.gotrip;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.MotionEvent;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;
import com.google.firebase.auth.FirebaseAuth;


import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;

public class LoginActivity extends AppCompatActivity {

    EditText usernameEditText, passwordEditText;
    MaterialButton loginButton, registerNowButton;
    private boolean isPasswordVisible = false;
    private int loginAttempts = 5;

    // Dummy credentials for testing (replace with real validation logic or Firebase)
    private final String VALID_USERNAME = "user";
    private final String VALID_PASSWORD = "pass123";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Fullscreen
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);

        usernameEditText = findViewById(R.id.login_username);
        passwordEditText = findViewById(R.id.login_password);
        loginButton = findViewById(R.id.LoginSubmitButton);
        registerNowButton = findViewById(R.id.RegisterNowButton);

        loginButton.setOnClickListener(v -> handleLogin());

        registerNowButton.setOnClickListener(v -> {
            Intent intent = new Intent(LoginActivity.this, MainActivity.class); // Redirect to Registration
            startActivity(intent);
            finish();
        });

        passwordEditText.setOnTouchListener((v, event) -> {
            int drawableEnd = 2;
            if (event.getAction() == MotionEvent.ACTION_UP) {
                if (event.getRawX() >= (passwordEditText.getRight()
                        - passwordEditText.getCompoundDrawables()[drawableEnd].getBounds().width())) {
                    togglePasswordVisibility();
                    return true;
                }
            }
            return false;
        });

        // Reset error visuals when user focuses on the fields
        usernameEditText.setOnFocusChangeListener((v, hasFocus) -> {
            if (hasFocus) {
                usernameEditText.setBackgroundResource(R.drawable.editextbg);
            }
        });

        passwordEditText.setOnFocusChangeListener((v, hasFocus) -> {
            if (hasFocus) {
                passwordEditText.setBackgroundResource(R.drawable.editextbg);
            }
        });
    }

    private void togglePasswordVisibility() {
        if (isPasswordVisible) {
            passwordEditText.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        } else {
            passwordEditText.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
        }
        passwordEditText.setSelection(passwordEditText.getText().length());
        isPasswordVisible = !isPasswordVisible;
    }

    // ✅ Updated handleLogin without shake animation
    private void handleLogin() {
        String email = usernameEditText.getText().toString().trim(); // Use email, not username
        String password = passwordEditText.getText().toString().trim();

        boolean hasError = false;

        usernameEditText.setBackgroundResource(R.drawable.editextbg);
        passwordEditText.setBackgroundResource(R.drawable.editextbg);

        if (email.isEmpty()) {
            usernameEditText.setBackgroundResource(R.drawable.error_bg);
            hasError = true;
        }

        if (password.isEmpty()) {
            passwordEditText.setBackgroundResource(R.drawable.error_bg);
            hasError = true;
        }

        if (hasError) return;

        // Firebase Authentication logic
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        Toast.makeText(LoginActivity.this, "Login successful!", Toast.LENGTH_SHORT).show();
                    } else {
                        // Login failed
                        Toast.makeText(LoginActivity.this, "Login failed: " + task.getException().getMessage(), Toast.LENGTH_LONG).show();
                        loginAttempts--;

                        if (loginAttempts <= 0) {
                            loginButton.setEnabled(false);
                        }

                        passwordEditText.setBackgroundResource(R.drawable.error_bg);
                    }
                });
    }


}
