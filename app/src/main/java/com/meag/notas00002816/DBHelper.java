package com.meag.notas00002816;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by uca on 05-16-18.
 */

public class DBHelper extends SQLiteOpenHelper {
    public static final String DB_NAME = "bd_alumnos";
    public static final String TABLA_USUARIO = "Persona";
    public static final String CAMPO_ID = "dui";
    public static final String CAMPO_NOMBRE = "nombre";
    public static final String CAMPO_NOTA = "nota";
    public static final String CREAR_TABLA_USUARIO = "CREATE TABLE " + TABLA_USUARIO + "(" + CAMPO_ID + " TEXT," + CAMPO_NOMBRE + " TEXT," + CAMPO_NOTA + " TEXT) ";
    public static DBHelper myDB = null;
    private Context context;
    SQLiteDatabase db;

    public static DBHelper getInstance(Context ctx) {
        if (myDB == null) {
            myDB = new DBHelper(ctx.getApplicationContext());
        }
        return myDB;
    }

    public DBHelper(Context context) {
        super(context, DB_NAME, null, 1);
        this.context = context;
        db = this.getWritableDatabase();
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREAR_TABLA_USUARIO);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + CAMPO_NOMBRE);
        onCreate(db);
    }

    public boolean add(Alumno p) {
        ContentValues values = new ContentValues();
        values.put(CAMPO_ID, p.getCarnet());
        values.put(CAMPO_NOMBRE, p.getNombre());
        values.put(CAMPO_NOTA, p.getNota());
        db.insert(TABLA_USUARIO, null, values);
        Toast.makeText(context, "Insertado con exito", Toast.LENGTH_SHORT).show();
        return true;
    }

    public boolean editUser(Alumno p) {
        String[] parametros = {p.getCarnet()};
        String[] campos = {CAMPO_NOMBRE, CAMPO_NOTA};
        ContentValues values = new ContentValues();
        values.put(CAMPO_NOMBRE, p.getNombre());
        values.put(CAMPO_NOTA, p.getNota());
        db.update(TABLA_USUARIO, values, CAMPO_ID + "=?", parametros);
        Toast.makeText(context, "Actualizado con exito", Toast.LENGTH_SHORT).show();
        return true;
    }

    public boolean deleteUser(String dui) {
        String[] parametros = {dui};
        db.delete(TABLA_USUARIO, CAMPO_ID + "=?", parametros);
        Toast.makeText(context, "Eliminado con exito", Toast.LENGTH_SHORT).show();
        return true;
    }

    public Alumno findUser(String dui) {
        Alumno p;
        String[] parametros = {dui};
        String[] campos = {CAMPO_NOMBRE, CAMPO_NOTA};

        try {
            Cursor cursor = db.query(TABLA_USUARIO, campos, CAMPO_ID + "=?", parametros, null, null, null);
            cursor.moveToFirst();
            p = new Alumno(dui, cursor.getString(0), cursor.getString(1));
        } catch (Exception e) {
            p = null;
        }
        return p;
    }

    public List<Alumno> filllist() {
        List<Alumno> alumnoList = new ArrayList<>();
        Alumno p = null;
        String[] campos = {CAMPO_ID, CAMPO_NOMBRE, CAMPO_NOTA};

        try {
            Cursor cursor = db.query(TABLA_USUARIO, campos, null, null, null, null, null);
            while (cursor.moveToNext()) {
                p = new Alumno(cursor.getString(0), cursor.getString(1), cursor.getString(2));
                alumnoList.add(p);
            }
        } catch (Exception e) {
            alumnoList = null;
        }
        return alumnoList;
    }

    public float getAvg() {
        float avg = 0;
        try {
            String query = "SELECT AVG(" + CAMPO_NOTA + ")" + " FROM " + TABLA_USUARIO;
            Cursor cursor = db.rawQuery(query, null);
            while (cursor.moveToNext()) {
                avg = cursor.getFloat(0);
            }
        } catch (Exception e) {
            avg = 0;
        }
        return avg;
    }

}
