package com.example.phasetwo.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.phasetwo.R;
import com.example.phasetwo.adapters.TimeSlotAdapter;

public class ProviderMainMenuActivity extends AppCompatActivity {

    private static final int NUM_LIST_ITEMS = 100;
    private TimeSlotAdapter slotAdapter;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_provider_main_menu);

        // Get the Intent that started this activity and extract the string
        Intent intent = getIntent();
        String userName = intent.getStringExtra(MainActivity.EXTRA_USERNAME);
        // TODO: fetch details of the provider's appointments

        recyclerView = (RecyclerView) findViewById(R.id.rv_provider_menu);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        recyclerView.setHasFixedSize(true);

        slotAdapter = new TimeSlotAdapter(NUM_LIST_ITEMS);

        recyclerView.setAdapter(slotAdapter);
        }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.provider, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int menuItemThatWasSelected = item.getItemId();
        Context context = ProviderMainMenuActivity.this;

        switch (menuItemThatWasSelected) {
            case R.id.action_view: {
                String message = "view selected";
                Toast.makeText(context, message, Toast.LENGTH_LONG).show();
                break;
            }
            case R.id.action_make: {
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
