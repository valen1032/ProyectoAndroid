package com.example.ecorecicla;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.models.CategoriaManager;

public class EcoCatRegistrar extends AppCompatActivity {
    private EditText Etmat, Etcant, Etdesc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eco_cat_registrar);

        // Referenciar los EditText y el botón
        Etmat = findViewById(R.id.etmat);
        Etcant = findViewById(R.id.etcant);
        Etdesc = findViewById(R.id.etdes);
        ImageButton btnback = findViewById(R.id.Buttonback);

        // Configurar el botón de regreso
        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(EcoCatRegistrar.this, EcoCategoria.class);
                startActivity(i);
                finish();
            }
        });

        // Configurar el botón "Guardar"
        Button btnAdd = findViewById(R.id.btnadd);
        btnAdd.setOnClickListener(v -> {
            // Obtener los valores de los EditText
            String newCategoria = Etmat.getText().toString();
            String newCantidad = Etcant.getText().toString();
            String newDescripcion = Etdesc.getText().toString();

            // Validar campos vacíos
            if (newCategoria.isEmpty() || newCantidad.isEmpty() || newDescripcion.isEmpty()) {
                Toast.makeText(EcoCatRegistrar.this, "Por favor diligencie todos los campos", Toast.LENGTH_SHORT).show();
                return;
            }

            // Realizar el insert en la base de datos
            long nuevoId = insertarEnBaseDeDatos(newCategoria, Integer.parseInt(newCantidad), newDescripcion);

            // Verificar si el insert fue exitoso
            if (nuevoId != -1) {
                // Mostrar un mensaje de éxito con el ID del registro
                Toast.makeText(EcoCatRegistrar.this, "Registro exitoso con ID: " + nuevoId, Toast.LENGTH_SHORT).show();

                // Crear un Intent para redirigir a EcoCategoria
                Intent resultIntent = new Intent(EcoCatRegistrar.this, EcoCategoria.class);
                // Agregar el ID del nuevo registro al Intent
                resultIntent.putExtra("nuevoId", nuevoId);
                // Iniciar la actividad
                startActivity(resultIntent);
                // Finalizar la actividad actual
                finish();
            } else {
                // Mostrar un mensaje de error si el insert falló
                Toast.makeText(EcoCatRegistrar.this, "Error al registrar los datos", Toast.LENGTH_SHORT).show();
            }
        });
    }

    // Método para insertar datos en la base de datos y devolver el ID del nuevo registro
    private long insertarEnBaseDeDatos(String categoria, Integer cantidad, String descripcion) {
        CategoriaManager categoriaManager = new CategoriaManager(this);
        return categoriaManager.insertCategoria(categoria, cantidad, descripcion);
    }
}
