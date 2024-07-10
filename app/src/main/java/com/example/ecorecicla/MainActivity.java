package com.example.ecorecicla;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {
   ImageButton Catbtn1;
    ImageButton Estbtn2;
    ImageButton Blogbtn3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Catbtn1 = findViewById(R.id.ecobtn1);
        Estbtn2 = findViewById(R.id.ecobtn2);
        Blogbtn3 = findViewById(R.id.ecobtn3);
        Catbtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, EcoCategoria.class);
                startActivity(i);
                finish();

            }
        });

        Estbtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, EcoEstadistica.class);
                startActivity(i);
                finish();

            }
        });

        Blogbtn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(MainActivity.this, EcoBlog.class);
                startActivity(i);
                finish();

            }
        });



    }
}