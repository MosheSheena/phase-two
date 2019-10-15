package com.example.phasetwo.activities.ui.producer;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.phasetwo.R;
import com.example.phasetwo.activities.MainActivity;
import com.example.phasetwo.adapters.TimeSlotsAdapter;
import com.example.phasetwo.logic.TimeSlotEntity;

import java.util.List;

public class ProducerMenuActivity extends AppCompatActivity {

    private static final String TAG = ProducerMenuActivity.class.getSimpleName();

    private ProducerMenuViewModel producerMenuViewModel;

    private TimeSlotsAdapter slotAdapter;
    private RecyclerView recyclerView;

    private final int NUMBER_OF_TIME_SLOTS = 20;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_producer_main_menu);

        producerMenuViewModel = ViewModelProviders.of(this,
                new ProducerMenuViewModelFactory()).get(ProducerMenuViewModel.class);

        // Get the Intent that started this activity and extract the string
        Intent intent = getIntent();

        String userName = null;

        if (intent.hasExtra(MainActivity.EXTRA_USERNAME)) {
            userName = intent.getStringExtra(MainActivity.EXTRA_USERNAME);
        }
        if (userName == null) {
            Log.e(TAG, "onCreate: username was not passed by MainActivity");
        }

        recyclerView = (RecyclerView) findViewById(R.id.rv_producer_menu);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);

        producerMenuViewModel.getTimeSlots().observe(this, new Observer<List<TimeSlotEntity>>() {
            @Override
            public void onChanged(List<TimeSlotEntity> timeSlotEntities) {
                slotAdapter = new TimeSlotsAdapter(producerMenuViewModel.getTimeSlots().getValue());
                recyclerView.setAdapter(slotAdapter);
            }
        });

        producerMenuViewModel.fetchTimeSlots(userName, NUMBER_OF_TIME_SLOTS);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.producer, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int menuItemThatWasSelected = item.getItemId();
        Context context = ProducerMenuActivity.this;

        switch (menuItemThatWasSelected) {
            case R.id.producer_action_view: {
                String message = "view selected";
                Toast.makeText(context, message, Toast.LENGTH_LONG).show();
                break;
            }
            case R.id.producer_action_make: {
                String message = "make selected";
                Toast.makeText(context, message, Toast.LENGTH_LONG).show();
                break;
            }
            default: {
                break;
            }
        }
        return true;
    }
}
