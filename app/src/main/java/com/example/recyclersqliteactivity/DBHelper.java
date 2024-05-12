package com.example.recyclersqliteactivity;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {

    public DBHelper( Context context) {
        super(context, "userdata.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase DB) {
        DB.execSQL("create Table  Userdetails(name TEXT primary Key, description TEXT , Age TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase DB, int i, int ii) {
        DB.execSQL("drop Table if exists Userdetails ");

    }

    public  boolean insertuserdata(String name , String description , String Age ){
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name",name);
        contentValues.put("description",description);
        contentValues.put("Age",Age);
        long result = DB.insert("Userdetails",null,contentValues);
        if (result == -1){
            return false;
        }
        else {
            return true;
        }

    }
    public  boolean updateuserdata(String name , String description , String Age ){
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("description",description);
        contentValues.put("Age",Age);
        Cursor cursor = DB.rawQuery("Select * from Userdetails where name=?",new String[]{name});
        if (cursor.getCount()> 0) {
            long result = DB.update("Userdetails", contentValues, "name=?", new String[]{name});
            if (result == -1) {
                return false;
            } else {
                return true;
            }
        }else {
            return  false;
        }

    }

    public  boolean deleterdata(String name){
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from Userdetails where name=?",new String[]{name});
        if (cursor.getCount()> 0) {
            long result = DB.delete("Userdetails", "name=?", new String[]{name});
            if (result == -1) {
                return false;
            } else {
                return true;
            }
        }else {
            return  false;
        }

    }

    public Cursor getdata()
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from Userdetails",null);
        return cursor;
    }
}
