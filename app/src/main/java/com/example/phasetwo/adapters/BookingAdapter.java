package com.example.phasetwo.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.example.phasetwo.R;
import com.example.phasetwo.logic.TimeSlot;

import java.util.List;

public class BookingAdapter extends TimeSlotsAdapter {

    final private ListItemClickListener itemClickListener;

    public BookingAdapter(List<TimeSlot> timeSlots, ListItemClickListener itemClickListener) {
        super(timeSlots);
        this.itemClickListener = itemClickListener;
    }

    @NonNull
    @Override
    public BookingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();

        int layoutIdForListItem = R.layout.time_slot_item;
        LayoutInflater inflater = LayoutInflater.from(context);

        boolean shouldAttachToParentImmediately = false;

        View view = inflater.inflate(layoutIdForListItem, parent, shouldAttachToParentImmediately);

        return new BookingViewHolder(view);
    }

    public interface ListItemClickListener {
        void onListItemClicked(int clickedItemIndex);
    }

    class BookingViewHolder extends TimeSlotViewHolder implements View.OnClickListener{

        BookingViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int clickedPosition = getAdapterPosition();
            itemClickListener.onListItemClicked(clickedPosition);
        }
    }
}
