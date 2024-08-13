package com.example.ecorecicla;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

public class EditUserActivity extends AppCompatActivity {

    private EditText editTextId, firstName, lastName, idNumber, email, phone;
    private int catId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_user);

        // Referenciar los EditText con los nuevos IDs
        editTextId = findViewById(R.id.editTextId);
        firstName = findViewById(R.id.firstName);
        lastName = findViewById(R.id.lastName);
        idNumber = findViewById(R.id.idNumber);
        email = findViewById(R.id.email);
        phone = findViewById(R.id.phone);

        // Obtener los datos del usuario desde el Intent
        Intent intent = getIntent();
        catId = intent.getIntExtra("id", -1);
        editTextId.setText(String.valueOf(catId));
        idNumber.setText(intent.getStringExtra("document"));
        firstName.setText(intent.getStringExtra("nombres"));
        lastName.setText(intent.getStringExtra("apellidos"));
        email.setText(intent.getStringExtra("email"));
        phone.setText(intent.getStringExtra("phone"));

        // Configurar el botón "Guardar"
        Button saveButton = findViewById(R.id.saveButton);
        saveButton.setOnClickListener(v -> {
            // Obtener los nuevos valores de los EditText
            String newDocument = idNumber.getText().toString();
            String newNombres = firstName.getText().toString();
            String newApellidos = lastName.getText().toString();
            String newEmail = email.getText().toString();
            String newPhone = phone.getText().toString();

            // Crear un Intent para devolver los datos modificados a UserDetailActivity
            Intent resultIntent = new Intent();
            resultIntent.putExtra("id", catId);
            resultIntent.putExtra("document", newDocument);
            resultIntent.putExtra("nombres", newNombres);
            resultIntent.putExtra("apellidos", newApellidos);
            resultIntent.putExtra("email", newEmail);
            resultIntent.putExtra("phone", newPhone);
            setResult(RESULT_OK, resultIntent);
            finish();
        });
    }
}
