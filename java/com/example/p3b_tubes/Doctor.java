package com.example.p3b_tubes;

public class Doctor {
    String name;
    String specialty;
    String phone;

    public Doctor(String name, String specialty, String phone){
        this.name = name;
        this.specialty = specialty;
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public String getSpecialty() {
        return specialty;
    }

    public String getPhone() {
        return phone;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
