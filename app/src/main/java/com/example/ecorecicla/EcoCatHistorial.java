package com.example.ecorecicla;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.models.DatabaseManager;

public class EcoCatHistorial extends AppCompatActivity {

    private DatabaseManager dbManager;
    private ActivityResultLauncher<Intent> catDetailLauncher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eco_cat_historial);  // Establece el diseño para la actividad
        dbManager = new DatabaseManager(this); // Inicializa el gestor de base de datos

        // Inicializar el ActivityResultLauncher
        catDetailLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == RESULT_OK){
                        // Refresca los datos aquí
                        refreshCatList();
                    }
                }
        );

        ImageButton btnback = findViewById(R.id.ButtonbackHis);
        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(EcoCatHistorial.this, EcoCategoria.class);
                startActivity(i);
                finish();
            }
        });

    }

    private void refreshCatList() {
        // Obtiene referencia a la TableLayout donde se mostrarán los usuarios
        TableLayout tableLayoutCats = findViewById(R.id.tableLayoutCat);
        tableLayoutCats.removeAllViews();
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

        TextView headerDocument = createHeaderView("Categoria");
        headerRow.addView(headerDocument);

        TextView headerVer = createHeaderView("VER");
        headerRow.addView(headerVer);

        // Agregar la fila de encabezado a la TableLayout
        tableLayoutCats.addView(headerRow);

        // Obtener todos las categorias de la base de datos
        Cursor cursor = dbManager.getUsers();
        if (cursor.moveToFirst()) { // Verifica si hay al menos una Categoria
            do {
                // Obtiene los datos de la categoria del cursor
                final int id = cursor.getInt(0);
                String categorias = cursor.getString(1);
                String cantidades = cursor.getString(2);
                String descripcion = cursor.getString(3);
                String fecha = cursor.getString(4);

                // Crea una nueva fila para cada categoria y la agrega a la tabla
                TableRow row = new TableRow(this);
                row.setLayoutParams(new TableLayout.LayoutParams(
                        TableLayout.LayoutParams.MATCH_PARENT,
                        TableLayout.LayoutParams.WRAP_CONTENT
                ));
                row.setBackgroundResource(R.drawable.table_row_background);

                // Crea y configura el TextView para el ID de la categoria
                TextView textViewId = createDataView(String.valueOf(id));
                row.addView(textViewId);

                // Crea y configura el TextView para la categoria
                TextView textViewCategoria = createDataView(categorias);
                row.addView(textViewCategoria);

                // Crea y configura el botón "Ver"
                Button buttonVer = createButton("Ver");
                buttonVer.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // Acción al hacer clic en el botón "Ver"
                        Intent intent = new Intent(EcoCatHistorial.this, CatDetailActivity.class);
                        intent.putExtra("id", id);
                        intent.putExtra("categorias", categorias);
                        intent.putExtra("cantidades", cantidades);
                        intent.putExtra("descripicion", descripcion);
                        intent.putExtra("fecha", fecha);
                        catDetailLauncher.launch(intent);
                    }
                });
                row.addView(buttonVer);

                // Agrega la fila a la TableLayout
                tableLayoutCats.addView(row);

            } while (cursor.moveToNext()); // Repite para cada categoria en la base de datos
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
        button.setPadding(10, 5, 10, 5);

        // Ajusta el tamaño del texto según tus preferencias
        button.setTextSize(12);

        // Ajusta la gravedad del texto
        button.setGravity(Gravity.CENTER);

        // Establece el color del texto
        button.setTextColor(getResources().getColor(android.R.color.white));
        return button;

    }
}