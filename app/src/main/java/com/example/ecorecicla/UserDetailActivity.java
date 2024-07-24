package com.example.ecorecicla;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.StyleSpan;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.models.DatabaseManager;

public class UserDetailActivity extends AppCompatActivity {

    private static final int EDIT_USER_REQUEST_CODE = 1;

    private TextView textViewId, textViewDocument, textViewNombres, textViewApellidos, textViewEmail, textViewPhone;
    private int userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_detail);

        // Obtener los datos del usuario desde el Intent
        userId = getIntent().getIntExtra("id", -1);
        String document = getIntent().getStringExtra("document");
        String nombres = getIntent().getStringExtra("nombres");
        String apellidos = getIntent().getStringExtra("apellidos");
        String email = getIntent().getStringExtra("email");
        String phone = getIntent().getStringExtra("phone");

        // Inicializar los TextViews
        textViewId = findViewById(R.id.textViewId);
        textViewDocument = findViewById(R.id.textViewDocument);
        textViewNombres = findViewById(R.id.textViewNombres);
        textViewApellidos = findViewById(R.id.textViewApellidos);
        textViewEmail = findViewById(R.id.textViewEmail);
        textViewPhone = findViewById(R.id.textViewPhone);

        setBoldText(textViewId, "Código: ", String.valueOf(userId));
        setBoldText(textViewDocument, "Documento: ", document);
        setBoldText(textViewNombres, "Nombres: ", nombres);
        setBoldText(textViewApellidos, "Apellidos: ", apellidos);
        setBoldText(textViewEmail, "Email: ", email);
        setBoldText(textViewPhone, "Teléfono: ", phone);

        // Configurar el botón "USUARIOS" para regresar a la actividad anterior
        Button volverButton = findViewById(R.id.volverButton);
        volverButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        // Configurar el botón "ELIMINAR" para eliminar el usuario con confirmación
        Button deleteUserButton = findViewById(R.id.deleteUserButton);
        deleteUserButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirmDeleteUser(userId);
            }
        });

        // Configurar el botón "Modificar" para abrir EditUserActivity
        Button editUserButton = findViewById(R.id.editUserButton);
        editUserButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserDetailActivity.this, EditUserActivity.class);
                intent.putExtra("id", userId);
                intent.putExtra("document", document);
                intent.putExtra("nombres", nombres);
                intent.putExtra("apellidos", apellidos);
                intent.putExtra("email", email);
                intent.putExtra("phone", phone);
                startActivityForResult(intent, EDIT_USER_REQUEST_CODE);
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
        DatabaseManager dbManager = new DatabaseManager(this);
        dbManager.deleteUser(userId);
        dbManager.close();
        setResult(RESULT_OK);
        finish();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == EDIT_USER_REQUEST_CODE && resultCode == RESULT_OK) {
            // Obtener los datos modificados del Intent
            userId = data.getIntExtra("id", userId);
            String document = data.getStringExtra("document");
            String nombres = data.getStringExtra("nombres");
            String apellidos = data.getStringExtra("apellidos");
            String email = data.getStringExtra("email");
            String phone = data.getStringExtra("phone");

            // Actualizar los TextViews con los datos modificados
            setBoldText(textViewId, "Código: ", String.valueOf(userId));
            setBoldText(textViewDocument, "Documento: ", document);
            setBoldText(textViewNombres, "Nombres: ", nombres);
            setBoldText(textViewApellidos, "Apellidos: ", apellidos);
            setBoldText(textViewEmail, "Email: ", email);
            setBoldText(textViewPhone, "Teléfono: ", phone);
        }
    }
}
