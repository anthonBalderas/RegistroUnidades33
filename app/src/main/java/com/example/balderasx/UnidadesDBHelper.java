package com.example.balderasx;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.balderasx.UnidadesContract.*;

public class UnidadesDBHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "unidadesList.db";
    public static final int DATABASE_VERSION = 2;

    public UnidadesDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        final String SQL_CREATE_UNIDADESLIST_TABLE = "CREATE TABLE " +
                UnidadesEntry.TABLE_NAME + " ( " +
                UnidadesEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                UnidadesEntry.COLUMN_UNIDAD + " TEXT NOT NULL, " +
                UnidadesEntry.COLUMN_HORA + " TEXT NOT NULL, " +
                UnidadesEntry.COLUMN_TIMESTAMP + " TIMESTAMP DEFAULT CURRENT_TIMESTAMP" +
                ");";
        db.execSQL(SQL_CREATE_UNIDADESLIST_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + UnidadesEntry.TABLE_NAME);
        onCreate(db);
    }
}
