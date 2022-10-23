package com.example.p3b_tubes;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;

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
        this.fragmentDoctorAddBinding.btnCancel.setOnClickListener(this::onCancel);
        return fragmentDoctorAddBinding.getRoot();
    }

    private void onCancel(View view) {
        resetForm();
        Bundle result = new Bundle();
        result.putString("page", "doctor");
        getParentFragmentManager().setFragmentResult("changePage", result);
    }

    private void onClick(View view) {
        String name =  this.fragmentDoctorAddBinding.etDoctorAddName.getEditText().getText().toString();
        String specialty =  this.fragmentDoctorAddBinding.etDoctorAddSpecialty.getEditText().getText().toString();
        String phone = this.fragmentDoctorAddBinding.etDoctorAddPhone.getText().toString();
        this.presenter.addDoctor(name, specialty, phone);

        Bundle result = new Bundle();
        result.putString("page", "doctor");
        getParentFragmentManager().setFragmentResult("changePage", result);
    }

    public void resetForm(){
        this.fragmentDoctorAddBinding.etDoctorAddName.getEditText().setText("");
        this.fragmentDoctorAddBinding.etDoctorAddSpecialty.getEditText().setText("");
        this.fragmentDoctorAddBinding.etDoctorAddPhone.setText("");
        InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Activity.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(), 0);
    }
}
