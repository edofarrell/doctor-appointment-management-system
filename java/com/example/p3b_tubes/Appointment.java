package com.example.p3b_tubes;

import java.util.Date;

public class Appointment {
    String patientIssues;
    Doctor doctor;
    Date date;

    public Appointment(String patientIssues, Doctor doctor, Date date){
        this.patientIssues = patientIssues;
        this.doctor = doctor;
        this.date = date;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public Date getDate() {
        return date;
    }

    public String getPatientIssues() {
        return patientIssues;
    }
}
