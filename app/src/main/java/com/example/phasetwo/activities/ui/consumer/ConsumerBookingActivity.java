package com.example.phasetwo.activities.ui.consumer;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.phasetwo.R;
import com.example.phasetwo.adapters.BookingAdapter;
import com.example.phasetwo.logic.TimeSlot;

import java.util.List;

public class ConsumerBookingActivity extends AppCompatActivity implements BookingAdapter.ListItemClickListener {

    private static final String TAG = ConsumerBookingActivity.class.getSimpleName();

    private ConsumerBookingViewModel viewModel;

    private BookingAdapter adapter;
    private RecyclerView recyclerView;

    private final int NUMBER_OF_SLOTS = 20;

    private String userName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consumer_booking);

        viewModel = ViewModelProviders.of(this,
                new ConsumerBookingViewModelFactory()).get(ConsumerBookingViewModel.class);

        // Get the Intent that started this activity and extract the string
        Intent intent = getIntent();
        if(intent.hasExtra(Intent.EXTRA_USER)) {
            userName = intent.getStringExtra(Intent.EXTRA_USER);
        }

        recyclerView = (RecyclerView) findViewById(R.id.consumerBookingRV);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);

        viewModel.getAvailableBookings().observe(this, new Observer<List<TimeSlot>>() {
            @Override
            public void onChanged(List<TimeSlot> timeSlots) {
                adapter = new BookingAdapter(timeSlots, ConsumerBookingActivity.this);
                recyclerView.setAdapter(adapter);
            }
        });

        viewModel.getAvailableTimeSlotsForConsumer(userName, NUMBER_OF_SLOTS);
    }

    @Override
    public void onListItemClicked(int clickedItemIndex) {
        Toast mToast = Toast.makeText(this, "Item #" + clickedItemIndex + " clicked", Toast.LENGTH_SHORT);
        mToast.show();
    }
}
