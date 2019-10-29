package com.example.phasetwo.activities.ui.consumer;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.phasetwo.R;
import com.example.phasetwo.adapters.TouchableTimeSlotsAdapter;
import com.example.phasetwo.logic.TimeSlot;

import java.util.List;

public class ConsumerBookingActivity extends AppCompatActivity implements TouchableTimeSlotsAdapter.ListItemClickListener {

    private static final String TAG = ConsumerBookingActivity.class.getSimpleName();

    private ConsumerBookingViewModel viewModel;

    private TouchableTimeSlotsAdapter adapter;
    private RecyclerView recyclerView;

    private String uid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consumer_booking);

        viewModel = ViewModelProviders.of(this,
                new ConsumerBookingViewModelFactory()).get(ConsumerBookingViewModel.class);

        // Get the Intent that started this activity and extract the string
        Intent intent = getIntent();
        if(intent.hasExtra(Intent.EXTRA_USER)) {
            uid = intent.getStringExtra(Intent.EXTRA_USER);
        }

        recyclerView = findViewById(R.id.consumerBookingRV);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);

        viewModel.getAvailableTimeSlots().observe(this, new Observer<List<TimeSlot>>() {
            @Override
            public void onChanged(List<TimeSlot> timeSlots) {
                adapter = new TouchableTimeSlotsAdapter(timeSlots, ConsumerBookingActivity.this);
                recyclerView.setAdapter(adapter);
            }
        });

        viewModel.getAvailableTimeSlotsForConsumer(uid);
    }

    @Override
    public void onListItemClicked(int clickedItemIndex) {
        final Context context = getApplicationContext();
        final TimeSlot timeSlot = viewModel.getAvailableTimeSlots().getValue().get(clickedItemIndex);

        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.booking_title)
                .setMessage("Book this time-slot ?");

        builder.setPositiveButton(R.string.booking_confirm, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                viewModel.bookTimeSlot(timeSlot.getId(), uid);

                Intent intent = new Intent(context, ConsumerMenuActivity.class);
                intent.putExtra(Intent.EXTRA_USER, uid);
                intent.putExtra(Intent.EXTRA_SUBJECT, "time-slot successfully booked");
                startActivity(intent);
            }
        });

        builder.setNegativeButton(R.string.booking_cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                // Nothing really to do when the user regrets trying to book
            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}
