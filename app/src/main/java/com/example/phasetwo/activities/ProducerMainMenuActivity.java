package com.example.phasetwo.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.phasetwo.R;
import com.example.phasetwo.adapters.TimeSlotsAdapter;
import com.example.phasetwo.common.UserType;
import com.example.phasetwo.logic.TimeSlotEntity;
import com.example.phasetwo.logic.UserEntity;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class ProducerMainMenuActivity extends AppCompatActivity {

    private static final String TAG = ProducerMainMenuActivity.class.getSimpleName();

    private TimeSlotsAdapter slotAdapter;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_producer_main_menu);

        // Get the Intent that started this activity and extract the string
        Intent intent = getIntent();
        String userName = null;
        if (intent.hasExtra(MainActivity.EXTRA_USERNAME)) {
            userName = intent.getStringExtra(MainActivity.EXTRA_USERNAME);
        }
        if (userName == null) {
            Log.e(TAG, "onCreate: username was not passed by MainActivity");
        }

        // TODO: fetch details of the producer's appointments

        recyclerView = (RecyclerView) findViewById(R.id.rv_producer_menu);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        recyclerView.setHasFixedSize(true);

//        UserEntity userEntity = new UserEntity("Earl", "email", "1", UserType.PRODUCER);
//        TimeSlotEntity ts1 = new TimeSlotEntity(userEntity,
//                LocalDate.of(2019, 10, 18),
//                LocalTime.of(7, 0),
//                LocalTime.of(7, 30));
//        TimeSlotEntity ts2 = new TimeSlotEntity(userEntity,
//                LocalDate.of(2019, 11, 18),
//                LocalTime.of(7, 0),
//                LocalTime.of(7, 30));
//        TimeSlotEntity ts3 = new TimeSlotEntity(userEntity,
//                LocalDate.of(2019, 12, 18),
//                LocalTime.of(7, 0),
//                LocalTime.of(7, 30));
//        List<TimeSlotEntity> l = new ArrayList<>();
//        l.add(ts1);
//        l.add(ts2);
//        l.add(ts3);

        slotAdapter = new TimeSlotsAdapter(null); //TODO: change to a real list

        recyclerView.setAdapter(slotAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.producer, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int menuItemThatWasSelected = item.getItemId();
        Context context = ProducerMainMenuActivity.this;

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
