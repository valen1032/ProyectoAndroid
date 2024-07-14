package com.models;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "eco_recicla.db";
    private static final int DATABASE_VERSION = 2;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Crear tabla
        String CREATE_TABLE = "CREATE TABLE users (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "nombres TEXT, " +
                "apellidos TEXT, " +
                "documento TEXT, " +
                "email TEXT, " +
                "telefono TEXT, " +
                "password TEXT, " +
                "rol TEXT" +
                ")";

        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Borrar tabla si existe y crearla de nuevo
       // db.execSQL("DROP TABLE IF EXISTS users");
       // onCreate(db);
        if (oldVersion < 2) {
            // Actualiza la base de datos para incluir el nuevo campo
            db.execSQL("ALTER TABLE users ADD COLUMN rol TEXT");
        }
    }
}