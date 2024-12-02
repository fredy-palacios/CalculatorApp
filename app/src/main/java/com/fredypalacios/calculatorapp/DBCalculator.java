package com.fredypalacios.calculatorapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBCalculator extends SQLiteOpenHelper {
    public static String DATABASE_NAME = "DBCalculator.db";
    public static final int DATABASE_VERSION = 1;
    public static final String TABLE_NAME = "Calculator";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_RESULT = "result";
    private SQLiteDatabase db = null;

    private final String SQLCREATE = "CREATE TABLE " + TABLE_NAME +
                    "(" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_RESULT + " TEXT)";

    private final String SQLDROP = "DROP TABLE IF EXISTS " + TABLE_NAME;

    public DBCalculator(Context context) {
        super(context, DATABASE_NAME, null , DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQLCREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQLDROP);
        db.execSQL(SQLCREATE);
    }

    public void closeDB () {
        if(db != null) {
            db.close();
        }
    }

    public void insertResult (String result){
        try {
            db = getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(COLUMN_RESULT, result);
            db.insert(TABLE_NAME, null, values);
        } catch (Exception e) {
            Log.e("DBCalculator", "Error al insertar el resultado: " + e.getMessage(), e);
        } finally {
            closeDB();
        }
    }

    public String getLastResult() {
        Cursor cursor = null;
        try {
            db = getReadableDatabase();
            cursor = db.rawQuery(
                    " SELECT " + COLUMN_RESULT +
                            " FROM " + TABLE_NAME +
                            " ORDER BY " + COLUMN_ID +
                            " DESC LIMIT 1", null
            );
            if (cursor != null && cursor.moveToFirst()) {
                return cursor.getString(0);
            }
        } catch (Exception e) {
            Log.e("DBCalculator", "Error al obtener el último resultado: " + e.getMessage(), e);
        } finally {
            if (cursor != null) {
                cursor.close();
            }
            closeDB();
        }
        return null;
    }
}
