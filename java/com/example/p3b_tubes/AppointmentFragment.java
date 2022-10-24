package com.example.p3b_tubes;

import android.os.Bundle;
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
import androidx.fragment.app.FragmentManager;

import com.example.p3b_tubes.databinding.FragmentAppointmentBinding;

public class AppointmentFragment extends Fragment implements MainPresenter.IAppointment {
    private FragmentAppointmentBinding fragmentAppointmentBinding;
    private AppointmentListAdapter appointmentListAdapter;
    private MainPresenter presenter;

    private AppointmentFragment() {
    }

    public static AppointmentFragment newInstance(MainPresenter presenter, FragmentManager fm) {
        Bundle args = new Bundle();
        AppointmentFragment fragment = new AppointmentFragment();
        fragment.presenter = presenter;
        fragment.appointmentListAdapter = new AppointmentListAdapter(presenter, fm);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        this.fragmentAppointmentBinding = FragmentAppointmentBinding.inflate(inflater);

        this.fragmentAppointmentBinding.lstAppointment.setAdapter(this.appointmentListAdapter);
        this.fragmentAppointmentBinding.btnAddAppointment.setOnClickListener(this::onClickAddAppointment);

        this.presenter.loadAppointment();

        setHasOptionsMenu(true);

        return this.fragmentAppointmentBinding.getRoot();
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
                presenter.searchAppointment(newText);
                return false;
            }
        });

        super.onCreateOptionsMenu(menu, inflater);
    }

    private void onClickAddAppointment(View view) {
        Bundle result = new Bundle();
        result.putString("page", "appointmentAdd");
        getParentFragmentManager().setFragmentResult("changePage", result);
    }

    @Override
    public void updateListAppointment(Appointments appointments) {
        this.appointmentListAdapter.update(appointments);
    }

    @Override
    public void resetAppointmentForm() {}
}
