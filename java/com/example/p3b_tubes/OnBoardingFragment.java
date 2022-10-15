package com.example.p3b_tubes;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.p3b_tubes.databinding.FragmentOnboardingBinding;

public class OnBoardingFragment extends Fragment {
    FragmentOnboardingBinding fragmentOnboardingBinding;

    public static OnBoardingFragment newInstance() {
        Bundle args = new Bundle();
        OnBoardingFragment onBoardingFragment = new OnBoardingFragment();
        onBoardingFragment.setArguments(args);

        return onBoardingFragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        fragmentOnboardingBinding = FragmentOnboardingBinding.inflate(inflater);

        return fragmentOnboardingBinding.getRoot();
    }
}
