package com.example.ecorecicla;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;


import androidx.appcompat.app.AppCompatActivity;


public class EcoCategoria extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eco_categoria);
        ImageButton btnback = findViewById(R.id.Buttonback);
        Button btnReg = findViewById(R.id.btnecoReg);
        Button btnSeg = findViewById(R.id.btnecoSegui);

        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(EcoCategoria.this, MainActivity.class);
                startActivity(i);
                finish();
            }
        });

        btnReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(EcoCategoria.this, EcoCatRegistrar.class);
                startActivity(i);
                finish();
            }
        });

        btnSeg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(EcoCategoria.this, EcoCatHistorial.class);
                startActivity(i);
                finish();

            }
        });



    }
}