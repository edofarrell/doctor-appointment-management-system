package com.example.p3b_tubes;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

import java.util.ArrayList;
import java.util.List;

public class Doctors {
    List<Doctor> doctorList;
    DatabaseHelper database;

    public Doctors(DatabaseHelper database) {
        this.doctorList = new ArrayList<>();
        this.database = database;
    }

    public Doctor getDoctor(int i) {
        return this.doctorList.get(i);
    }

    public void addDoctor(Doctor doctor) {
        this.doctorList.add(doctor);
    }

    public void removeDoctor(int i) {
        this.doctorList.remove(i);
    }

    public int getSize() {
        return this.doctorList.size();
    }

    public void save() {
        SQLiteDatabase db = this.database.getWritableDatabase();

        for (int i = 0; i < this.doctorList.size(); i++) {
            ContentValues values = new ContentValues();
            values.put(DatabaseContract.DoctorEntry.COLUMN_NAME, this.doctorList.get(i).getName());
            values.put(DatabaseContract.DoctorEntry.COLUMN_SPECIALTY, this.doctorList.get(i).getSpecialty());
            db.insert(DatabaseContract.DoctorEntry.TABLE_NAME, null, values);
        }
    }

    public void load(){
        SQLiteDatabase db = this.database.getReadableDatabase();

        String[] projection = {
                BaseColumns._ID,
                DatabaseContract.DoctorEntry.COLUMN_NAME,
                DatabaseContract.DoctorEntry.COLUMN_SPECIALTY
        };

        Cursor cursor = db.query(
                DatabaseContract.DoctorEntry.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                null
        );

        while(cursor.moveToNext()){
            String name = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.DoctorEntry.COLUMN_NAME));
            String specialty = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.DoctorEntry.COLUMN_SPECIALTY));
            this.addDoctor(new Doctor(name,specialty));
        }
    }
}
