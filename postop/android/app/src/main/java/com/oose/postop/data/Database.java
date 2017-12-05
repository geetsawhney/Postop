package com.oose.postop.data;

import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

/**Defines Databases and Attributes
 * Created by Omotola on 10/26/2017.
 */

public class Database {
    private Database() {
    }

    /**
     * Creates DeviceID Table
     */
    public static class Data implements BaseColumns {
        public static final String TABLE_NAME = "PatientData";
        public static final String ID = "id";
        public static final String INTERVAL = "notificationInterval";

        public static final String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS " +
                TABLE_NAME + " ( " +
                ID + " TEXT, " +
                INTERVAL +" NUMBER "+
               ")";



    }



}
