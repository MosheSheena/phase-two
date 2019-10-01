package com.example.phasetwo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class ProviderMainMenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_provider_main_menu);

        // Get the Intent that started this activity and extract the string
        Intent intent = getIntent();
        String userName = intent.getStringExtra(MainActivity.EXTRA_USERNAME);
        // TODO: fetch details of the provider's appointments
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.provider, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int menuItemThatWasSelected = item.getItemId();
        Context context = ProviderMainMenu.this;

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
