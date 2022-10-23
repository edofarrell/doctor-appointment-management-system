package com.example.p3b_tubes;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.p3b_tubes.databinding.FragmentDoctorBinding;

public class DoctorFragment extends Fragment implements MainPresenter.IDoctor {
    FragmentDoctorBinding fragmentDoctorBinding;
    DoctorListAdapter doctorListAdapter;
    MainPresenter presenter;

    private DoctorFragment() {}

    public static DoctorFragment newInstance(MainPresenter presenter) {
        Bundle args = new Bundle();
        DoctorFragment doctorFragment = new DoctorFragment();
        doctorFragment.presenter = presenter;
        doctorFragment.doctorListAdapter = new DoctorListAdapter(presenter);
        doctorFragment.setArguments(args);
        return doctorFragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        this.fragmentDoctorBinding = FragmentDoctorBinding.inflate(inflater);

        this.fragmentDoctorBinding.lstFoods.setAdapter(doctorListAdapter);
        this.fragmentDoctorBinding.btnAddDoctor.setOnClickListener(this::addDoctor);

        this.presenter.loadDoctor();

        return fragmentDoctorBinding.getRoot();
    }

    private void addDoctor(View view) {
        Bundle result = new Bundle();
        result.putString("page", "doctorAdd");
        getParentFragmentManager().setFragmentResult("changePage", result);
    }

    @Override
    public void updateListDoctor(Doctors doctors) {
        this.doctorListAdapter.update(doctors);
    }

    @Override
    public void resetDoctorForm() {
    }
}
