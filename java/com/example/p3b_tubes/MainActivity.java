package com.example.p3b_tubes;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.example.p3b_tubes.databinding.ActivityMainBinding;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding activityMainBinding;
    private HashMap<String, Fragment> fragments;
    private FragmentManager fm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.activityMainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(this.activityMainBinding.getRoot());

        Toolbar toolbar = this.activityMainBinding.toolbar;
        setSupportActionBar(toolbar);

        DrawerLayout drawer = this.activityMainBinding.getRoot();
        ActionBarDrawerToggle abdt = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.open_drawer, R.string.close_drawer);
        drawer.addDrawerListener(abdt);
        abdt.syncState();

        this.fragments = new HashMap<>();
        //tambah fragment di sini
        this.fragments.put("home", HomeFragment.newInstance());
        this.fm =getSupportFragmentManager();

        FragmentTransaction ft = this.fm.beginTransaction();
        ft.add(this.activityMainBinding.fragmentContainer.getId(), fragments.get("home"))
                .addToBackStack(null)
                .commit();
    }
}