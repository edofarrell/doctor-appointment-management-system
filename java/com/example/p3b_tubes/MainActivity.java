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

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.provider.BaseColumns;
import android.util.Log;

import com.example.p3b_tubes.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity implements MainPresenter.IAppointment, MainPresenter.IDoctor{
    private ActivityMainBinding activityMainBinding;
    private HashMap<String, Fragment> fragments;
    private FragmentManager fm;
    private DrawerLayout drawer;
    private MainPresenter presenter;
    private DoctorReaderDbHelper dbDoctorHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.activityMainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        this.dbDoctorHelper = new DoctorReaderDbHelper(this);
        setContentView(this.activityMainBinding.getRoot());

        Toolbar toolbar = this.activityMainBinding.toolbar;
        setSupportActionBar(toolbar);

        drawer = this.activityMainBinding.getRoot();
        ActionBarDrawerToggle abdt = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.open_drawer, R.string.close_drawer);
        drawer.addDrawerListener(abdt);
        abdt.syncState();

        this.presenter = new MainPresenter(this, this);
        this.fragments = new HashMap<>();
        //tambah fragment di sini
        this.fragments.put("onboarding", OnBoardingFragment.newInstance());
        this.fragments.put("home", HomeFragment.newInstance());
        this.fragments.put("appointment", AppointmentFragment.newInstance(presenter));
        this.fragments.put("appointmentAdd", AppointmentAddFragment.newInstance(presenter));
        this.fragments.put("doctor", DoctorFragment.newInstance(presenter));
        this.fragments.put("doctorAdd", DoctorAddFragment.newInstance(presenter));
        this.fm =getSupportFragmentManager();

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
        if(page.equals("exit")) {
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
    public void updateListDoctor(List<Doctor> doctors) {
        DoctorFragment fragment = (DoctorFragment) this.fragments.get("doctor");
        fragment.updateListDoctor(doctors);
    }

    @Override
    public void updateListAppointment(List<Appointment> appointments) {
        AppointmentFragment fragment = (AppointmentFragment) this.fragments.get("appointment");
        fragment.updateListAppointment(appointments);
    }

    @Override
    protected void onPause() {
        super.onPause();
        this.saveData();
    }

    @Override
    protected void onResume() {
        super.onResume();
        this.loadData();
    }

    private void loadData() {
        SQLiteDatabase dbDoctor = dbDoctorHelper.getReadableDatabase();

        String[] projection = {
                BaseColumns._ID,
                DoctorReaderContract.DoctorEntry.COLUMN_NAME_NAME,
                DoctorReaderContract.DoctorEntry.COLUMN_NAME_SPECIALTY
        };

        Cursor cursor = dbDoctor.query(
                DoctorReaderContract.DoctorEntry.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                null
        );

        List<Doctor> doctors = new ArrayList<>();
        while(cursor.moveToNext()){
            String name = cursor.getString(cursor.getColumnIndexOrThrow(DoctorReaderContract.DoctorEntry.COLUMN_NAME_NAME));
            String specialty = cursor.getString(cursor.getColumnIndexOrThrow(DoctorReaderContract.DoctorEntry.COLUMN_NAME_SPECIALTY));
            doctors.add(new Doctor(name,specialty));
            Log.d("data", name+" "+specialty);
        }
        this.presenter.loadDoctor(doctors);
    }

    private void saveData() {
        SQLiteDatabase db = this.dbDoctorHelper.getWritableDatabase();

        List<Doctor> doctors = this.presenter.getDoctors();

        for(int i=0; i<doctors.size(); i++){
            ContentValues values = new ContentValues();
            values.put(DoctorReaderContract.DoctorEntry.COLUMN_NAME_NAME, doctors.get(i).getName());
            values.put(DoctorReaderContract.DoctorEntry.COLUMN_NAME_SPECIALTY, doctors.get(i).getSpecialty());
            db.insert(DoctorReaderContract.DoctorEntry.TABLE_NAME, null, values);
        }
    }
}