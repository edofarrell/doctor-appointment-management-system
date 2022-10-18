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
import java.util.Calendar;

public class AppointmentAddFragment extends Fragment {
    private FragmentAppointmentAddBinding fragmentAppointmentAddBinding;
    private Doctor doctor;
    private Calendar calendar;
    private MainPresenter presenter;

    private AppointmentAddFragment(){};

    public static AppointmentAddFragment newInstance(MainPresenter presenter) {
        Bundle args = new Bundle();
        args.putSerializable("presenter", presenter);
        AppointmentAddFragment fragment = new AppointmentAddFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        this.fragmentAppointmentAddBinding = FragmentAppointmentAddBinding.inflate(inflater);

        this.calendar = Calendar.getInstance();
        this.presenter = (MainPresenter) this.getArguments().getSerializable("presenter");
        this.fragmentAppointmentAddBinding.btnAddAppointment.setOnClickListener(this::addAppointment);
        this.fragmentAppointmentAddBinding.btnDate.setOnClickListener(this::showDatePickerDialog);
        this.fragmentAppointmentAddBinding.btnTime.setOnClickListener(this::showTimePickerDialog);

        getParentFragmentManager().setFragmentResultListener("setDate", this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                Calendar inputDate = (Calendar) result.getSerializable("date");
                calendar.set(Calendar.DATE, inputDate.get(Calendar.DATE));
                calendar.set(Calendar.MONTH, inputDate.get(Calendar.MONTH));
                calendar.set(Calendar.YEAR, inputDate.get(Calendar.YEAR));
                fragmentAppointmentAddBinding.tvDate.setText(DateFormat.getDateInstance(DateFormat.FULL).format(inputDate.getTime()));
            }});

        getParentFragmentManager().setFragmentResultListener("setTime", this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                Calendar inputTime = (Calendar) result.getSerializable("time");
                calendar.set(Calendar.HOUR_OF_DAY, inputTime.get(Calendar.HOUR_OF_DAY));
                calendar.set(Calendar.MINUTE, inputTime.get(Calendar.MINUTE));
                fragmentAppointmentAddBinding.tvTime.setText(new SimpleDateFormat("HH:mm").format(inputTime.getTime()));
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
        this.doctor = new Doctor(this.fragmentAppointmentAddBinding.etDoctor.getText().toString(), this.fragmentAppointmentAddBinding.etSpecialty.getText().toString());
        this.presenter.addAppointment(this.doctor, this.calendar.getTime());

        Bundle result = new Bundle();
        result.putString("page", "appointment");
        getParentFragmentManager().setFragmentResult("changePage", result);
    }
}
