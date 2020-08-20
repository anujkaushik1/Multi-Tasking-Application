package com.example.myfirstapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class sqlitetask extends SQLiteOpenHelper {
    public sqlitetask(Context context) {
        super(context,"tasks31.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
    sqLiteDatabase.execSQL("create table todo (_id INTEGER PRIMARY KEY ,stitle text,sdeadline text,scurrentdate text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    onCreate(sqLiteDatabase);
    }
}
