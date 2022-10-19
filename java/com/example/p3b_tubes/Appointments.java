package com.example.p3b_tubes;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Appointments {
    List<Appointment> appointmentList;
    DatabaseHelper database;

    public Appointments(DatabaseHelper database) {
        this.appointmentList = new ArrayList<>();
        this.database = database;
    }

    public Appointment getAppointment(int i) {
        return this.appointmentList.get(i);
    }

    public void addAppointment(Appointment appointment) {
        this.appointmentList.add(appointment);
    }

    public void removeAppointment(int i) {
        this.appointmentList.remove(i);
    }

    public int getSize() {
        return this.appointmentList.size();
    }

    public void save() {
        SQLiteDatabase db = this.database.getWritableDatabase();

        for (int i = 0; i < this.appointmentList.size(); i++) {
            ContentValues values = new ContentValues();
            values.put(DatabaseContract.AppointmentEntry.COLUMN_DOCTOR_NAME, this.appointmentList.get(i).getDoctor().getName());
            values.put(DatabaseContract.AppointmentEntry.COLUMN_DOCTOR_SPECIALTY, this.appointmentList.get(i).getDoctor().getSpecialty());
            values.put(DatabaseContract.AppointmentEntry.COLUMN_DATE, this.appointmentList.get(i).getDate().toString());
            db.insert(DatabaseContract.AppointmentEntry.TABLE_NAME, null, values);
        }
    }

    public void load() {
        SQLiteDatabase db = this.database.getReadableDatabase();

        String[] projection = {
                BaseColumns._ID,
                DatabaseContract.AppointmentEntry.COLUMN_DOCTOR_NAME,
                DatabaseContract.AppointmentEntry.COLUMN_DOCTOR_SPECIALTY,
                DatabaseContract.AppointmentEntry.COLUMN_DATE
        };

        Cursor cursor = db.query(
                DatabaseContract.AppointmentEntry.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                null
        );

        while (cursor.moveToNext()) {
            String doctorName = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.AppointmentEntry.COLUMN_DOCTOR_NAME));
            String specialty = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.AppointmentEntry.COLUMN_DOCTOR_SPECIALTY));
            String dateString = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.AppointmentEntry.COLUMN_DATE));

            Doctor doctor = new Doctor(doctorName, specialty);
            Date date = null;
            try {
                date = new SimpleDateFormat("E, dd MMM yyyy HH:mm").parse(dateString);
            } catch (ParseException e) {
                e.printStackTrace();
            }

            this.appointmentList.add(new Appointment(doctor, date));
        }
    }
}
