package com.example.phasetwo.activities.ui.consumer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.phasetwo.R;
import com.example.phasetwo.adapters.TimeSlotsAdapter;
import com.example.phasetwo.logic.TimeSlot;

import java.util.List;

public class ConsumerMenuActivity extends AppCompatActivity {

    private static final String TAG = ConsumerMenuActivity.class.getSimpleName();

    private ConsumerMenuViewModel viewModel;

    private TimeSlotsAdapter adapter;
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

        viewModel.getFutureBookings().observe(this, new Observer<List<TimeSlot>>() {
            @Override
            public void onChanged(List<TimeSlot> timeSlots) {
                adapter = new TimeSlotsAdapter(timeSlots);
                recyclerView.setAdapter(adapter);
            }
        });

        viewModel.getConsumerFutureBookings(uid);
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
    protected void onResume() {
        super.onResume();
        viewModel.getConsumerFutureBookings(uid);
    }


}
