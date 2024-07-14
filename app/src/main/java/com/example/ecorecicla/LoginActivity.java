package com.example.ecorecicla;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button irLoginButton = findViewById(R.id.loginButton);
        irLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, MenuActivity.class);
                startActivity(intent);
            }
        });

        // Encontrar el botón irRegisterButton en activity_login.xml
        Button irRegisterButton = findViewById(R.id.irRegisterButton);

        // Configurar OnClickListener para el botón
        irRegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Iniciar MainActivity al hacer clic en irRegisterButton
                Intent intent = new Intent(LoginActivity.this, UserRegisterActivity.class);
                startActivity(intent);
            }
        });

        TextView forgotPasswordLink = findViewById(R.id.forgotPasswordLink);
        forgotPasswordLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Ir a ForgotPasswordActivity
                Intent intent = new Intent(LoginActivity.this, ForgotPasswordActivity.class);
                startActivity(intent);
            }
        });
    }
}
