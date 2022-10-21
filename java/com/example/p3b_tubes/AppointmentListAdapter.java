package com.example.p3b_tubes;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.p3b_tubes.databinding.ItemListAppointmentBinding;

import java.text.SimpleDateFormat;;

public class AppointmentListAdapter extends BaseAdapter {
    private Appointments appointments;
    private MainPresenter presenter;

    private class ViewHolder{
        protected int i;
        protected TextView tvpatientName;
        protected TextView tvPatientIssues;
        protected TextView tvDoctor;
        protected TextView tvSpecialty;
        protected TextView tvDate;
        protected ImageView btnDelete;

        public ViewHolder(ItemListAppointmentBinding itemListAppointmentBinding, int i) {
            this.i = i;
            this.tvpatientName = itemListAppointmentBinding.tvPatientName;
            this.tvPatientIssues = itemListAppointmentBinding.tvPatientIssues;
            this.tvDoctor = itemListAppointmentBinding.tvDoctorName;
            this.tvSpecialty = itemListAppointmentBinding.tvDoctorSpecialty;
            this.tvDate = itemListAppointmentBinding.tvDate;
            this.btnDelete = itemListAppointmentBinding.btnDelete;
            this.btnDelete.setOnClickListener(this::onDelete);
        }

        private void onDelete(View view) {
            AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
            builder.setMessage("Delete Appointment?");
            builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                    presenter.removeAppointment(i);
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
            Appointment appointment = appointments.getAppointment(i);
            this.tvpatientName.setText(appointment.getPatientName());
            this.tvPatientIssues.setText(appointment.getPatientIssues());
            this.tvDoctor.setText(appointment.getDoctor().getName());
            this.tvSpecialty.setText(appointment.getDoctor().getSpecialty());
            this.tvDate.setText(new SimpleDateFormat("E, dd MMM yyyy HH:mm").format(appointment.getDate()));
        }
    }

    public AppointmentListAdapter(MainPresenter presenter){
        this.appointments = new Appointments();
        this.presenter = presenter;
    }

    @Override
    public int getCount() {
        return this.appointments.getSize();
    }

    @Override
    public Object getItem(int i) {
        return this.appointments.getAppointment(i);
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

    public void update(Appointments appointments){
        this.appointments = appointments;
    }
}
