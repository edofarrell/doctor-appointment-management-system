package com.example.p3b_tubes;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.p3b_tubes.databinding.ItemListDoctorBinding;

public class DoctorListAdapter extends BaseAdapter {
    private Doctors doctors;
    private MainPresenter presenter;

    private class ViewHolder {
        protected int i;
        protected TextView tvDoctor;
        protected TextView tvSpecialty;
        protected TextView tvPhone;
        protected ImageView btnDelete;

        public ViewHolder(ItemListDoctorBinding itemListDoctorBinding, int i) {
            this.i = i;
            this.tvDoctor = itemListDoctorBinding.tvDoctorName;
            this.tvSpecialty = itemListDoctorBinding.tvDoctorSpecialty;
            this.tvPhone = itemListDoctorBinding.tvPhoneNumber;
            this.btnDelete = itemListDoctorBinding.btnDelete;
            this.btnDelete.setOnClickListener(this::onDelete);
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

    public DoctorListAdapter(MainPresenter presenter) {
        super();
        this.doctors = new Doctors();
        this.presenter = presenter;
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
