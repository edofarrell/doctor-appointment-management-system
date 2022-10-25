package com.example.p3b_tubes;

import java.util.Date;

public class Appointment {
    private String patientName;
    private String patientIssues;
    private String patientPhone;
    private Doctor doctor;
    private Date date;
    private boolean status;

    public Appointment(String patientName, String patientIssues, String patientPhone, Doctor doctor, Date date, boolean status) {
        this.patientName = patientName;
        this.patientIssues = patientIssues;
        this.patientPhone = patientPhone;
        this.doctor = doctor;
        this.date = date;
        this.status = status;
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

    public boolean isStatus() {
        return status;
    }

    public void updateStatus() {
        this.status = true;
    }
}
