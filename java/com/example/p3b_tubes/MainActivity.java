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

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding activityMainBinding;
    private HashMap<String, Fragment> fragments;
    private FragmentManager fm;
    private DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.activityMainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(this.activityMainBinding.getRoot());

        Toolbar toolbar = this.activityMainBinding.toolbar;
        setSupportActionBar(toolbar);

        drawer = this.activityMainBinding.getRoot();
        ActionBarDrawerToggle abdt = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.open_drawer, R.string.close_drawer);
        drawer.addDrawerListener(abdt);
        abdt.syncState();

        this.fragments = new HashMap<>();
        //tambah fragment di sini
        this.fragments.put("onboarding", OnBoardingFragment.newInstance());
        this.fragments.put("home", HomeFragment.newInstance());
        this.fragments.put("appointment", AppointmentFragment.newInstance());
        this.fragments.put("doctor", DoctorFragment.newInstance());
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
}