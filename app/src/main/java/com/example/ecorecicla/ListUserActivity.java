package com.example.ecorecicla;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.models.DatabaseManager;

public class ListUserActivity extends AppCompatActivity {
    private DatabaseManager dbManager; // Instancia del gestor de base de datos

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_list); // Establece el diseño para la actividad

        dbManager = new DatabaseManager(this); // Inicializa el gestor de base de datos

        // Obtiene referencia a la TableLayout donde se mostrarán los usuarios
        TableLayout tableLayoutUsers = findViewById(R.id.tableLayoutUsers);

        // Crear y agregar la fila de encabezado
        TableRow headerRow = new TableRow(this);
        headerRow.setLayoutParams(new TableLayout.LayoutParams(
                TableLayout.LayoutParams.MATCH_PARENT,
                TableLayout.LayoutParams.WRAP_CONTENT
        ));
        headerRow.setBackgroundResource(R.drawable.table_row_background);

        // Crear y configurar TextViews para los títulos de las columnas
        TextView headerId = createHeaderView("Id");
        headerRow.addView(headerId);

        TextView headerDocument = createHeaderView("Documento");
        headerRow.addView(headerDocument);

        TextView headerVer = createHeaderView("Ver");
        headerRow.addView(headerVer);

        TextView headerEliminar = createHeaderView("Eliminar");
        headerRow.addView(headerEliminar);

        // Agregar la fila de encabezado a la TableLayout
        tableLayoutUsers.addView(headerRow);

        // Obtener todos los usuarios de la base de datos
        Cursor cursor = dbManager.getUsers();
        if (cursor.moveToFirst()) { // Verifica si hay al menos un usuario
            do {
                // Obtiene los datos del usuario del cursor
                final int id = cursor.getInt(0);
                String nombres = cursor.getString(1);
                String apellidos = cursor.getString(2);
                String document = cursor.getString(3);
                String email = cursor.getString(4);
                String phone = cursor.getString(5);

                // Crea una nueva fila para cada usuario y la agrega a la tabla
                TableRow row = new TableRow(this);
                row.setLayoutParams(new TableLayout.LayoutParams(
                        TableLayout.LayoutParams.MATCH_PARENT,
                        TableLayout.LayoutParams.WRAP_CONTENT
                ));
                row.setBackgroundResource(R.drawable.table_row_background); // Establece el fondo para la fila

                // Crea y configura el TextView para el ID del usuario
                TextView textViewId = createDataView(String.valueOf(id));
                row.addView(textViewId);

                // Crea y configura el TextView para el documento del usuario
                TextView textViewDocument = createDataView(document);
                row.addView(textViewDocument);

                // Crea y configura el botón "Ver"
                Button buttonVer = createButton("Ver");
                buttonVer.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // Acción al hacer clic en el botón "Ver"
                        Intent intent = new Intent(ListUserActivity.this, UserDetailActivity.class);
                        intent.putExtra("id", id);
                        intent.putExtra("document", document);
                        intent.putExtra("nombres", nombres);
                        intent.putExtra("apellidos", apellidos);
                        intent.putExtra("email", email);
                        intent.putExtra("phone", phone);
                        startActivity(intent);
                    }
                });
                row.addView(buttonVer);


                // Crea y configura el botón "Eliminar"
                Button buttonEliminar = createButton("Eliminar");
                buttonEliminar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // Acción al hacer clic en el botón "Eliminar"
                        Toast.makeText(ListUserActivity.this, "Eliminar usuario: " + id, Toast.LENGTH_SHORT).show();
                        // Aquí puedes implementar la lógica para eliminar el usuario de la base de datos
                        // por ejemplo: dbManager.deleteUser(id);
                        // Luego, puedes remover la fila de la tabla:
                        // tableLayoutUsers.removeView(row);
                    }
                });
                row.addView(buttonEliminar);

                // Agrega la fila a la TableLayout
                tableLayoutUsers.addView(row);

            } while (cursor.moveToNext()); // Repite para cada usuario en la base de datos
        }
        cursor.close(); // Cierra el cursor para liberar recursos
    }

    // Método para crear TextViews de encabezado con fondo negro y texto blanco
    private TextView createHeaderView(String text) {
        TextView textView = new TextView(this);
        textView.setText(text);
        textView.setBackgroundResource(R.drawable.header_text_background); // Fondo negro
        textView.setPadding(8, 8, 8, 8);
        textView.setGravity(Gravity.CENTER);
        textView.setTextColor(Color.WHITE); // Texto blanco
        textView.setTypeface(null, Typeface.BOLD);
        return textView;
    }

    // Método para crear TextViews de datos
    private TextView createDataView(String text) {
        TextView textView = new TextView(this);
        textView.setText(text);
        textView.setBackgroundResource(R.drawable.table_cell_background);
        textView.setPadding(8, 8, 8, 8);
        textView.setGravity(Gravity.CENTER);
        return textView;
    }

    // Método para crear botones
    private Button createButton(String text) {
        Button button = new Button(this);
        button.setText(text);
        button.setBackgroundResource(R.drawable.button_background);

        // Define el tamaño del botón
        int widthInPixels = getResources().getDimensionPixelSize(R.dimen.button_width);
        int heightInPixels = getResources().getDimensionPixelSize(R.dimen.button_height);
        TableRow.LayoutParams params = new TableRow.LayoutParams(widthInPixels, heightInPixels);
        params.gravity = Gravity.CENTER;
        button.setLayoutParams(params);

        // Ajusta el padding según tus preferencias
        button.setPadding(8, 4, 8, 4);

        // Ajusta el tamaño del texto según tus preferencias
        button.setTextSize(10);

        // Ajusta la gravedad del texto
        button.setGravity(Gravity.CENTER);

        // Establece el color del texto
        button.setTextColor(getResources().getColor(android.R.color.white));

        return button;
    }
}