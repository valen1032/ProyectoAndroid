package com.example.ecorecicla;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ForgotPasswordActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        EditText emailEditText = findViewById(R.id.email);
        Button recoverPasswordButton = findViewById(R.id.recoverPasswordButton);
        Button loginButton = findViewById(R.id.loginButton);

        recoverPasswordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailEditText.getText().toString().trim();
                if (email.isEmpty()) {
                    Toast.makeText(ForgotPasswordActivity.this, "Por favor ingrese su correo electrónico", Toast.LENGTH_SHORT).show();
                } else {
                    // Aquí puedes añadir la lógica para recuperar la contraseña
                    // Por ejemplo, enviar una solicitud al servidor
                    Toast.makeText(ForgotPasswordActivity.this, "Se ha enviado un enlace de recuperación a su correo electrónico", Toast.LENGTH_SHORT).show();
                }
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Redirigir a LoginActivity
                Intent intent = new Intent(ForgotPasswordActivity.this, LoginActivity.class);
                startActivity(intent);
                finish(); // Opcional: Cierra la actividad actual para que no se quede en el stack de actividades
            }
        });
    }
}
