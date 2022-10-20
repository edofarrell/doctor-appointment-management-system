package com.example.p3b_tubes;

import java.util.Date;

public class MainPresenter {
    protected Doctors doctors;
    protected Appointments appointments;
    protected IDoctor uiDoctor;
    protected IAppointment uiAppointment;

    public MainPresenter(IDoctor uiDoctor, IAppointment uiAppointment){
        this.doctors = new Doctors();
        this.appointments = new Appointments();
        this.uiDoctor = uiDoctor;
        this.uiAppointment = uiAppointment;
    }

    public void loadDoctor(){
        this.uiDoctor.updateListDoctor(this.doctors);
    }

    public void loadAppointment(){
        this.uiAppointment.updateListAppointment(this.appointments);
    }

    public void addDoctor(String name, String specialty){
        this.doctors.addDoctor(new Doctor(name,specialty));
        this.uiDoctor.updateListDoctor(this.doctors);
    }

    public void addAppointment(Doctor doctor, Date date){
        this.appointments.addAppointment(new Appointment(doctor,date));
        this.uiAppointment.updateListAppointment(this.appointments);
    }

    public void saveToDatabase(DatabaseHelper database){
        this.doctors.save(database);
        this.appointments.save(database);
    }

    public void loadFromDatabase(DatabaseHelper database){
        this.doctors.load(database);
        this.uiDoctor.updateListDoctor(this.doctors);
        this.appointments.load(database);
        this.uiAppointment.updateListAppointment(this.appointments);
    }

    public interface IDoctor {
        void updateListDoctor(Doctors doctors);
    }

    public interface IAppointment {
        void updateListAppointment(Appointments appointments);
    }
}
