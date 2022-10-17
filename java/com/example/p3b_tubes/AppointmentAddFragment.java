package com.example.p3b_tubes;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.p3b_tubes.databinding.FragmentAppointmentAddBinding;
import com.example.p3b_tubes.databinding.FragmentAppointmentBinding;

public class AppointmentAddFragment extends Fragment {
    FragmentAppointmentAddBinding fragmentAppointmentAddBindingBinding;

    private AppointmentAddFragment(){};

    public static AppointmentAddFragment newInstance() {
        Bundle args = new Bundle();
        AppointmentAddFragment fragment = new AppointmentAddFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        this.fragmentAppointmentAddBindingBinding = FragmentAppointmentAddBinding.inflate(inflater);
        
        this.fragmentAppointmentAddBindingBinding.btnAddAppointment.setOnClickListener(this::addAppointment);
        
        return this.fragmentAppointmentAddBindingBinding.getRoot();
    }

    private void addAppointment(View view) {
    }
}
