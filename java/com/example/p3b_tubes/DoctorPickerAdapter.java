package com.example.p3b_tubes;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.p3b_tubes.databinding.ItemListDoctorBinding;

import java.util.ArrayList;
import java.util.List;

public class DoctorPickerAdapter extends BaseAdapter {
    private Doctors doctors;
    private MainPresenter presenter;
    protected MainPresenter.IAddDoctor IAddDoctor;

    private class ViewHolder{
        protected int i;
        protected TextView tvDoctor;
        protected TextView tvSpecialty;

        public ViewHolder(ItemListDoctorBinding itemListDoctorBinding, int i) {
            this.i = i;
            this.tvDoctor = itemListDoctorBinding.tvDoctorName;
            this.tvSpecialty = itemListDoctorBinding.tvDoctorSpecialty;
            tvDoctor.setOnClickListener(this::onClick);
            tvSpecialty.setOnClickListener(this::onClick);
        }

        private void onClick(View view) {
            presenter.addDoctorToAppointment(i);
        }

        private void updateView(int i) {
            Doctor doctor = doctors.getDoctor(i);
            tvDoctor.setText(doctor.getName());
            tvSpecialty.setText(doctor.getSpecialty());
        }
    }

    public DoctorPickerAdapter(MainPresenter presenter) {
        super();
        this.doctors = new Doctors();
        this.presenter = presenter;
        this.IAddDoctor = presenter.uiAddDoctor;
    }

    @Override
    public int getCount() {
        return this.doctors.getSize();
    }

    @Override
    public Object getItem(int i) {
        return this.doctors.getDoctor(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        ItemListDoctorBinding itemListDoctorBinding = ItemListDoctorBinding.inflate(inflater);
        ViewHolder viewHolder;
        if(view == null) {
            view = itemListDoctorBinding.getRoot();
            viewHolder = new ViewHolder(itemListDoctorBinding, i);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.updateView(i);

        return view;
    }

    public void update(Doctors doctors) {
        this.doctors = doctors;
    }
}
