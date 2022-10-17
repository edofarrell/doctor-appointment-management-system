package com.example.p3b_tubes;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;

import com.example.p3b_tubes.databinding.FragmentAppointmentAddBinding;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AppointmentAddFragment extends Fragment {
    FragmentAppointmentAddBinding fragmentAppointmentAddBinding;

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
        this.fragmentAppointmentAddBinding = FragmentAppointmentAddBinding.inflate(inflater);
        
        this.fragmentAppointmentAddBinding.btnAddAppointment.setOnClickListener(this::addAppointment);
        this.fragmentAppointmentAddBinding.btnDate.setOnClickListener(this::showDatePickerDialog);
        this.fragmentAppointmentAddBinding.btnTime.setOnClickListener(this::showTimePickerDialog);

        getParentFragmentManager().setFragmentResultListener("setDate", this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                Date date = (Date) result.getSerializable("date");
                fragmentAppointmentAddBinding.tvDate.setText(DateFormat.getDateInstance(DateFormat.FULL).format(date));
            }
        });

        getParentFragmentManager().setFragmentResultListener("setTime", this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                Date time = (Date) result.getSerializable("time");
                fragmentAppointmentAddBinding.tvTime.setText(new SimpleDateFormat("HH:mm").format(time));
            }
        });
        
        return this.fragmentAppointmentAddBinding.getRoot();
    }

    private void showTimePickerDialog(View view) {
        DialogFragment newFragment = TimePickerFragment.newInstance();
        newFragment.show(getParentFragmentManager(), "timePicker");
    }

    private void showDatePickerDialog(View view) {
        DialogFragment newFragment = DatePickerFragment.newInstance();
        newFragment.show(getParentFragmentManager(), "datePicker");
    }

    private void addAppointment(View view) {
    }
}
