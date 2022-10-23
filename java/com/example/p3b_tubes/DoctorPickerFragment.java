package com.example.p3b_tubes;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
import android.widget.Toolbar;

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
        this.presenter.loadDoctor();;

        SearchView searchView = this.fragmentDoctorPickerBinding.searchBar;
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                presenter.searchDoctor(newText);
                return false;
            }
        });

        return fragmentDoctorPickerBinding.getRoot();
    }

    @Override
    public void updateListDoctor(Doctors doctors) {
        this.doctorPickerAdapter.update(doctors);
    }

    @Override
    public void resetDoctorForm() {}
}
