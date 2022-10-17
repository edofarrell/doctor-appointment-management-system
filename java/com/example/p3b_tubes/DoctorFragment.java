package com.example.p3b_tubes;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.p3b_tubes.databinding.FragmentDoctorBinding;

public class DoctorFragment extends Fragment {
    FragmentDoctorBinding fragmentDoctorBinding;
    DoctorListAdapter doctorListAdapter;

    private DoctorFragment(){};

    public static DoctorFragment newInstance() {
        Bundle args = new Bundle();
        DoctorFragment doctorFragment = new DoctorFragment();
        doctorFragment.setArguments(args);
        return doctorFragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        fragmentDoctorBinding = FragmentDoctorBinding.inflate(inflater);

        doctorListAdapter = new DoctorListAdapter();
        fragmentDoctorBinding.lstFoods.setAdapter(doctorListAdapter);
        fragmentDoctorBinding.btnAddDoctor.setOnClickListener(this::addDoctor);
        
        return fragmentDoctorBinding.getRoot();
    }

    private void addDoctor(View view) {
    }
}
