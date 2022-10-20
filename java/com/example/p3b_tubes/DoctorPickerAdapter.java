package com.example.p3b_tubes;

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

    private class ViewHolder{
        protected int i;
        protected TextView tvDoctor;
        protected TextView tvSpecialty;

        public ViewHolder(ItemListDoctorBinding itemListDoctorBinding, int i) {
            this.i = i;
            this.tvDoctor = itemListDoctorBinding.tvDoctorName;
            this.tvSpecialty = itemListDoctorBinding.tvDoctorSpecialty;
        }

        private void updateView(int i) {
            Doctor doctor = doctors.getDoctor(i);
            tvDoctor.setText(doctor.getName());
            tvSpecialty.setText(doctor.getSpecialty());
        }
    }

    public DoctorPickerAdapter() {
        super();
        this.doctors = new Doctors();
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
