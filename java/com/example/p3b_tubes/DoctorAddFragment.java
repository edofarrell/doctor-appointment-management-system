package com.example.p3b_tubes;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.p3b_tubes.databinding.FragmentDoctorAddBinding;

public class DoctorAddFragment extends Fragment {
    FragmentDoctorAddBinding fragmentDoctorAddBinding;
    MainPresenter presenter;

    public static DoctorAddFragment newInstance(MainPresenter presenter) {
        Bundle args = new Bundle();
        DoctorAddFragment doctorAddFragment = new DoctorAddFragment();
        doctorAddFragment.presenter = presenter;
        doctorAddFragment.setArguments(args);
        return doctorAddFragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        this.fragmentDoctorAddBinding = FragmentDoctorAddBinding.inflate(inflater);
        this.fragmentDoctorAddBinding.btnAddNewDoctor.setOnClickListener(this::onClick);
        return fragmentDoctorAddBinding.getRoot();
    }

    private void onClick(View view) {
        String name =  this.fragmentDoctorAddBinding.etDoctorAddName.getEditText().getText().toString();
        String specialty =  this.fragmentDoctorAddBinding.etDoctorAddSpecialty.getEditText().getText().toString();
        this.presenter.addDoctor(name, specialty);

        Bundle result = new Bundle();
        result.putString("page", "doctor");
        getParentFragmentManager().setFragmentResult("changePage", result);
    }
}
