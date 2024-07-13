package com.example.ecorecicla;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ecorecicla.R;
import com.models.DatabaseManager;
import com.example.ecorecicla.ListUserActivity;

public class UserFormHelper {
    private Activity activity;
    private DatabaseManager dbManager;
    private EditText firstNameField;
    private EditText lastNameField;
    private EditText idNumberField;
    private EditText emailField;
    private EditText phoneField;
    private EditText passwordField;
    private Button registerButton;
    private TextView textView;

    public UserFormHelper(Activity activity) {
        this.activity = activity;
        dbManager = new DatabaseManager(activity);
        firstNameField = activity.findViewById(R.id.firstName);
        lastNameField = activity.findViewById(R.id.lastName);
        idNumberField = activity.findViewById(R.id.idNumber);
        emailField = activity.findViewById(R.id.email);
        phoneField = activity.findViewById(R.id.phone);
        passwordField = activity.findViewById(R.id.password);
        registerButton = activity.findViewById(R.id.registerButton);

        setupRegisterButton();
    }

    private void setupRegisterButton() {
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String firstName = firstNameField.getText().toString();
                String lastName = lastNameField.getText().toString();
                String idNumber = idNumberField.getText().toString();
                String email = emailField.getText().toString();
                String phone = phoneField.getText().toString();
                String password = passwordField.getText().toString();

                if (firstName.isEmpty() || lastName.isEmpty() || idNumber.isEmpty() || email.isEmpty() || phone.isEmpty() || password.isEmpty()) {
                    Toast.makeText(activity, "Por favor diligencie todos los campos", Toast.LENGTH_SHORT).show();
                    return;
                }

                insertUser(firstName, lastName, idNumber, email, phone, password);
                clearFields();
                showUserList();
            }
        });
    }

    private void insertUser(String firstName, String lastName, String idNumber, String email, String phone, String password) {
        dbManager.insertUser(firstName, lastName, idNumber, email, phone, password);
    }

    private void clearFields() {
        firstNameField.setText("");
        lastNameField.setText("");
        idNumberField.setText("");
        emailField.setText("");
        phoneField.setText("");
        passwordField.setText("");
    }

    private void showUserList() {
        // Crear un Intent para abrir la nueva actividad
        Intent intent = new Intent(activity, ListUserActivity.class);
        activity.startActivity(intent); // Iniciar la nueva actividad
    }
}