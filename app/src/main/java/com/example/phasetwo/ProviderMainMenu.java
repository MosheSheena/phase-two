package com.example.phasetwo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

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
}
