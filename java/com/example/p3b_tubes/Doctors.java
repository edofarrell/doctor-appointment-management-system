package com.example.p3b_tubes;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

import java.util.ArrayList;
import java.util.List;

public class Doctors {
    List<Doctor> doctorList;

    public Doctors() {
        this.doctorList = new ArrayList<>();
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

    public void changeDoctor(int i,Doctor doctor){
        this.doctorList.set(i, doctor);
    }

    public Doctors search(String s){
        List<Doctor> newList = new ArrayList<>(this.doctorList);
        for (int i=newList.size()-1;i>=0; i--){
            if(!newList.get(i).getName().contains(s) && !newList.get(i).getSpecialty().contains(s)){
                newList.remove(i);
            }
        }

        Doctors newDoctors = new Doctors();
        newDoctors.doctorList = newList;
        return newDoctors;
    }

    public void save(DatabaseHelper database) {
        SQLiteDatabase db = database.getWritableDatabase();

        for (int i = 0; i < this.doctorList.size(); i++) {
            ContentValues values = new ContentValues();
            values.put(DatabaseContract.DoctorEntry.COLUMN_NAME, this.doctorList.get(i).getName());
            values.put(DatabaseContract.DoctorEntry.COLUMN_SPECIALTY, this.doctorList.get(i).getSpecialty());
            values.put(DatabaseContract.DoctorEntry.COLUMN_PHONE, this.doctorList.get(i).getPhone());
            db.insert(DatabaseContract.DoctorEntry.TABLE_NAME, null, values);
        }
    }

    public void load(DatabaseHelper database) {
        SQLiteDatabase db = database.getReadableDatabase();

        String[] projection = {
                BaseColumns._ID,
                DatabaseContract.DoctorEntry.COLUMN_NAME,
                DatabaseContract.DoctorEntry.COLUMN_SPECIALTY,
                DatabaseContract.DoctorEntry.COLUMN_PHONE
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

        while (cursor.moveToNext()) {
            String name = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.DoctorEntry.COLUMN_NAME));
            String specialty = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.DoctorEntry.COLUMN_SPECIALTY));
            String phone = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.DoctorEntry.COLUMN_PHONE));
            this.addDoctor(new Doctor(name, specialty, phone));
        }
    }
}
