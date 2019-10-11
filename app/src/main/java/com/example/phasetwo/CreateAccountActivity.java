package com.example.phasetwo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class CreateAccountActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        // This is for handling intents
        // Don't forget to specify the Extras type
//        Intent intentThatStartedThisActivity = getIntent();
//        if(intentThatStartedThisActivity.hasExtra()) {
//            // Extract Extra and do something with it
//        }
    }
}
