package com.oose.postop;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

/**
 * Created by Omotola on 10/26/2017.
 */

public class DeviceIdDAO
{
    String id;
    Context context;

    public DeviceIdDAO(String deviceId, Context c){
        id = deviceId;
        context = c;
    }


    //Add Id to Database
    public void addIDToDB(){

        //get the database or create the database if it doesnt exist already
        SQLiteDatabase database = new DatabaseHelper(context).getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Database.DeviceId.ID,id);

        database.insert(Database.DeviceId.TABLE_NAME, null, values);
    }


    //Check if Id Exists in DB
    public boolean checkIdExists(Context c) {

        SQLiteDatabase database = new DatabaseHelper(context).getReadableDatabase();


        Cursor cursor = database.rawQuery("SELECT * FROM " + Database.DeviceId.TABLE_NAME + " WHERE " + Database.DeviceId.ID + " = ?", new String[]{id});
        if (cursor.getCount()!=0) {

            return true;
        }
        return false;
    }

    //Delete Id From DB
    public void deleteID(){
        SQLiteDatabase d = new DatabaseHelper(context).getWritableDatabase();
        d.execSQL("DELETE FROM "+Database.DeviceId.TABLE_NAME +" WHERE " + Database.DeviceId.ID + " = '"+ id+"'" );
    }

}
