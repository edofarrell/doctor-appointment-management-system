package com.example.p3b_tubes;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.p3b_tubes.databinding.FragmentDoctorAddBinding;
import com.example.p3b_tubes.databinding.FragmentDoctorBinding;

public class DoctorAddFragment extends Fragment {
    FragmentDoctorAddBinding fragmentDoctorAddBinding;
    MainPresenter presenter;

    public static DoctorAddFragment newInstance(MainPresenter presenter) {
        Bundle args = new Bundle();
        args.putSerializable("presenter", presenter);
        DoctorAddFragment doctorAddFragment = new DoctorAddFragment();
        doctorAddFragment.setArguments(args);
        return doctorAddFragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        fragmentDoctorAddBinding = FragmentDoctorAddBinding.inflate(inflater);
        this.presenter = (MainPresenter) this.getArguments().getSerializable("presenter");
        fragmentDoctorAddBinding.btnAddNewDoctor.setOnClickListener(this::onClick);
        return fragmentDoctorAddBinding.getRoot();
    }

    private void onClick(View view) {
        String name = fragmentDoctorAddBinding.etDoctorAddName.getText().toString();
        String specialty = fragmentDoctorAddBinding.etDoctorAddSpecialty.getText().toString();
        presenter.addDoctor(name, specialty);

        Bundle result = new Bundle();
        result.putString("page", "doctor");
        getParentFragmentManager().setFragmentResult("changePage", result);
    }
}
