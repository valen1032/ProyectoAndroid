package com.models;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class DatabaseManager {
    private DatabaseHelper dbHelper;
    private SQLiteDatabase database;

    public DatabaseManager(Context context) {
        dbHelper = new DatabaseHelper(context);
        database = dbHelper.getWritableDatabase();
    }

    public long insertUser(String nombres, String apellidos, String documento,
                           String email,  String telefono, String password
    ) {
        ContentValues values = new ContentValues();
        values.put("nombres", nombres);
        values.put("apellidos", apellidos);
        values.put("documento", documento);
        values.put("email", email);
        values.put("telefono", telefono);
        values.put("password", password);


        return database.insert("users", null, values);
    }

    public Cursor getUsers() {
        String[] columns = {"id", "nombres", "apellidos", "documento", "email", "telefono", "password"};
        return database.query("users", columns, null, null, null, null, null);
    }

    public int updateUser(int id, String nombres, String apellidos, String documento,
                          String email,  String telefono, String password
    ) {
        ContentValues values = new ContentValues();
        values.put("nombres", nombres);
        values.put("apellidos", apellidos);
        values.put("documento", documento);
        values.put("email", email);
        values.put("telefono", telefono);
        values.put("password", password);

        return database.update("users", values, "id = ?", new String[]{String.valueOf(id)});
    }

    public void deleteUser(int id) {
        database.delete("users", "id = ?", new String[]{String.valueOf(id)});
    }
}