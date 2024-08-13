package com.models;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import java.util.Date;

public class CategoriaManager {
    private DatabaseHelper dbHelper;
    private SQLiteDatabase database;

    public CategoriaManager(Context context) {
        dbHelper = new DatabaseHelper(context);
        database = dbHelper.getWritableDatabase();
    }

    public long insertCategoria(String categoria, Integer cantidad, String descripcion) {
        Date fecha = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        String fechaString = sdf.format(fecha);
        ContentValues values = new ContentValues();
        values.put("categoria", categoria);
        values.put("cantidad", cantidad);
        values.put("descripcion", descripcion);
        values.put("fecha", fechaString);

        return database.insert("categorias", null,values);
    }

    public Cursor getCategorias() {
        String[] columns = {"id", "categoria", "cantidad", "descripcion", "fecha"};
        return database.query("categorias", columns, null, null, null, null, null);
    }
}
