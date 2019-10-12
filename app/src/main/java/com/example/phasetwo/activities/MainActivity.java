package com.example.phasetwo.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.phasetwo.R;
import com.example.phasetwo.common.UserType;

public class MainActivity extends AppCompatActivity {

    static final String EXTRA_USERNAME = "com.example.phasetwo.activities.MainActivity.EXTRA_USERNAME";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void createAccount(View view) {
        Intent intent = new Intent(this, CreateAccountActivity.class);
        startActivity(intent);
    }

    public void login(View view) {
        EditText usernameInput = (EditText) findViewById(R.id.userNameInput);
        EditText passwordInput = (EditText) findViewById(R.id.userPasswordInput);

        String username = usernameInput.getText().toString();
        String password = passwordInput.getText().toString();

        boolean userExists = checkUserExists(username);
        if (!userExists) {
            // TODO: do something if the user does not exist
        } else {
            // TODO: validate user password
            // The user exists so now we need to find out which type it is (PRODUCER / CONSUMER)
            UserType userType = checkUserTypeByCredentials(username);

            if (userType == UserType.PRODUCER) {
                loginProvider(view, username);
            }
        }
    }

    public boolean checkUserExists(String username) {
        return true;
    }

    public UserType checkUserTypeByCredentials(String username) {
        return UserType.PRODUCER;
    }

    public void loginProvider(View view, String username) {
        Intent intent = new Intent(this, ProviderMainMenuActivity.class);
        intent.putExtra(EXTRA_USERNAME, username);
        startActivity(intent);
    }
}
