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

import java.util.List;

public class TimeSlotsAdapter extends RecyclerView.Adapter<TimeSlotsAdapter.TimeSlotViewHolder> {

    private static final String TAG = TimeSlotsAdapter.class.getSimpleName();

    private List<TimeSlot> timeSlots;

    public TimeSlotsAdapter(List<TimeSlot> timeSlots) {
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

        holder.setDateText(timeSlot.getDate().toString());
        holder.setStartingTimeText(timeSlot.getStartingTime().toString());
        holder.setEndingTimeText(timeSlot.getEndingTime().toString());
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
