package com.example.p3b_tubes;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainPresenter {
    protected List<Doctor> doctors;
    protected List<Appointment> appointments;
    protected IDoctor uiDoctor;
    protected IAppointment uiAppointment;

    public MainPresenter(IDoctor uiDoctor, IAppointment uiAppointment){
        this.doctors = new ArrayList<>();
        this.appointments = new ArrayList<>();
        this.uiDoctor = uiDoctor;
        this.uiAppointment = uiAppointment;
    }

    public void loadDoctor(){
        this.uiDoctor.updateListDoctor(this.doctors);
    }

    public void loadDoctor(List<Doctor> doctors){
        this.doctors = doctors;
        this.uiDoctor.updateListDoctor(this.doctors);
    }

    public void loadAppointment(){
        this.uiAppointment.updateListAppointment(this.appointments);
    }

    public void addDoctor(String name, String specialty){
        this.doctors.add(new Doctor(name,specialty));
        this.uiDoctor.updateListDoctor(this.doctors);
    }

    public void addAppointment(Doctor doctor, Date date){
        this.appointments.add(new Appointment(doctor,date));
        this.uiAppointment.updateListAppointment(this.appointments);
    }

    public List<Doctor> getDoctors(){
        return this.doctors;
    }

    public interface IDoctor {
        void updateListDoctor(List<Doctor> doctors);
    }

    public interface IAppointment {
        void updateListAppointment(List<Appointment> appointments);
    }
}
