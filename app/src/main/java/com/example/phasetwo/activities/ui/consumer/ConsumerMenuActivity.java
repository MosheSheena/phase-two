package com.example.phasetwo.activities.ui.consumer;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
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

public class ConsumerMenuActivity extends AppCompatActivity implements TouchableTimeSlotsAdapter.ListItemClickListener{

    private static final String TAG = ConsumerMenuActivity.class.getSimpleName();

    private ConsumerMenuViewModel viewModel;

    private TouchableTimeSlotsAdapter adapter;
    private RecyclerView recyclerView;

    private String uid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consumer_main_menu);

        viewModel = ViewModelProviders.of(this,
                new ConsumerMenuViewModelFactory()).get(ConsumerMenuViewModel.class);

        // Get the Intent that started this activity and extract the string
        Intent intent = getIntent();
        if (intent.hasExtra(Intent.EXTRA_USER)) {
            uid = intent.getStringExtra(Intent.EXTRA_USER);
        }
        if (intent.hasExtra(Intent.EXTRA_SUBJECT)) {
            String message = intent.getStringExtra(Intent.EXTRA_SUBJECT);
            Toast.makeText(this, message, Toast.LENGTH_LONG).show();
        }

        recyclerView = findViewById(R.id.consumerMenuRV);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);

        viewModel.getBookings().observe(this, new Observer<List<TimeSlot>>() {
            @Override
            public void onChanged(List<TimeSlot> timeSlots) {
                adapter = new TouchableTimeSlotsAdapter(timeSlots, ConsumerMenuActivity.this);
                recyclerView.setAdapter(adapter);
            }
        });

        viewModel.getConsumerBookings(uid);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.consumer, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int menuItemThatWasSelected = item.getItemId();

        switch (menuItemThatWasSelected) {
            case R.id.consumer_action_view: {
                break;
            }
            case R.id.consumer_action_book: {
                Intent intent = new Intent(this, ConsumerBookingActivity.class);
                intent.putExtra(Intent.EXTRA_USER, uid);
                startActivity(intent);
                break;
            }
            default: {
                break;
            }
        }
        return true;
    }

    @Override
    public void onListItemClicked(int clickedItemIndex) {
        final Context context = getApplicationContext();
        final TimeSlot timeSlot = viewModel.getBookings().getValue().get(clickedItemIndex);

        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.booking_title)
                .setMessage("Cancel this time-slot ?");

        builder.setPositiveButton(R.string.booking_confirm, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                viewModel.cancelBooking(timeSlot.getId(), "N/A");
                viewModel.getConsumerBookings(uid);
            }
        });

        builder.setNegativeButton(R.string.booking_cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                // Nothing really to do when the user regrets trying to cancel
            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}
