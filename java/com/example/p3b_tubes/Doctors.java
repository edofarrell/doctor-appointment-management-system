package com.example.p3b_tubes;

import android.content.ContentValues;

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

    public void save() {
//        for (int i = 0; i < this.doctorList.size(); i++) {
//            ContentValues values = new ContentValues();
//            values.put(DoctorReaderContract.DoctorEntry.COLUMN_NAME_NAME, this.doctorList.get(i).getName());
//            values.put(DoctorReaderContract.DoctorEntry.COLUMN_NAME_SPECIALTY, this.doctorList.get(i).getSpecialty());
//            db.insert(DoctorReaderContract.DoctorEntry.TABLE_NAME, null, values);
//        }
    }

    public void load(){}
}
