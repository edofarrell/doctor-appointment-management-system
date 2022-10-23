package com.example.p3b_tubes;

import java.util.Date;

public class Appointment {
    String patientName;
    String patientIssues;
    String patientPhone;
    Doctor doctor;
    Date date;

    public Appointment(String patientName, String patientIssues, String patientPhone, Doctor doctor, Date date){
        this.patientName = patientName;
        this.patientIssues = patientIssues;
        this.patientPhone = patientPhone;
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

    public String getPatientName() {
        return patientName;
    }

    public String getPatientPhone() {
        return patientPhone;
    }
}
