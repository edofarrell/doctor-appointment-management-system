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

        this.fragmentNavigationBinding.tvHome.setOnClickListener(this::onClickChangePage);
        this.fragmentNavigationBinding.tvAppointment.setOnClickListener(this::onClickChangePage);
        this.fragmentNavigationBinding.tvDoctor.setOnClickListener(this::onClickChangePage);
        this.fragmentNavigationBinding.tvExit.setOnClickListener(this::onClickChangePage);

        return this.fragmentNavigationBinding.getRoot();
    }

    private void onClickChangePage(View view) {
        Bundle result = new Bundle();

        if (view==this.fragmentNavigationBinding.tvHome){
            result.putString("page", "home");
        }else if (view==this.fragmentNavigationBinding.tvAppointment){
            result.putString("page", "appointment");
        }else if (view==this.fragmentNavigationBinding.tvDoctor){
            result.putString("page", "doctor");
        }else if(view == this.fragmentNavigationBinding.tvExit){
            result.putString("page", "exit");
        }
        getParentFragmentManager().setFragmentResult("changePage", result);
    }
}
