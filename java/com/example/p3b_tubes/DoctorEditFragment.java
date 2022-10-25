package com.example.p3b_tubes;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.p3b_tubes.databinding.FragmentDoctorEditBinding;

public class DoctorEditFragment extends DialogFragment {
    private FragmentDoctorEditBinding fragmentDoctorEditBinding;
    private MainPresenter presenter;
    private Doctor doctor;
    private int index;

    public static DoctorEditFragment newInstance(MainPresenter presenter, Doctor doctor, int index) {
        Bundle args = new Bundle();
        DoctorEditFragment fragment = new DoctorEditFragment();
        fragment.presenter = presenter;
        fragment.doctor = doctor;
        fragment.index = index;
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        this.fragmentDoctorEditBinding = FragmentDoctorEditBinding.inflate(inflater);

        this.fragmentDoctorEditBinding.etDoctorName.setText(this.doctor.getName().substring(4));
        this.fragmentDoctorEditBinding.etDoctorSpecialty.setText(this.doctor.getSpecialty());
        this.fragmentDoctorEditBinding.etDoctorPhone.setText(this.doctor.getPhone());

        this.fragmentDoctorEditBinding.btnSave.setOnClickListener(this::onSave);
        this.fragmentDoctorEditBinding.btnCancel.setOnClickListener(this::onCancel);

        return this.fragmentDoctorEditBinding.getRoot();
    }

    private void onCancel(View view) {
        dismiss();
    }

    private void onSave(View view) {
        this.doctor.setName(this.fragmentDoctorEditBinding.etDoctorName.getText().toString());
        this.doctor.setSpecialty(this.fragmentDoctorEditBinding.etDoctorSpecialty.getText().toString());
        this.doctor.setPhone(this.fragmentDoctorEditBinding.etDoctorPhone.getText().toString());
        this.presenter.changeDoctorData(this.index, this.doctor);
        dismiss();
    }
}
