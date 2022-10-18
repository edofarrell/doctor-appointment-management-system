package com.example.p3b_tubes;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.p3b_tubes.databinding.ItemListAppointmentBinding;
import com.example.p3b_tubes.databinding.ItemListDoctorBinding;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AppointmentListAdapter extends BaseAdapter {
    private List<Appointment> appointmentList;

    private class ViewHolder{
        protected int i;
        protected TextView tvDoctor;
        protected TextView tvSpecialty;
        protected TextView tvDate;

        public ViewHolder(ItemListAppointmentBinding itemListAppointmentBinding, int i) {
            this.i = i;
            this.tvDoctor = itemListAppointmentBinding.tvDoctorName;
            this.tvSpecialty = itemListAppointmentBinding.tvDoctorSpecialty;
            this.tvDate = itemListAppointmentBinding.tvDate;
        }

        private void updateView(int i) {
            Appointment appointment = appointmentList.get(i);
            this.tvDoctor.setText(appointment.getDoctor().getName());
            this.tvSpecialty.setText(appointment.getDoctor().getSpecialty());
            this.tvDate.setText(appointment.getDate().toString());
        }
    }

    public AppointmentListAdapter(){
        this.appointmentList = new ArrayList<>();
    }

    @Override
    public int getCount() {
        return this.appointmentList.size();
    }

    @Override
    public Object getItem(int i) {
        return this.appointmentList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ItemListAppointmentBinding itemListAppointmentBinding = ItemListAppointmentBinding.inflate(inflater);
        ViewHolder viewHolder;
        if(view == null) {
            view = itemListAppointmentBinding.getRoot();
            viewHolder = new ViewHolder(itemListAppointmentBinding, i);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.updateView(i);

        return view;
    }

    public void update(List<Appointment> appointments){
        this.appointmentList = appointments;
    }
}
