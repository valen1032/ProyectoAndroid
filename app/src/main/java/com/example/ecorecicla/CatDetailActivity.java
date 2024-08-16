package com.example.ecorecicla;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.StyleSpan;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.models.DatabaseManager;

public class CatDetailActivity extends AppCompatActivity {

    private static final int EDIT_USER_REQUEST_CODE = 1;

    private TextView textViewId, textViewCat, textViewCant, textViewDes, textViewfec;
    private int catId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cat_detail2);

        // Obtener los datos de la categoria desde el Intent
        catId = getIntent().getIntExtra("id", -1);
        String categoria = getIntent().getStringExtra("categoria");
        String cantidad = getIntent().getStringExtra("cantidad");
        String descripcion = getIntent().getStringExtra("descripcion");
        String fecha = getIntent().getStringExtra("fecha");

        // Inicializar los TextViews
        textViewId = findViewById(R.id.textViewId);
        textViewCat = findViewById(R.id.textViewCategoria);
        textViewCant = findViewById(R.id.textViewCantidad);
        textViewDes = findViewById(R.id.textViewDescripcion);
        textViewfec = findViewById(R.id.textViewFecha);

        setBoldText(textViewId, "Código: ", String.valueOf(catId));
        setBoldText(textViewCat, "Categoria: ", categoria);
        setBoldText(textViewCant, "cantidad: ", cantidad);
        setBoldText(textViewDes, "Descripcion: ", descripcion);
        setBoldText(textViewfec, "Fecha: ", fecha);

        // Configurar el botón "CATEGORIAS" para regresar a la actividad anterior
        Button volverButton = findViewById(R.id.volverButton);
        volverButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        // Configurar el botón "ELIMINAR" para eliminar el usuario con confirmación
        Button deleteUserButton = findViewById(R.id.deleteCatButton);
        deleteUserButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirmDeleteCat(catId);
            }
        });

        // Configurar el botón "Modificar" para abrir EditUserActivity
        Button editUserButton = findViewById(R.id.editUserButton);
        editUserButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CatDetailActivity.this, EcoCatRegistrar.class);
                intent.putExtra("id", catId);
                intent.putExtra("categoria", categoria);
                intent.putExtra("cantidad", cantidad);
                intent.putExtra("descripcion", descripcion);
                intent.putExtra("fecha", fecha);
                startActivityForResult(intent, EDIT_USER_REQUEST_CODE);
            }
        });



    }

    private void setBoldText(TextView textView, String label, String value) {
        SpannableString spannableString = new SpannableString(label + value);
        spannableString.setSpan(new StyleSpan(Typeface.BOLD), 0, label.length(), 0);
        textView.setText(spannableString);
    }

    private void confirmDeleteCat(int userId) {
        new AlertDialog.Builder(this)
                .setTitle("Confirmar eliminación")
                .setMessage("¿Está seguro de que desea eliminar esta categoria?")
                .setPositiveButton("Sí", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        deleteCat(userId);
                    }
                })
                .setNegativeButton("No", null)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }

    private void deleteCat(int userId) {
        DatabaseManager dbManager = new DatabaseManager(this);
        dbManager.deleteUser(userId);
        dbManager.close();
        setResult(RESULT_OK);
        finish();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == EDIT_USER_REQUEST_CODE && resultCode == RESULT_OK) {
            // Obtener los datos modificados del Intent
            catId = data.getIntExtra("id", catId);
            String categoria = data.getStringExtra("categoria");
            String cantidad = data.getStringExtra("cantidad");
            String descripcion = data.getStringExtra("descripcion");
            String fecha = data.getStringExtra("fecha");

            // Actualizar los TextViews con los datos modificados
            setBoldText(textViewId, "Código: ", String.valueOf(catId));
            setBoldText(textViewCat,"categoria: ", categoria);
            setBoldText(textViewCant, "cantidad: ", cantidad);
            setBoldText(textViewDes, "descripcion: ", descripcion);
            setBoldText(textViewfec, "fecha: ", fecha);
        }
    }
}