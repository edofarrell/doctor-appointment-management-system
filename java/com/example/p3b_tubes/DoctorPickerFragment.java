package com.example.p3b_tubes;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import com.example.p3b_tubes.databinding.FragmentDoctorPickerBinding;

import java.util.List;

public class DoctorPickerFragment extends DialogFragment implements MainPresenter.IDoctor{
    FragmentDoctorPickerBinding fragmentDoctorPickerBinding;
    DoctorPickerAdapter doctorPickerAdapter;
    MainPresenter presenter;

    public static DoctorPickerFragment newInstance(MainPresenter presenter) {
        Bundle args = new Bundle();
        DoctorPickerFragment doctorPickerFragment = new DoctorPickerFragment();
        doctorPickerFragment.presenter = presenter;
        return doctorPickerFragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        fragmentDoctorPickerBinding = FragmentDoctorPickerBinding.inflate(inflater, container, false);

        doctorPickerAdapter = new DoctorPickerAdapter();
        fragmentDoctorPickerBinding.lstPickerDoctor.setAdapter(doctorPickerAdapter);
        presenter.loadDoctor();
        return fragmentDoctorPickerBinding.getRoot();
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        presenter.loadDoctor();
        builder.setView(inflater.inflate(R.layout.fragment_doctor_picker, null));
        return builder.create();
    }

    @Override
    public void updateListDoctor(List<Doctor> doctors) {
        doctorPickerAdapter.update(doctors);
    }
}
