package com.example.ecorecicla;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.models.DatabaseManager;

public class MenuActivity extends AppCompatActivity {
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
                Intent i = new Intent(MenuActivity.this, EcoCategoria.class);
                startActivity(i);
            }
        });

        Estbtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MenuActivity.this, EcoEstadistica.class);
                startActivity(i);
            }
        });

        Blogbtn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MenuActivity.this, EcoBlog.class);
                startActivity(i);
            }
        });

    }
}