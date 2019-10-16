package com.example.phasetwo.activities.ui.producer;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.phasetwo.R;
import com.example.phasetwo.adapters.TimeSlotsAdapter;
import com.example.phasetwo.logic.TimeSlot;

import java.util.List;

public class ProducerMenuActivity extends AppCompatActivity {

    private static final String TAG = ProducerMenuActivity.class.getSimpleName();

    private ProducerMenuViewModel viewModel;

    private TimeSlotsAdapter adapter;
    private RecyclerView recyclerView;

    private final int NUMBER_OF_TIME_SLOTS = 20;

    private String userName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_producer_main_menu);

        viewModel = ViewModelProviders.of(this,
                new ProducerMenuViewModelFactory()).get(ProducerMenuViewModel.class);

        // Get the Intent that started this activity and extract the string
        Intent intent = getIntent();
        if (intent.hasExtra(Intent.EXTRA_USER)) {
            userName = intent.getStringExtra(Intent.EXTRA_USER);
        }

        recyclerView = (RecyclerView) findViewById(R.id.rv_producer_menu);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);

        viewModel.getTimeSlots().observe(this, new Observer<List<TimeSlot>>() {
            @Override
            public void onChanged(List<TimeSlot> timeSlots) {
                adapter = new TimeSlotsAdapter(timeSlots);
                recyclerView.setAdapter(adapter);
            }
        });

        viewModel.fetchTimeSlots(userName, NUMBER_OF_TIME_SLOTS);
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
                break;
            }
            case R.id.producer_action_make: {
                Intent intent = new Intent(this, ProducerMakeTimeActivity.class);
                intent.putExtra(Intent.EXTRA_USER, userName);
                startActivity(intent);
                break;
            }
            default: {
                break;
            }
        }
        return true;
    }
}
