package com.example.p3b_tubes;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.p3b_tubes.databinding.FragmentAppointmentBinding;

import java.util.List;

public class AppointmentFragment extends Fragment implements MainPresenter.IAppointment{
    private FragmentAppointmentBinding fragmentAppointmentBinding;
    private AppointmentListAdapter appointmentListAdapter;
    private MainPresenter presenter;

    private AppointmentFragment(){}

    public static AppointmentFragment newInstance(MainPresenter presenter) {
        Bundle args = new Bundle();
        AppointmentFragment fragment = new AppointmentFragment();
        fragment.presenter = presenter;
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        this.fragmentAppointmentBinding = FragmentAppointmentBinding.inflate(inflater);

        this.appointmentListAdapter = new AppointmentListAdapter();
        this.fragmentAppointmentBinding.lstAppointment.setAdapter(this.appointmentListAdapter);
        this.fragmentAppointmentBinding.btnAddAppointment.setOnClickListener(this::onClickAddAppointment);

        this.presenter = (MainPresenter) this.getArguments().getSerializable("presenter");
        this.presenter.loadAppointment();

        return this.fragmentAppointmentBinding.getRoot();
    }

    private void onClickAddAppointment(View view) {
        Bundle result = new Bundle();
        result.putString("page", "appointmentAdd");
        getParentFragmentManager().setFragmentResult("changePage", result);
    }

    @Override
    public void updateListAppointment(List<Appointment> appointments) {
        this.appointmentListAdapter.update(appointments);
    }
}
