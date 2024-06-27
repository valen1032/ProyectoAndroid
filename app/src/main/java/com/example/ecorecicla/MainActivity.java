package com.example.ecorecicla;


import android.database.Cursor;
import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.models.DatabaseManager;

public class MainActivity extends AppCompatActivity {
    private DatabaseManager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbManager = new DatabaseManager(this);

        // Insertar usuario
        dbManager.insertUser("Diana Carolina", "Mesa Niño", "52922518", "dianacmesa@gmail.com", "525317", "123458");

        // Obtener usuarios
        Cursor cursor = dbManager.getUsers();
        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                String name = cursor.getString(1);
                String email = cursor.getString(2);

                // Mostrar datos en un TextView
                TextView textView = findViewById(R.id.textView);
                textView.append("ID: " + id + ", Name: " + name + ", Email: " + email + "\n");
            } while (cursor.moveToNext());
        }
        cursor.close();
    }
}
