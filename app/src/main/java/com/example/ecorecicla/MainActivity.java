package com.example.ecorecicla;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.models.DatabaseManager;
//import com.views.UserFormHelper;

public class MainActivity extends AppCompatActivity {
    private DatabaseManager dbManager;
    ImageButton Catbtn1;
    ImageButton Estbtn2;
    ImageButton Blogbtn3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inicializar botones
        Catbtn1 = findViewById(R.id.ecobtn1);
        Estbtn2 = findViewById(R.id.ecobtn2);
        Blogbtn3 = findViewById(R.id.ecobtn3);

        // Configurar clic de botones
        Catbtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, EcoCategoria.class);
                startActivity(i);
            }
        });

        Estbtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, EcoEstadistica.class);
                startActivity(i);
            }
        });

        Blogbtn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, EcoBlog.class);
                startActivity(i);
            }
        });
        /*
        // Crear una instancia de UserFormHelper
        UserFormHelper userFormHelper = new UserFormHelper(this);

        // Configurar clic del botón verUserButton
        Button verUserButton = findViewById(R.id.verUserButton);
        verUserButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ListUserActivity.class);
                startActivity(intent);
            }
        });

        // Configurar clic del botón loginButton
        Button loginButton = findViewById(R.id.loginButton);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

         */
    }
}
