package com.example.gotrip; // Change to your actual package name

import android.os.Bundle;
import android.text.InputType;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;

public class MainActivity extends AppCompatActivity {

    EditText usernameEditText, passwordEditText, emailEditText;
    CheckBox agreeCheckBox;
    MaterialButton registerButton, loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Make the activity fullscreen
        //Enable full-screen mode
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main); // Ensure this matches your XML layout file

        // Bind UI elements to variables
        usernameEditText = findViewById(R.id.username);
        passwordEditText = findViewById(R.id.password);
        emailEditText = findViewById(R.id.email);
        agreeCheckBox = findViewById(R.id.agreeCheckBox);
        registerButton = findViewById(R.id.RegisterButton);
        loginButton = findViewById(R.id.LoginButton);

        // Register Button Click
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleRegister();
            }
        });

        // Login Button Click
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // For now, just show a toast
                Toast.makeText(MainActivity.this, "Redirecting to Login...", Toast.LENGTH_SHORT).show();
            }
        });

        // Add a touch listener to the password EditText to detect clicks on the eye icon
        passwordEditText.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int drawableRight = 2; // The index for drawableEnd (right side icon)

                if (event.getAction() == MotionEvent.ACTION_UP) {
                    // Check if the user clicked on the eye icon (drawableEnd)
                    if (event.getRawX() >= (passwordEditText.getRight() - passwordEditText.getCompoundDrawables()[drawableRight].getBounds().width())) {
                        togglePasswordVisibility();
                        return true;
                    }
                }
                return false;
            }
        });
    }

    private boolean isPasswordVisible = false;

    private void togglePasswordVisibility() {
        if (isPasswordVisible) {
            // Hide password
            passwordEditText.setInputType(android.text.InputType.TYPE_CLASS_TEXT |
                    android.text.InputType.TYPE_TEXT_VARIATION_PASSWORD);
            passwordEditText.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, R.drawable.eye_slash_svgrepo_com, 0);
        } else {
            // Show password
            passwordEditText.setInputType(android.text.InputType.TYPE_CLASS_TEXT |
                    android.text.InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
            passwordEditText.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, R.drawable.eye_slash_svgrepo_com, 0);
        }

        // Keep the cursor at the end of the text
        passwordEditText.setSelection(passwordEditText.getText().length());

        // Toggle the flag
        isPasswordVisible = !isPasswordVisible;
    }

    private void handleRegister() {
        String username = usernameEditText.getText().toString().trim();
        String password = passwordEditText.getText().toString().trim();
        String email = emailEditText.getText().toString().trim();

        if (username.isEmpty() || password.isEmpty() || email.isEmpty()) {
            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!agreeCheckBox.isChecked()) {
            Toast.makeText(this, "You must agree to the Terms and Privacy Policy", Toast.LENGTH_SHORT).show();
            return;
        }

        // Success
        Toast.makeText(this, "Registered Successfully!", Toast.LENGTH_SHORT).show();

        // You can add code to actually register the user (e.g., Firebase, API, etc.)
    }
}
