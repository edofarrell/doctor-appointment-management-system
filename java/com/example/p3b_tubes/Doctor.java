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
}
