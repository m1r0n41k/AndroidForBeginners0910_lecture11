package com.drondon.androidforbeginner_lecture11;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by andriimiroshnychenko on 11/13/17.
 */

public class DataBaseHelper extends SQLiteOpenHelper {

    public static String COLUMN_NAME_FIRST_NAME = "firstName";

    public DataBaseHelper(Context context) {
        //Создание базы данных
        super(context, "MyDB.db", null, 3);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //Создание таблиц
        db.execSQL("create table groups ("
                + "_id integer primary key autoincrement,"
                + "number integer not null);");
        db.execSQL("CREATE TABLE users" +
                        "(_id INTEGER  PRIMARY KEY AUTOINCREMENT," +
                        "firstName TEXT NOT NULL," +
                        "lastName  TEXT NOT NULL," +
                        "age  INTEGER NOT NULL);");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db,
                          int oldVersion, int newVersion) {
        //Обновление базы данных
    }

}

