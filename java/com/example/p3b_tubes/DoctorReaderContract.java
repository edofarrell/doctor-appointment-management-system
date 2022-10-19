package com.example.p3b_tubes;

import android.provider.BaseColumns;

public final class DoctorReaderContract {
    private DoctorReaderContract(){}

    public static class DoctorEntry implements BaseColumns {
        public static final String TABLE_NAME = "Doctor";
        public static final String COLUMN_NAME_NAME = "Name";
        public static final String COLUMN_NAME_SPECIALTY = "Specialty";
    }
}
