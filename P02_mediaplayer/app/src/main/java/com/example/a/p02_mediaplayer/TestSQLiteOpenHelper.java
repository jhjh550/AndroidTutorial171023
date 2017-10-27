package com.example.a.p02_mediaplayer;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by a on 2017-10-27.
 */

public class TestSQLiteOpenHelper extends SQLiteOpenHelper {
    public TestSQLiteOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE playinfo (id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "filename TEXT, current INTEGER, duration INTEGER)";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
