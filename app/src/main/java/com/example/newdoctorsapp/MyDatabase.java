package com.example.newdoctorsapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class MyDatabase extends SQLiteOpenHelper {
   static String databasename="DoctorApp";
    static int version=1;
    String qualification_table="create table qualification_table ( id integer primary key not null ,_id text, name text ,abbreviation text)";

    public MyDatabase(Context context) {
        super(context, databasename, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
     db.execSQL(qualification_table);
    }
    public  void DeleteTable(String tblname) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(tblname,null,null);

    }
    public void InsertData(ContentValues values, String TABLE_NAME) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    public Cursor GetAllData(String query) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor=db.rawQuery(query,null);
        return cursor;
    }
    public void DropDatabase() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("qualification_table",null,null);
        db.close();
        //DROP DATABASE databasename
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + "qualification_table");
        onCreate(db);
    }
}
