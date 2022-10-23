package com.example.p3b_tubes;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;

import com.example.p3b_tubes.databinding.FragmentAppointmentAddBinding;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AppointmentAddFragment extends Fragment implements MainPresenter.IDoctor, MainPresenter.IAddDoctor {
    private FragmentAppointmentAddBinding fragmentAppointmentAddBinding;
    private MainPresenter presenter;
    private DoctorPickerFragment doctorPickerFragment;
    private Doctor doctor;

    private AppointmentAddFragment() {
    }

    ;

    public static AppointmentAddFragment newInstance(MainPresenter presenter) {
        Bundle args = new Bundle();
        AppointmentAddFragment fragment = new AppointmentAddFragment();
        fragment.presenter = presenter;
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        this.fragmentAppointmentAddBinding = FragmentAppointmentAddBinding.inflate(inflater);

        this.fragmentAppointmentAddBinding.tvDoctorName.setOnClickListener(this::showDoctorPickerDialog);
        this.fragmentAppointmentAddBinding.btnDate.setOnClickListener(this::showDatePickerDialog);
        this.fragmentAppointmentAddBinding.btnTime.setOnClickListener(this::showTimePickerDialog);
        this.fragmentAppointmentAddBinding.btnAddAppointment.setOnClickListener(this::addAppointment);

        getParentFragmentManager().setFragmentResultListener("setDate", this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                fragmentAppointmentAddBinding.tvDate.setText(result.getString("date"));
            }
        });

        getParentFragmentManager().setFragmentResultListener("setTime", this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                fragmentAppointmentAddBinding.tvTime.setText(result.getString("time"));
            }
        });

        return this.fragmentAppointmentAddBinding.getRoot();
    }

    private void showDoctorPickerDialog(View view) {
        this.doctorPickerFragment.show(getParentFragmentManager().beginTransaction(), "doctorPicker");
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
        String patientName = this.fragmentAppointmentAddBinding.etPatientName.getText().toString();
        String patientIssues = this.fragmentAppointmentAddBinding.etIssue.getText().toString();
        String doctorName = this.doctor.getName();
        String doctorSpecialty = this.doctor.getSpecialty();
        String doctorPhone = this.doctor.getPhone();
        String stringDate = this.fragmentAppointmentAddBinding.tvDate.getText().toString();
<<<<<<< Updated upstream
        String stringTime = " "+this.fragmentAppointmentAddBinding.tvTime.getText().toString();
=======
        String stringTime = " " + this.fragmentAppointmentAddBinding.tvTime.getText().toString();
        String patientName = this.fragmentAppointmentAddBinding.etPatientName.getText().toString();
        String issues = this.fragmentAppointmentAddBinding.etIssue.getText().toString();
        String patientPhone = this.fragmentAppointmentAddBinding.etPatientPhone.toString();
>>>>>>> Stashed changes

        Doctor doctor = new Doctor(doctorName, doctorSpecialty, doctorPhone);
        Date date = null;
        try {
            date = new SimpleDateFormat("E, dd MMM yyyy HH:mm").parse(stringDate + stringTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        this.presenter.addAppointment(patientName, patientIssues, doctor, date);

        Bundle result = new Bundle();
        result.putString("page", "appointment");
        getParentFragmentManager().setFragmentResult("changePage", result);
    }

    @Override
    public void updateListDoctor(Doctors doctors) {
        if (this.doctorPickerFragment == null)
            this.doctorPickerFragment = DoctorPickerFragment.newInstance(presenter);
        this.doctorPickerFragment.updateListDoctor(doctors);
    }

    @Override
    public void resetDoctorForm() {
    }

    @Override
    public void setDoctorToAppointment(Doctor doctor) {
        this.doctor = doctor;
        this.fragmentAppointmentAddBinding.tvDoctorName.setText(doctor.getName());
        this.fragmentAppointmentAddBinding.tvDoctorSpecialty.setText(doctor.getSpecialty());
    }

<<<<<<< Updated upstream
    public void resetForm(){
        this.fragmentAppointmentAddBinding.etPatientName.setText("");
=======
    public void resetForm() {
>>>>>>> Stashed changes
        this.fragmentAppointmentAddBinding.etIssue.setText("");
        this.fragmentAppointmentAddBinding.tvDoctorName.setText("");
        this.fragmentAppointmentAddBinding.tvDoctorSpecialty.setText("");
        this.fragmentAppointmentAddBinding.tvDate.setText("");
        this.fragmentAppointmentAddBinding.tvTime.setText("");
        InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Activity.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(), 0);
    }
}
