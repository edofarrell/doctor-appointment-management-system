package com.example.p3b_tubes;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

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

        setHasOptionsMenu(true);

        return fragmentDoctorBinding.getRoot();
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.search, menu);

        MenuItem menuItem = menu.findItem(R.id.search_bar);
        SearchView searchView = (SearchView) menuItem.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                Log.d("debug", newText);
                presenter.searchDoctor(newText);
                return false;
            }
        });

        super.onCreateOptionsMenu(menu, inflater);
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
