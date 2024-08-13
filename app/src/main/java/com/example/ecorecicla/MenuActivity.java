package com.example.ecorecicla;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;
import android.widget.Toolbar;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
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
    Toolbar toolbar;


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.logout){
            Toast.makeText(this, "Cerrar sesion", Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*toolbar = findViewById(R.id.mytoolBar);
        setSupportActionBar(toolbar);*/


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