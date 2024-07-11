package com.example.ecorecicla;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Inflar el layout de user_register.xml
        View userRegisterView = getLayoutInflater().inflate(R.layout.user_register, null, false);

        // Encontrar el botón registerButton en user_register.xml
        Button registerButton = userRegisterView.findViewById(R.id.registerButton);

        // Configurar OnClickListener para el botón
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Iniciar ActivityLogin al hacer clic en registerButton
                Intent intent = new Intent(LoginActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}
