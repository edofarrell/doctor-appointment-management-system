package com.example.p3b_tubes;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;

import com.example.p3b_tubes.databinding.ItemListAppointmentBinding;

import java.text.SimpleDateFormat;;

public class AppointmentListAdapter extends BaseAdapter {
    private Appointments appointments;
    private MainPresenter presenter;
    private FragmentManager fm;

    private class ViewHolder {
        protected int i;
        protected TextView tvpatientName;
        protected TextView tvDoctor;
        protected TextView tvSpecialty;
        protected TextView tvDate;
        protected TextView tvTime;
        protected ImageView btnDelete;
        protected LinearLayout ll;

        public ViewHolder(ItemListAppointmentBinding itemListAppointmentBinding, int i) {
            this.i = i;
            this.tvpatientName = itemListAppointmentBinding.tvPatientName;
            this.tvDoctor = itemListAppointmentBinding.tvDoctorName;
            this.tvSpecialty = itemListAppointmentBinding.tvDoctorSpecialty;
            this.tvDate = itemListAppointmentBinding.tvDate;
            this.tvTime = itemListAppointmentBinding.tvTime;
            this.btnDelete = itemListAppointmentBinding.btnDelete;
            this.ll = itemListAppointmentBinding.llAppointment;

            this.btnDelete.setOnClickListener(this::onDelete);
            this.ll.setOnClickListener(this::onOpenDetail);
        }

        private void onOpenDetail(View view) {
            DialogFragment detail = AppointmentDetailFragment.newInstance(presenter, appointments.getAppointment(i), i);
            detail.show(fm.beginTransaction(), "detailAppointment");
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
            this.tvDoctor.setText("Dr. " + appointment.getDoctor().getName());
            this.tvSpecialty.setText(appointment.getDoctor().getSpecialty());
            this.tvDate.setText(new SimpleDateFormat("dd MMM yyyy").format(appointment.getDate()));
            this.tvTime.setText(new SimpleDateFormat("HH:mm").format(appointment.getDate()));
        }
    }

    public AppointmentListAdapter(MainPresenter presenter, FragmentManager fm) {
        this.appointments = new Appointments();
        this.presenter = presenter;
        this.fm = fm;
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
        if (view == null) {
            view = itemListAppointmentBinding.getRoot();
            viewHolder = new ViewHolder(itemListAppointmentBinding, i);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.updateView(i);

        return view;
    }

    public void update(Appointments appointments) {
        this.appointments = appointments;
        notifyDataSetChanged();
    }
}
