package com.oose.postop;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by Omotola on 10/26/2017.
 */

public class DeviceIdDAO
{

    Context context;

    public DeviceIdDAO( Context c){

        context = c;
    }


    //Add Id to Database
    public void addIDToDB(String id){

        //get the database or create the database if it doesnt exist already
        SQLiteDatabase database = new DatabaseHelper(context).getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Database.DeviceId.ID,id);

        database.insert(Database.DeviceId.TABLE_NAME, null, values);
    }


    //Check if Id Exists in DB
    public boolean checkIdExists(Context c, String id) {

        SQLiteDatabase database = new DatabaseHelper(context).getReadableDatabase();


        Cursor cursor = database.rawQuery("SELECT * FROM " + Database.DeviceId.TABLE_NAME + " WHERE " + Database.DeviceId.ID + " = ?", new String[]{id});
        if (cursor.getCount()!=0) {

            return true;
        }
        return false;
    }

    //Delete Id From DB
    public void deleteID(String id){
        SQLiteDatabase d = new DatabaseHelper(context).getWritableDatabase();
        d.execSQL("DELETE FROM "+Database.DeviceId.TABLE_NAME +" WHERE " + Database.DeviceId.ID + " = '"+ id+"'" );
    }

    public String retrieveID(){
        SQLiteDatabase database = new DatabaseHelper(context).getReadableDatabase();


        Cursor c = database.rawQuery("SELECT "+Database.DeviceId.ID+" FROM " + Database.DeviceId.TABLE_NAME , new String[]{});
        if(c != null)
        {
            c.moveToFirst();
                String id = c.getString(c.getColumnIndex(Database.DeviceId.ID));
                //Toast.makeText(context, id, Toast.LENGTH_LONG).show();
                return id;


                // use these strings as you want

        }
        Toast.makeText(context,"DIDNT WORK!!!", Toast.LENGTH_LONG).show();
        return null;
    }

}
