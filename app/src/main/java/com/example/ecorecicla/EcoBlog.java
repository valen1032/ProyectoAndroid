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

public class EcoBlog extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eco_blog);
        ImageButton btnback = findViewById(R.id.Buttonback);
        ImageButton blogbtn1 = findViewById(R.id.ecobtn1);
        ImageButton blogbtn2 = findViewById(R.id.ecobtn2);

        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(EcoBlog.this, MainActivity.class);
                startActivity(i);
                finish();
            }
        });

        blogbtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(EcoBlog.this, EcoBlogView1.class);
                startActivity(i);
                finish();

            }
        });

        blogbtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(EcoBlog.this, EcoBlogView2.class);
                startActivity(i);
                finish();

            }
        });

    }
}