package com.oose.postop;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Omotola on 10/26/2017.
 */

public class DatabaseHelper  extends SQLiteOpenHelper {


    private static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "PostOpDatabase";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(Database.DeviceId.CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + Database.DeviceId.TABLE_NAME);
        onCreate(sqLiteDatabase);


    }





}
