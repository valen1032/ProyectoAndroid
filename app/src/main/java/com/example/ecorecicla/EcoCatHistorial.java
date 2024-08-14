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

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import com.models.CategoriaManager;  // Asegúrate de que esta es la clase correcta

public class EcoCatHistorial extends AppCompatActivity {

    private CategoriaManager dbManager;  // Instancia del manejador de categorías para interactuar con la base de datos
    private ActivityResultLauncher<Intent> catDetailLauncher;  // Lanzador para iniciar la actividad CatDetailActivity con resultado

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eco_cat_historial);  // Configura el diseño de la actividad
        dbManager = new CategoriaManager(this);  // Inicializa el manejador de categorías

        // Inicializar el ActivityResultLauncher para manejar resultados de la actividad CatDetailActivity
        catDetailLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == RESULT_OK) {
                        refreshCatList();  // Actualiza la lista de categorías si el resultado es OK
                    }
                }
        );

        // Configura el botón para regresar a la actividad anterior
        ImageButton btnback = findViewById(R.id.ButtonbackHis);
        btnback.setOnClickListener(view -> {
            Intent i = new Intent(EcoCatHistorial.this, EcoCategoria.class);
            startActivity(i);  // Inicia la actividad EcoCategoria
            finish();  // Finaliza la actividad actual
        });

        // Llama a refreshCatList() para cargar los datos al inicio
        refreshCatList();
    }

    // Método para actualizar la lista de categorías en la tabla
    private void refreshCatList() {
        TableLayout tableLayoutCats = findViewById(R.id.tableLayoutCat);  // Obtiene el TableLayout para mostrar los datos
        tableLayoutCats.removeAllViews();  // Elimina todas las vistas actuales para refrescar la tabla

        // Crear y agregar la fila de encabezado
        TableRow headerRow = new TableRow(this);
        headerRow.setLayoutParams(new TableLayout.LayoutParams(
                TableLayout.LayoutParams.MATCH_PARENT,
                TableLayout.LayoutParams.WRAP_CONTENT
        ));
        headerRow.setBackgroundResource(R.drawable.table_row_background);  // Establece el fondo para la fila de encabezado

        // Agrega los encabezados de columna
        TextView headerId = createHeaderView("Id");
        headerRow.addView(headerId);

        TextView headerCategoria = createHeaderView("Categoria");
        headerRow.addView(headerCategoria);

        TextView headerCantidad = createHeaderView("Cantidad");
        headerRow.addView(headerCantidad);

        TextView headerFecha = createHeaderView("Fecha");
        headerRow.addView(headerFecha);

        TextView headerVer = createHeaderView("Ver");
        headerRow.addView(headerVer);

        tableLayoutCats.addView(headerRow);  // Agrega la fila de encabezado a la tabla

        // Obtener todas las categorías de la base de datos
        Cursor cursor = dbManager.getCategorias();  // Ejecuta la consulta para obtener categorías
        if (cursor != null && cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(cursor.getColumnIndexOrThrow("id"));
                String categoria = cursor.getString(cursor.getColumnIndexOrThrow("categoria"));
                String cantidad = cursor.getString(cursor.getColumnIndexOrThrow("cantidad"));
                String descripcion = cursor.getString(cursor.getColumnIndexOrThrow("descripcion"));
                String fecha = cursor.getString(cursor.getColumnIndexOrThrow("fecha"));

                // Crear una nueva fila para los datos de la categoría
                TableRow row = new TableRow(this);
                row.setLayoutParams(new TableLayout.LayoutParams(
                        TableLayout.LayoutParams.MATCH_PARENT,
                        TableLayout.LayoutParams.WRAP_CONTENT
                ));
                row.setBackgroundResource(R.drawable.table_row_background);  // Establece el fondo para la fila de datos

                // Agrega los datos de la categoría a la fila
                TextView textViewId = createDataView(String.valueOf(id));
                row.addView(textViewId);

                TextView textViewCategoria = createDataView(categoria);
                row.addView(textViewCategoria);

                TextView textViewCantidad = createDataView(cantidad);
                row.addView(textViewCantidad);

                TextView textViewFecha = createDataView(fecha);
                row.addView(textViewFecha);

                // Crea un botón "Ver" que abre una nueva actividad para ver detalles
                Button buttonVer = createButton("Ver");
                buttonVer.setOnClickListener(v -> {
                    Intent intent = new Intent(EcoCatHistorial.this, CatDetailActivity.class);
                    intent.putExtra("id", id);
                    intent.putExtra("categoria", categoria);
                    intent.putExtra("cantidad", cantidad);
                    intent.putExtra("descripcion", descripcion);
                    intent.putExtra("fecha", fecha);
                    catDetailLauncher.launch(intent);  // Lanza la actividad CatDetailActivity
                });
                row.addView(buttonVer);

                tableLayoutCats.addView(row);  // Agrega la fila con los datos y el botón a la tabla

            } while (cursor.moveToNext());  // Avanza al siguiente registro
        }
        if (cursor != null) {
            cursor.close();  // Cierra el cursor para liberar recursos
        }
    }

    // Método para crear una vista de encabezado con estilo
    private TextView createHeaderView(String text) {
        TextView textView = new TextView(this);
        textView.setText(text);
        textView.setBackgroundResource(R.drawable.header_text_background);  // Establece el fondo para el encabezado
        textView.setPadding(8, 8, 8, 8);
        textView.setGravity(Gravity.CENTER);
        textView.setTextColor(Color.WHITE);
        textView.setTypeface(null, Typeface.BOLD);  // Establece el estilo de texto en negrita
        return textView;
    }

    // Método para crear una vista de datos con estilo
    private TextView createDataView(String text) {
        TextView textView = new TextView(this);
        textView.setText(text);
        textView.setBackgroundResource(R.drawable.table_cell_background);  // Establece el fondo para las celdas de datos
        textView.setPadding(8, 8, 8, 8);
        textView.setGravity(Gravity.CENTER);
        return textView;
    }

    // Método para crear un botón con estilo
    private Button createButton(String text) {
        Button button = new Button(this);
        button.setText(text);
        button.setBackgroundResource(R.drawable.button_background);  // Establece el fondo para el botón

        // Configura el tamaño del botón basado en recursos
        int widthInPixels = getResources().getDimensionPixelSize(R.dimen.button_width);
        int heightInPixels = getResources().getDimensionPixelSize(R.dimen.button_height);
        TableRow.LayoutParams params = new TableRow.LayoutParams(widthInPixels, heightInPixels);
        params.gravity = Gravity.CENTER;
        button.setLayoutParams(params);

        button.setPadding(10, 5, 10, 5);
        button.setTextSize(12);
        button.setGravity(Gravity.CENTER);
        button.setTextColor(getResources().getColor(android.R.color.white));  // Establece el color del texto del botón
        return button;
    }
}
