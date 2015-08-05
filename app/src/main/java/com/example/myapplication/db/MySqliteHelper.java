package com.example.myapplication.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by sondt on 05/08/2015.
 */
public class MySqliteHelper extends SQLiteOpenHelper {
    public static String DB_NAME = "mydatabase.db";
    public static int VERSION = 3;

    public MySqliteHelper(Context context) {
        super(context, DB_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("Create table Product( _id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "id INTEGER, name TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.i("MySqliteHelper.onUpgrade", "-----> " + oldVersion);
//        if (oldVersion == 1) {
            db.execSQL("drop table Product");
            db.execSQL("Create table Product( _id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "id INTEGER, name TEXT)");

//        }
    }
}
