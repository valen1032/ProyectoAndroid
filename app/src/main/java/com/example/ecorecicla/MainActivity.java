package com.example.ecorecicla;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.models.DatabaseManager;
// import com.views.UserFormHelper;

public class MainActivity extends AppCompatActivity {
    private DatabaseManager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }
}
