package com.example.phasetwo.activities.ui.consumer;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.phasetwo.R;
import com.example.phasetwo.activities.ui.login.LoginActivity;
import com.example.phasetwo.adapters.TouchableTimeSlotsAdapter;
import com.example.phasetwo.logic.TimeSlot;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class ConsumerMenuActivity extends AppCompatActivity implements TouchableTimeSlotsAdapter.ListItemClickListener {

    private static final String TAG = ConsumerMenuActivity.class.getSimpleName();

    private ConsumerMenuViewModel viewModel;

    private FloatingActionButton fab;
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

        recyclerView = findViewById(R.id.rv_consumer_schedule);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);

        adapter = new TouchableTimeSlotsAdapter(new ArrayList<TimeSlot>(), this);

        fab = findViewById(R.id.consumer_schedule_fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ConsumerBookingActivity.class);
                intent.putExtra(Intent.EXTRA_USER, uid);
                startActivity(intent);
            }
        });

        viewModel.getBookings().observe(this, new Observer<List<TimeSlot>>() {
            @Override
            public void onChanged(List<TimeSlot> timeSlots) {
                adapter.setTimeSlots(timeSlots);
                recyclerView.setAdapter(adapter);
            }
        });

        viewModel.getConsumerBookings(uid);
    }

    @Override
    public void onListItemClicked(int clickedItemIndex) {
        final TimeSlot timeSlot = viewModel.getBookings().getValue().get(clickedItemIndex);

        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.booking_cancel)
                .setMessage("Cancel this time-slot ?");

        builder.setPositiveButton(R.string.booking_confirm, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                viewModel.cancelBooking(timeSlot.getId(), "N/A");
                refreshAdapter();
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.general, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.logout:
                viewModel.logout();
                startActivity(new Intent(this, LoginActivity.class));
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        refreshAdapter();
    }

    private void refreshAdapter() {
        adapter.getTimeSlots().clear();
        viewModel.getConsumerBookings(uid);
        adapter.notifyDataSetChanged();
    }
}
