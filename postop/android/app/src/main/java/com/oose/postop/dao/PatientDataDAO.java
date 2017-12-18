package com.oose.postop.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import com.oose.postop.data.Database;
import com.oose.postop.data.DatabaseHelper;

/**Provides functions for accessing Device ID in Database
 * Created by Omotola on 10/26/2017.
 */

public class PatientDataDAO
{

    Context context;


    /**
     * Class Constructor
     * @param c
     */
    public PatientDataDAO(Context c){

        context = c;
    }


    /**
     *  Add Id to Database
     */

    public void addIDToDB(String id){

        //get the database or create the database if it doesnt exist already
        SQLiteDatabase database = new DatabaseHelper(context).getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Database.Data.ID,id);

        database.insert(Database.Data.TABLE_NAME, null, values);
    }


    /**
     * Check if Id Exists in DB
      */

    public boolean checkIdExists(Context c, String id) {

        SQLiteDatabase database = new DatabaseHelper(context).getReadableDatabase();


        Cursor cursor = database.rawQuery("SELECT * FROM " + Database.Data.TABLE_NAME + " WHERE " + Database.Data.ID + " = ?", new String[]{id});
        if (cursor.getCount()!=0) {

            return true;
        }
        return false;
    }

    /**
     * Delete Id From DB
     */

    public void deleteID(String id){
        SQLiteDatabase d = new DatabaseHelper(context).getWritableDatabase();
        d.execSQL("DELETE FROM "+ Database.Data.TABLE_NAME +" WHERE " + Database.Data.ID + " = '"+ id+"'" );
    }


    /**
     * Retrieves Id
     * @return
     */
    public String retrieveID(){
        SQLiteDatabase database = new DatabaseHelper(context).getReadableDatabase();


        Cursor c = database.rawQuery("SELECT "+ Database.Data.ID+" FROM " + Database.Data.TABLE_NAME , new String[]{});
        if(c.getCount()>0)
        {
            c.moveToFirst();
                String id = c.getString(c.getColumnIndex(Database.Data.ID));

                return id;




        }
        Toast.makeText(context,"DIDNT WORK!!!", Toast.LENGTH_LONG).show();
        return null;
    }


}
