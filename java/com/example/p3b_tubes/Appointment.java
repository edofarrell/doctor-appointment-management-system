package com.example.p3b_tubes;

import java.util.Date;

public class Appointment {
    Doctor doctor;
    Date date;

    public Appointment(Doctor doctor, Date date){
        this.doctor = doctor;
        this.date = date;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public Date getDate() {
        return date;
    }

}
