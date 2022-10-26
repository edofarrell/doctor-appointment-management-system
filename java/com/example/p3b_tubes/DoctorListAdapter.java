package com.example.p3b_tubes;

<<<<<<< Updated upstream
=======
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
>>>>>>> Stashed changes
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.p3b_tubes.databinding.ItemListDoctorBinding;

import java.util.ArrayList;
import java.util.List;

public class DoctorListAdapter extends BaseAdapter {
    private List<Doctor> doctorList;

    private class ViewHolder{
        protected int i;
        protected TextView tvDoctor;
        protected TextView tvSpecialty;

        public ViewHolder(ItemListDoctorBinding itemListDoctorBinding, int i) {
            this.i = i;
<<<<<<< Updated upstream
            tvDoctor = itemListDoctorBinding.tvDoctorName;
            tvSpecialty = itemListDoctorBinding.tvDoctorSpecialty;
=======
            this.tvDoctor = itemListDoctorBinding.tvDoctorName;
            this.tvSpecialty = itemListDoctorBinding.tvDoctorSpecialty;
            this.tvPhone = itemListDoctorBinding.tvPhoneNumber;
            this.btnPhone = itemListDoctorBinding.icPhone;
            this.btnDelete = itemListDoctorBinding.btnDelete;
            this.llDoctor = itemListDoctorBinding.llDoctor;
            this.llDoctor.setOnClickListener(this::onEdit);
            this.btnDelete.setOnClickListener(this::onDelete);
        }

        private void onClick(View view) {
            Intent intent = new Intent(Intent.ACTION_DIAL);
            String phoneNumber = this.tvPhone.getText().toString();
            intent.setData(Uri.parse("tel:" + phoneNumber));
            if (intent.resolveActivity(doctorFragment.getActivity().getPackageManager()) != null) {
                doctorFragment.startActivity(intent);
            }

        }

        private void onEdit(View view) {
            String name = this.tvDoctor.getText().toString();
            String specialty = this.tvSpecialty.getText().toString();
            String phone = this.tvPhone.getText().toString();
            Doctor doctor = new Doctor(name, specialty, phone);
            DialogFragment fragment = DoctorEditFragment.newInstance(presenter, doctor, this.i);
            fragment.show(fm.beginTransaction(), "editDoctor");
        }

        private void onDelete(View view) {
            AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
            builder.setMessage("Delete Doctor?");
            builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                    presenter.removeDoctor(i);
                    notifyDataSetChanged();
                }
            });
            builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });
            AlertDialog deleteAlert = builder.create();
            deleteAlert.show();
>>>>>>> Stashed changes
        }

        private void updateView(int i) {
            Doctor doctor = doctorList.get(i);
            tvDoctor.setText(doctor.getName());
            tvSpecialty.setText(doctor.getSpecialty());
        }
    }

    public DoctorListAdapter() {
        super();
        doctorList = new ArrayList<>();
    }

    @Override
    public int getCount() {
        return doctorList.size();
    }

    @Override
    public Object getItem(int i) {
        return doctorList.get(i);
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

    public void update(List<Doctor> doctorList) {
        this.doctorList = doctorList;
    }
}
