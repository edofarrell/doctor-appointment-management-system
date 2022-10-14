package com.example.p3b_tubes;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.p3b_tubes.databinding.FragmentAppointmentBinding;

public class AppointmentFragment extends Fragment {
    FragmentAppointmentBinding fragmentAppointmentBinding;

    private AppointmentFragment(){}

    public static AppointmentFragment newInstance() {
        Bundle args = new Bundle();
        AppointmentFragment fragment = new AppointmentFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        this.fragmentAppointmentBinding = FragmentAppointmentBinding.inflate(inflater);
        return this.fragmentAppointmentBinding.getRoot();
    }
}
