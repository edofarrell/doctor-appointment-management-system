package com.example.p3b_tubes;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.p3b_tubes.databinding.FragmentHomeBinding;

public class HomeFragment extends Fragment {
    FragmentHomeBinding fragmentHomeBinding;

    private HomeFragment(){}

    public static HomeFragment newInstance() {
        Bundle args = new Bundle();
        HomeFragment fragment = new HomeFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        this.fragmentHomeBinding = FragmentHomeBinding.inflate(inflater);
        return this.fragmentHomeBinding.getRoot();
    }
}
