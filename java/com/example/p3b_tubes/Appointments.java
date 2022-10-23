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

    public Appointments() {
        this.appointmentList = new ArrayList<>();
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

    public Appointments search(String s){
        List<Appointment> newList = new ArrayList<>(this.appointmentList);
        for (int i=newList.size()-1;i>=0; i--){
            Doctor doctor = newList.get(i).getDoctor();
            String patientName = newList.get(i).patientName;
            if(!doctor.getName().contains(s) && !doctor.getSpecialty().contains(s) && !patientName.contains(s)){
                newList.remove(i);
            }
        }

        Appointments newAppointments = new Appointments();
        newAppointments.appointmentList = newList;
        return newAppointments;
    }

    public void save(DatabaseHelper database) {
        SQLiteDatabase db = database.getWritableDatabase();

        for (int i = 0; i < this.appointmentList.size(); i++) {
            ContentValues values = new ContentValues();
            values.put(DatabaseContract.AppointmentEntry.COLUMN_PATIENT_NAME, this.appointmentList.get(i).getPatientName());
            values.put(DatabaseContract.AppointmentEntry.COLUMN_PATIENT_ISSUES, this.appointmentList.get(i).getPatientIssues());
            values.put(DatabaseContract.AppointmentEntry.COLUMN_PATIENT_PHONE, this.appointmentList.get(i).getPatientPhone());
            values.put(DatabaseContract.AppointmentEntry.COLUMN_DOCTOR_NAME, this.appointmentList.get(i).getDoctor().getName());
            values.put(DatabaseContract.AppointmentEntry.COLUMN_DOCTOR_SPECIALTY, this.appointmentList.get(i).getDoctor().getSpecialty());
            values.put(DatabaseContract.AppointmentEntry.COLUMN_DOCTOR_PHONE, this.appointmentList.get(i).getDoctor().getPhone());
            String date = new SimpleDateFormat("E, dd MMM yyyy HH:mm").format(this.appointmentList.get(i).getDate());
            values.put(DatabaseContract.AppointmentEntry.COLUMN_DATE, date);

            db.insert(DatabaseContract.AppointmentEntry.TABLE_NAME, null, values);
        }
    }

    public void load(DatabaseHelper database) {
        SQLiteDatabase db = database.getReadableDatabase();

        String[] projection = {
                BaseColumns._ID,
                DatabaseContract.AppointmentEntry.COLUMN_PATIENT_NAME,
                DatabaseContract.AppointmentEntry.COLUMN_PATIENT_ISSUES,
                DatabaseContract.AppointmentEntry.COLUMN_PATIENT_PHONE,
                DatabaseContract.AppointmentEntry.COLUMN_DOCTOR_NAME,
                DatabaseContract.AppointmentEntry.COLUMN_DOCTOR_SPECIALTY,
                DatabaseContract.AppointmentEntry.COLUMN_DOCTOR_PHONE,
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
            String patientName = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.AppointmentEntry.COLUMN_PATIENT_NAME));
            String patientIssues = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.AppointmentEntry.COLUMN_PATIENT_ISSUES));
            String patientPhone = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.AppointmentEntry.COLUMN_PATIENT_PHONE));
            String doctorName = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.AppointmentEntry.COLUMN_DOCTOR_NAME));
            String specialty = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.AppointmentEntry.COLUMN_DOCTOR_SPECIALTY));
            String doctorPhone = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.AppointmentEntry.COLUMN_DOCTOR_PHONE));
            String dateString = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.AppointmentEntry.COLUMN_DATE));
            Doctor doctor = new Doctor(doctorName, specialty, doctorPhone);
            Date date = null;
            try {
                date = new SimpleDateFormat("E, dd MMM yyyy HH:mm").parse(dateString);
            } catch (ParseException e) {
                e.printStackTrace();
            }

            this.addAppointment(new Appointment(patientName, patientIssues, patientPhone, doctor, date));
        }
    }
}
