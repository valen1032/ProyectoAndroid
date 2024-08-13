package com.example.ecorecicla;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class UserRegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.user_register);

        ImageButton btnback = findViewById(R.id.Buttonbackest);
        Button loginButton = findViewById(R.id.loginButton);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Redirigir a LoginActivity
                Intent intent = new Intent(UserRegisterActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });

        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(UserRegisterActivity.this, LoginActivity.class);
                startActivity(i);
                finish();
            }
        });

        Button verUserButton = findViewById(R.id.verUserButton);
        verUserButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserRegisterActivity.this, ListUserActivity.class);
                startActivity(intent);
            }
        });

        new UserFormHelper(this);

    }
}