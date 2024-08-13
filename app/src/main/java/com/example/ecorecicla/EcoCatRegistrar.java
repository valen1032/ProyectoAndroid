package com.example.ecorecicla;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class EcoCatRegistrar extends AppCompatActivity {
    private EditText Etmat,Etcant,Etdesc;
    private int catId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eco_cat_registrar);

        // Referenciar los EditText con los nuevos IDs
        Etmat  = findViewById(R.id.etmat);
        Etcant = findViewById(R.id.etcant);
        Etdesc = findViewById(R.id.etdes);
        ImageButton btnback = findViewById(R.id.Buttonback);
        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(EcoCatRegistrar.this, EcoCategoria.class);
                startActivity(i);
                finish();
            }
        });

        // Obtener los datos del usuario desde el Intent
        Intent intent = getIntent();
        catId = intent.getIntExtra("id", -1);
        Etmat.setText(intent.getStringExtra("categorias"));
        Etcant.setText(intent.getStringExtra("cantidades"));
        Etdesc.setText(intent.getStringExtra("descripcion"));

        // Configurar el botón "Guardar"
        Button btnAdd = findViewById(R.id.btnadd);
        btnAdd.setOnClickListener(v ->{
            // Obtener los nuevos valores de los EditText
            String newCategoria = Etmat.getText().toString();
            String newCantidad = Etcant.getText().toString();
            String newDescripcion = Etdesc.getText().toString();


            // Crear un Intent para devolver los datos modificados a CatDetailActivity
            Intent resultIntent = new Intent();
            resultIntent.putExtra("id", catId);
            resultIntent.putExtra("categorias", newCategoria);
            resultIntent.putExtra("cantidades", newCantidad);
            resultIntent.putExtra("descripcion", newDescripcion);
            setResult(RESULT_OK, resultIntent);
            finish();


        });



    }


}