package com.example.p3b_tubes;

import java.util.Date;

public class Appointment {
    Doctor doctor;
    Date date;
    String time;

    public Appointment(Doctor doctor, Date date, String time){
        this.doctor = doctor;
        this.date = date;
        this.time = time;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public Date getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }
}
