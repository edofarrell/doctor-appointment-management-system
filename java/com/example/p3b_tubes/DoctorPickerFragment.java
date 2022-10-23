package com.example.p3b_tubes;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.p3b_tubes.databinding.FragmentDoctorPickerBinding;

public class DoctorPickerFragment extends DialogFragment implements MainPresenter.IDoctor{
    FragmentDoctorPickerBinding fragmentDoctorPickerBinding;
    DoctorPickerAdapter doctorPickerAdapter;
    MainPresenter presenter;

    public static DoctorPickerFragment newInstance(MainPresenter presenter) {
        Bundle args = new Bundle();
        DoctorPickerFragment doctorPickerFragment = new DoctorPickerFragment();
        doctorPickerFragment.presenter = presenter;
        doctorPickerFragment.doctorPickerAdapter = new DoctorPickerAdapter(presenter);
        doctorPickerFragment.setArguments(args);
        return doctorPickerFragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        this.fragmentDoctorPickerBinding = FragmentDoctorPickerBinding.inflate(inflater, container, false);
        this.fragmentDoctorPickerBinding.lstPickerDoctor.setAdapter(doctorPickerAdapter);
        this.presenter.loadDoctor();
        return fragmentDoctorPickerBinding.getRoot();
    }

    @Override
    public void updateListDoctor(Doctors doctors) {
        this.doctorPickerAdapter.update(doctors);
    }

    @Override
    public void resetDoctorForm() {}
}
