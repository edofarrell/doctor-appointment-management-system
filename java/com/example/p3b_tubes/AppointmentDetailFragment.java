package com.example.p3b_tubes;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.p3b_tubes.databinding.FragmentAppointmentDetailBinding;

import java.text.SimpleDateFormat;

public class AppointmentDetailFragment extends DialogFragment {
    private FragmentAppointmentDetailBinding fragmentAppointmentDetailBinding;
    private Appointment appointment;

    public static AppointmentDetailFragment newInstance(Appointment appointment) {
        Bundle args = new Bundle();
        AppointmentDetailFragment fragment = new AppointmentDetailFragment();
        fragment.appointment = appointment;
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        this.fragmentAppointmentDetailBinding = FragmentAppointmentDetailBinding.inflate(inflater, container, false);

        this.fragmentAppointmentDetailBinding.tvDoctorName.setText(this.appointment.getDoctor().getName());
        this.fragmentAppointmentDetailBinding.tvDoctorSpecialty.setText(this.appointment.getDoctor().getSpecialty());
        this.fragmentAppointmentDetailBinding.tvDoctorPhone.setText(this.appointment.getDoctor().getPhone());
        this.fragmentAppointmentDetailBinding.tvPatientName.setText(this.appointment.getPatientName());
        this.fragmentAppointmentDetailBinding.tvPatientIssues.setText(this.appointment.getPatientIssues());
        this.fragmentAppointmentDetailBinding.tvPatientPhone.setText(this.appointment.getPatientPhone());
        this.fragmentAppointmentDetailBinding.tvDate.setText(new SimpleDateFormat("dd MMM yyyy").format(this.appointment.getDate()));
        this.fragmentAppointmentDetailBinding.tvTime.setText(new SimpleDateFormat("HH:mm").format(this.appointment.getDate()));

        return this.fragmentAppointmentDetailBinding.getRoot();
    }
}
