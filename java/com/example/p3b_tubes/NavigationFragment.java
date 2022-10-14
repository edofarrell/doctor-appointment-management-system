package com.example.p3b_tubes;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.p3b_tubes.databinding.FragmentNavigationBinding;

public class NavigationFragment extends Fragment {
    FragmentNavigationBinding fragmentNavigationBinding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        this.fragmentNavigationBinding = FragmentNavigationBinding.inflate(inflater);
        return this.fragmentNavigationBinding.getRoot();
    }
}
