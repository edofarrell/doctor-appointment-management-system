package com.example.p3b_tubes;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DoctorReaderDbHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "DoctorReader.db";
    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + DoctorReaderContract.DoctorEntry.TABLE_NAME + " (" +
                    DoctorReaderContract.DoctorEntry._ID + " INTEGER PRIMARY KEY," +
                    DoctorReaderContract.DoctorEntry.COLUMN_NAME_NAME + " varchar(255)," +
                    DoctorReaderContract.DoctorEntry.COLUMN_NAME_SPECIALTY + " varchar(255))";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + DoctorReaderContract.DoctorEntry.TABLE_NAME;

    public DoctorReaderDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }
}
