package com.example.ecorecicla;

import android.content.DialogInterface;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.StyleSpan;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class UserDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_detail);

        // Obtener los datos del usuario desde el Intent
        int id = getIntent().getIntExtra("id", -1);
        String document = getIntent().getStringExtra("document");
        String nombres = getIntent().getStringExtra("nombres");
        String apellidos = getIntent().getStringExtra("apellidos");
        String email = getIntent().getStringExtra("email");
        String phone = getIntent().getStringExtra("phone");

        // Mostrar los datos del usuario en TextViews
        TextView textViewId = findViewById(R.id.textViewId);
        TextView textViewDocument = findViewById(R.id.textViewDocument);
        TextView textViewNombres = findViewById(R.id.textViewNombres);
        TextView textViewApellidos = findViewById(R.id.textViewApellidos);
        TextView textViewEmail = findViewById(R.id.textViewEmail);
        TextView textViewPhone = findViewById(R.id.textViewPhone);

        setBoldText(textViewId, "Código: ", String.valueOf(id));
        setBoldText(textViewDocument, "Documento: ", document);
        setBoldText(textViewNombres, "Nombres: ", nombres);
        setBoldText(textViewApellidos, "Apellidos: ", apellidos);
        setBoldText(textViewEmail, "Email: ", email);
        setBoldText(textViewPhone, "Phone: ", phone);

        // Configurar el botón "USUARIOS" para regresar a la actividad anterior
        Button volverButton = findViewById(R.id.volverButton);
        volverButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Termina la actividad actual y regresa a la actividad anterior
                finish();
            }
        });

        // Configurar el botón "ELIMINAR" para eliminar el usuario con confirmación
        Button deleteUserButton = findViewById(R.id.deleteUserButton);
        deleteUserButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirmDeleteUser(id);
            }
        });

    }

    private void setBoldText(TextView textView, String label, String value) {
        SpannableString spannableString = new SpannableString(label + value);
        spannableString.setSpan(new StyleSpan(Typeface.BOLD), 0, label.length(), 0);
        textView.setText(spannableString);
    }

    private void confirmDeleteUser(int userId) {
        new AlertDialog.Builder(this)
                .setTitle("Confirmar eliminación")
                .setMessage("¿Está seguro de que desea eliminar este usuario?")
                .setPositiveButton("Sí", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        deleteUser(userId);
                    }
                })
                .setNegativeButton("No", null)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }

    private void deleteUser(int userId) {
        // Lógica para eliminar el usuario
        // Por ejemplo, puedes eliminar el usuario de la base de datos aquí

        // Volver a la actividad anterior después de eliminar el usuario
        finish();
    }
}