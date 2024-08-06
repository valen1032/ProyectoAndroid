package com.example.ecorecicla;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.models.CategoriaManager;
import com.models.DatabaseManager;

public class CatFormHelper {

    private Activity activity;
    private CategoriaManager dbManager;
    private EditText etmate;
    private EditText etcanti;
    private EditText etdesc;
    private Button btnaddi;


    public CatFormHelper(Activity activity) {
        this.activity = activity;
        dbManager = new CategoriaManager(activity);
        etmate = activity.findViewById(R.id.etmat);
        etcanti = activity.findViewById(R.id.etcant);
        etdesc = activity.findViewById(R.id.etdes);
        btnaddi = activity.findViewById(R.id.btnadd);

        setupRegisterButton();
    }

    private void setupRegisterButton() {
        btnaddi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String categoria = etmate.getText().toString();
                String cantidad = etcanti.getText().toString();
                String descripcion = etdesc.getText().toString();

                if (categoria.isEmpty() || cantidad.isEmpty() || descripcion.isEmpty() ) {
                    Toast.makeText(activity, "Por favor diligencie todos los campos", Toast.LENGTH_SHORT).show();
                    return;
                }
                insertCat(categoria, Integer.valueOf(cantidad), descripcion);
                clearFields();
                showUserList();
            }
        });
    }


    private void insertCat(String categoria, Integer cantidad, String descripcion) {
        dbManager.insertCategoria(categoria,cantidad,descripcion);
    }

    private void clearFields() {
        etmate.setText("");
        etcanti.setText("");
        etdesc.setText("");
    }

    private void showUserList() {
        // Crear un Intent para abrir la nueva actividad
        Intent intent = new Intent(activity, EcoCatHistorial.class);
        activity.startActivity(intent);


    }



}
