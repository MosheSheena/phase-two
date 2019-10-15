package com.example.phasetwo.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.phasetwo.R;

public class SimpleNumbersAdapter extends RecyclerView.Adapter<SimpleNumbersAdapter.NumberViewHolder>{

    private static final String TAG = SimpleNumbersAdapter.class.getSimpleName();

    private int mNumberItems;

    public SimpleNumbersAdapter(int numberOfItems) {
        mNumberItems = numberOfItems;
    }

    @NonNull
    @Override
    public NumberViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        Context context = viewGroup.getContext();
        int layoutIdForListItem = R.layout.provider_time_slot_list_item;
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;

        View view = inflater.inflate(layoutIdForListItem, viewGroup, shouldAttachToParentImmediately);
        NumberViewHolder viewHolder = new NumberViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull NumberViewHolder holder, int position) {
        Log.d(TAG, "#" + position);
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return mNumberItems;
    }

    class NumberViewHolder extends RecyclerView.ViewHolder {

        TextView timeSlotTextView;

        public NumberViewHolder(@NonNull View itemView) {
            super(itemView);
            timeSlotTextView = (TextView) itemView.findViewById(R.id.tv_item_text);
        }

        void bind(int listIndex) {
            timeSlotTextView.setText(String.valueOf(listIndex));
        }
    }
}
