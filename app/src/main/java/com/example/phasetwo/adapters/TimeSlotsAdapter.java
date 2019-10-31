package com.example.phasetwo.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.phasetwo.R;
import com.example.phasetwo.logic.TimeSlot;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

public class TimeSlotsAdapter extends RecyclerView.Adapter<TimeSlotsAdapter.TimeSlotViewHolder> {

    private static final String TAG = TimeSlotsAdapter.class.getSimpleName();

    private List<TimeSlot> timeSlots;

    public TimeSlotsAdapter(List<TimeSlot> timeSlots) {
        this.timeSlots = timeSlots;
    }

    public List<TimeSlot> getTimeSlots() {
        return timeSlots;
    }

    public void setTimeSlots(List<TimeSlot> timeSlots) {
        this.timeSlots = timeSlots;
    }

    @NonNull
    @Override
    public TimeSlotViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();

        int layoutIdForListItem = R.layout.time_slot_item;
        LayoutInflater inflater = LayoutInflater.from(context);

        boolean shouldAttachToParentImmediately = false;

        View view = inflater.inflate(layoutIdForListItem, parent, shouldAttachToParentImmediately);

        return new TimeSlotViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TimeSlotViewHolder holder, int position) {
        TimeSlot timeSlot = timeSlots.get(position);

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm", Locale.getDefault());

        holder.setDateText(dateFormat.format(timeSlot.getStartPoint()));
        holder.setStartingTimeText(timeFormat.format(timeSlot.getStartPoint()));
        holder.setEndingTimeText(timeFormat.format(timeSlot.getEndPoint()));
    }

    @Override
    public int getItemCount() {
        return timeSlots == null ? 0 : timeSlots.size();
    }

    class TimeSlotViewHolder extends RecyclerView.ViewHolder {

        TextView date;
        TextView startingTime;
        TextView endingTime;

        TimeSlotViewHolder(@NonNull View itemView) {
            super(itemView);

            date = itemView.findViewById(R.id.producerMenuDateText);
            startingTime = itemView.findViewById(R.id.producerMenuStartingTimeText);
            endingTime = itemView.findViewById(R.id.producerMenuEndingTimeText);
        }

        void setDateText(String date) {
            this.date.setText(date);
        }

        void setStartingTimeText(String startingTime) {
            this.startingTime.setText(startingTime);
        }

        void setEndingTimeText(String endingTime) {
            this.endingTime.setText(endingTime);
        }
    }
}
