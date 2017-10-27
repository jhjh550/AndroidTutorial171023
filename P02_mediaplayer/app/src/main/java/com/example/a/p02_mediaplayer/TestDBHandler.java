package com.example.a.p02_mediaplayer;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by a on 2017-10-27.
 */

public class TestDBHandler {
    TestSQLiteOpenHelper helper;

    public TestDBHandler(Context context){
        helper = new TestSQLiteOpenHelper(context, "test", null, 1);
    }

    public void insert(String filename, int current, int duration){
        SQLiteDatabase db = helper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("filename", filename);
        values.put("current", current);
        values.put("duration", duration);

        db.insert("playinfo", null, values);
    }

    public void delete(String filename){
        SQLiteDatabase db = helper.getWritableDatabase();
        db.delete("playinfo", "filename = ?", new String[]{filename});
    }

    public void update(String filename, int current){
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("current", current);
        db.update("playinfo", values, "filename = ?", new String[]{filename});
    }

    public int getCurrentPostion(String filename){

        int currentPosition = 0;
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor c = db.query("playinfo", null, "filename = ?", new String[]{filename}, null, null, null);
        if(c.moveToFirst()){
            currentPosition = c.getInt( c.getColumnIndex("current"));
        }else{
            insert(filename, 0, 0);
        }

        return currentPosition;
    }
}
