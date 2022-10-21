package com.example.p3b_tubes;

import android.provider.BaseColumns;

public final class DatabaseContract {
    private DatabaseContract(){}

    public static class DoctorEntry implements BaseColumns {
        public static final String TABLE_NAME = "Doctor";
        public static final String COLUMN_NAME = "Name";
        public static final String COLUMN_SPECIALTY = "Specialty";
    }

    public static class AppointmentEntry implements BaseColumns {
        public static final String TABLE_NAME = "Appointment";
        public static final String COLUMN_PATIENT_NAME = "Patient_Name";
        public static final String COLUMN_PATIENT_ISSUES = "Patient_Issues";
        public static final String COLUMN_DOCTOR_NAME = "Doctor_Name";
        public static final String COLUMN_DOCTOR_SPECIALTY = "Doctor_Specialty";
        public static final String COLUMN_DATE = "Date";
    }
}
