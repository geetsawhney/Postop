package com.oose.postop;

import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

/**
 * Created by Omotola on 10/26/2017.
 */

public class Database {
    private Database() {
    }

    public static class DeviceId implements BaseColumns {
        public static final String TABLE_NAME = "SavedDeviceID";
        public static final String ID = "id";

        public static final String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS " +
                TABLE_NAME + " ( " +
                ID + " TEXT " +
               ")";



    }



}
