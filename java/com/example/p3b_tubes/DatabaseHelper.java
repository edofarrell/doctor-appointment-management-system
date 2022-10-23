package com.example.p3b_tubes;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "database.db";

    private static final String SQL_CREATE_ENTRIES_DOCTOR =
            "CREATE TABLE " + DatabaseContract.DoctorEntry.TABLE_NAME + " (" +
                    DatabaseContract.DoctorEntry._ID + " INTEGER PRIMARY KEY," +
                    DatabaseContract.DoctorEntry.COLUMN_NAME + " varchar(255)," +
                    DatabaseContract.DoctorEntry.COLUMN_SPECIALTY + " varchar(255)," +
                    DatabaseContract.DoctorEntry.COLUMN_PHONE + " varchar(255))";

    private static final String SQL_CREATE_ENTRIES_APPOINTMENT =
            "CREATE TABLE " + DatabaseContract.AppointmentEntry.TABLE_NAME + " (" +
                    DatabaseContract.AppointmentEntry._ID + " INTEGER PRIMARY KEY," +
                    DatabaseContract.AppointmentEntry.COLUMN_PATIENT_NAME + " varchar(255)," +
                    DatabaseContract.AppointmentEntry.COLUMN_PATIENT_ISSUES + " varchar(255), " +
                    DatabaseContract.AppointmentEntry.COLUMN_DOCTOR_NAME + " varchar(255)," +
                    DatabaseContract.AppointmentEntry.COLUMN_DOCTOR_SPECIALTY + " varchar(255)," +
                    DatabaseContract.AppointmentEntry.COLUMN_DOCTOR_PHONE + " varchar(255)," +
                    DatabaseContract.AppointmentEntry.COLUMN_DATE + " varchar(255))";

    private static final String SQL_DELETE_ENTRIES_DOCTOR =
            "DROP TABLE IF EXISTS " + DatabaseContract.DoctorEntry.TABLE_NAME;

    private static final String SQL_DELETE_ENTRIES_APPOINTMENT =
            "DROP TABLE IF EXISTS " + DatabaseContract.AppointmentEntry.TABLE_NAME;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES_DOCTOR);
        db.execSQL(SQL_CREATE_ENTRIES_APPOINTMENT);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_ENTRIES_DOCTOR);
        db.execSQL(SQL_DELETE_ENTRIES_APPOINTMENT);
        onCreate(db);
    }
}
