package com.example.p3b_tubes;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;

import com.example.p3b_tubes.databinding.ItemListDoctorBinding;

public class DoctorListAdapter extends BaseAdapter {
    private Doctors doctors;
    private MainPresenter presenter;
    private FragmentManager fm;
    private DoctorFragment doctorFragment;

    private class ViewHolder {
        protected int i;
        protected TextView tvDoctor;
        protected TextView tvSpecialty;
        protected TextView tvPhone;
        protected ImageView btnPhone;
        protected ImageView btnDelete;
        protected LinearLayout llDoctor;

        public ViewHolder(ItemListDoctorBinding itemListDoctorBinding, int i) {
            this.i = i;
            this.tvDoctor = itemListDoctorBinding.tvDoctorName;
            this.tvSpecialty = itemListDoctorBinding.tvDoctorSpecialty;
            this.tvPhone = itemListDoctorBinding.tvPhoneNumber;
            this.btnPhone = itemListDoctorBinding.icPhone;
            this.btnDelete = itemListDoctorBinding.btnDelete;
            this.llDoctor = itemListDoctorBinding.llDoctor;
            this.llDoctor.setOnClickListener(this::onEdit);
            this.btnDelete.setOnClickListener(this::onDelete);
            this.btnPhone.setOnClickListener(this::onClick);
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
        }

        private void updateView(int i) {
            Doctor doctor = doctors.getDoctor(i);
            this.tvDoctor.setText("Dr. " + doctor.getName());
            this.tvSpecialty.setText(doctor.getSpecialty());
            this.tvPhone.setText(doctor.getPhone());
        }
    }

    public DoctorListAdapter(MainPresenter presenter, FragmentManager fm, DoctorFragment doctorFragment) {
        super();
        this.doctors = new Doctors();
        this.presenter = presenter;
        this.fm = fm;
        this.doctorFragment = doctorFragment;
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
        if (view == null) {
            view = itemListDoctorBinding.getRoot();
            viewHolder = new ViewHolder(itemListDoctorBinding, i);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.updateView(i);

        return view;
    }

    public void update(Doctors doctorList) {
        this.doctors = doctorList;
        notifyDataSetChanged();
    }
}
