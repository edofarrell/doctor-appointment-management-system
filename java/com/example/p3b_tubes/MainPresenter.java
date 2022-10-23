package com.example.p3b_tubes;

import java.util.Date;

public class MainPresenter {
    protected Doctors doctors;
    protected Appointments appointments;
    protected IDoctor uiDoctor;
    protected IAppointment uiAppointment;
    protected IAddDoctor uiAddDoctor;

    public MainPresenter(IDoctor uiDoctor, IAppointment uiAppointment, IAddDoctor uiAddDoctor){
        this.doctors = new Doctors();
        this.appointments = new Appointments();
        this.uiDoctor = uiDoctor;
        this.uiAppointment = uiAppointment;
        this.uiAddDoctor = uiAddDoctor;
    }

    public void loadDoctor(){
        this.uiDoctor.updateListDoctor(this.doctors);
    }

    public void loadAppointment(){
        this.uiAppointment.updateListAppointment(this.appointments);
    }

    public void addDoctor(String name, String specialty, String phone){
        this.doctors.addDoctor(new Doctor(name,specialty,phone));
        this.uiDoctor.updateListDoctor(this.doctors);
        this.uiDoctor.resetDoctorForm();
    }

    public void removeDoctor(int i){
        this.doctors.removeDoctor(i);
        this.uiDoctor.updateListDoctor(this.doctors);
    }

    public void addAppointment(String issues, Doctor doctor, Date date){
        this.appointments.addAppointment(new Appointment(issues, doctor,date));
        this.uiAppointment.updateListAppointment(this.appointments);
        this.uiAppointment.resetAppointmentForm();
    }

    public void removeAppointment(int i){
        this.appointments.removeAppointment(i);
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

    public void addDoctorToAppointment(int i) {
        Doctor doctor = doctors.getDoctor(i);
        this.uiAddDoctor.setDoctorToAppointment(doctor);
    }

    public interface IDoctor {
        void updateListDoctor(Doctors doctors);
        void resetDoctorForm();
    }

    public interface IAppointment {
        void updateListAppointment(Appointments appointments);
        void resetAppointmentForm();
    }

    public interface IAddDoctor {
        void setDoctorToAppointment(Doctor doctor);
    }

}
