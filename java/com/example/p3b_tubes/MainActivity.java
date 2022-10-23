package com.example.p3b_tubes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentResultListener;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.util.Log;

import com.example.p3b_tubes.databinding.ActivityMainBinding;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity implements MainPresenter.IAppointment, MainPresenter.IDoctor, MainPresenter.IAddDoctor {
    private ActivityMainBinding activityMainBinding;
    private HashMap<String, Fragment> fragments;
    private FragmentManager fm;
    private DrawerLayout drawer;
    private MainPresenter presenter;
    private DatabaseHelper database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.activityMainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        this.presenter = new MainPresenter(this, this, this);
        this.database = new DatabaseHelper(this);
        setContentView(this.activityMainBinding.getRoot());

        Toolbar toolbar = this.activityMainBinding.toolbar;
        setSupportActionBar(toolbar);

        this.drawer = this.activityMainBinding.getRoot();
        ActionBarDrawerToggle abdt = new ActionBarDrawerToggle(this, this.drawer, toolbar, R.string.open_drawer, R.string.close_drawer);
        this.drawer.addDrawerListener(abdt);
        abdt.syncState();

        this.fragments = new HashMap<>();
        this.fragments.put("onboarding", OnBoardingFragment.newInstance());
        this.fragments.put("home", HomeFragment.newInstance());
        this.fragments.put("doctor", DoctorFragment.newInstance(this.presenter));
        this.fragments.put("doctorAdd", DoctorAddFragment.newInstance(this.presenter));
        this.fragments.put("appointment", AppointmentFragment.newInstance(this.presenter));
        this.fragments.put("appointmentAdd", AppointmentAddFragment.newInstance(this.presenter));
        this.fm = getSupportFragmentManager();

        FragmentTransaction ft = this.fm.beginTransaction();
        ft.add(this.activityMainBinding.fragmentContainer.getId(), fragments.get("onboarding"))
                .commit();

        this.fm.setFragmentResultListener("changePage", this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                String page = result.getString("page");
                changePage(page);
            }
        });
    }

    private void changePage(String page) {
        FragmentTransaction ft = this.fm.beginTransaction();
        if (page.equals("exit")) {
            this.closeApplication();
        } else {
            Log.d("page", page);
            ft.replace(this.activityMainBinding.fragmentContainer.getId(), fragments.get(page))
                    .addToBackStack(null)
                    .commit();
            this.drawer.closeDrawers();
        }
    }

    private void closeApplication() {
        this.moveTaskToBack(true);
        this.finish();
    }

    @Override
    public void updateListDoctor(Doctors doctors) {
        DoctorFragment doctorFragment = (DoctorFragment) this.fragments.get("doctor");
        doctorFragment.updateListDoctor(doctors);

        AppointmentAddFragment appointmentAddFragment = (AppointmentAddFragment) this.fragments.get("appointmentAdd");
        appointmentAddFragment.updateListDoctor(doctors);
    }

    @Override
    public void resetDoctorForm() {
        DoctorAddFragment fragment = (DoctorAddFragment) this.fragments.get("doctorAdd");
        fragment.resetForm();
    }

    @Override
    public void updateListAppointment(Appointments appointments) {
        AppointmentFragment fragment = (AppointmentFragment) this.fragments.get("appointment");
        fragment.updateListAppointment(appointments);
    }

    @Override
    public void resetAppointmentForm() {
        AppointmentAddFragment fragment = (AppointmentAddFragment) this.fragments.get("appointmentAdd");
        fragment.resetForm();
    }

    @Override
    protected void onPause() {
        super.onPause();
        this.deleteDatabase(DatabaseHelper.DATABASE_NAME);
        this.database = new DatabaseHelper(this);
        this.presenter.saveToDatabase(this.database);
    }

    @Override
    protected void onResume() {
        super.onResume();
        this.presenter.loadFromDatabase(this.database);
    }

    @Override
    public void setDoctorToAppointment(Doctor doctor) {
        AppointmentAddFragment fragment = (AppointmentAddFragment) this.fragments.get("appointmentAdd");
        fragment.setDoctorToAppointment(doctor);
    }
}