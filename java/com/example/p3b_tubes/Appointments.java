package com.example.p3b_tubes;

import java.util.ArrayList;
import java.util.List;

public class Appointments {
    List<Appointment> appointmentList;

    public Appointments(){
        this.appointmentList = new ArrayList<>();
    }

    public Appointment getAppointment(int i){
        return this.appointmentList.get(i);
    }

    public void addAppointment(Appointment appointment){
        this.appointmentList.add(appointment);
    }

    public void removeAppointment(int i){
        this.appointmentList.remove(i);
    }

    public int getSize(){
        return this.appointmentList.size();
    }

    public void save(){}

    public void load(){}
}
