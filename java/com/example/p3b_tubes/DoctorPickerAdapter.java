package com.example.p3b_tubes;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.p3b_tubes.databinding.ItemListDoctorAddBinding;
import com.example.p3b_tubes.databinding.ItemListDoctorBinding;

public class DoctorPickerAdapter extends BaseAdapter {
    private Doctors doctors;
    private MainPresenter presenter;
    protected MainPresenter.IAddDoctor IAddDoctor;
    private DoctorPickerFragment doctorPickerFragment;

    private class ViewHolder {
        protected int i;
        protected TextView tvDoctor;
        protected TextView tvSpecialty;
        protected LinearLayout ll;

        public ViewHolder(ItemListDoctorAddBinding itemListDoctorAddBinding, int i) {
            this.i = i;
            this.tvDoctor = itemListDoctorAddBinding.tvDoctorName;
            this.tvSpecialty = itemListDoctorAddBinding.tvDoctorSpecialty;
            this.ll = itemListDoctorAddBinding.llDoctor;
            this.ll.setOnClickListener(this::onClick);
        }

        private void onClick(View view) {
//            presenter.addDoctorToAppointment(i);
            String name = this.tvDoctor.getText().toString();
            String specialty = this.tvSpecialty.getText().toString();
            presenter.addDoctorToAppointment(name,specialty);
            doctorPickerFragment.dismiss();
        }

        private void updateView(int i) {
            Doctor doctor = doctors.getDoctor(i);
            this.tvDoctor.setText(doctor.getName());
            this.tvSpecialty.setText(doctor.getSpecialty());
        }
    }

    public DoctorPickerAdapter(MainPresenter presenter, DoctorPickerFragment doctorPickerFragment) {
        super();
        this.doctors = new Doctors();
        this.presenter = presenter;
        this.IAddDoctor = presenter.uiAddDoctor;
        this.doctorPickerFragment = doctorPickerFragment;
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
        ItemListDoctorAddBinding itemListDoctorAddBinding = ItemListDoctorAddBinding.inflate(inflater);
        ViewHolder viewHolder;
        if (view == null) {
            view = itemListDoctorAddBinding.getRoot();
            viewHolder = new ViewHolder(itemListDoctorAddBinding, i);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.updateView(i);

        return view;
    }

    public void update(Doctors doctors) {
        this.doctors = doctors;
        notifyDataSetChanged();
    }
}
