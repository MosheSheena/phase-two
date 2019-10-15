package com.example.phasetwo.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.phasetwo.R;
import com.example.phasetwo.activities.ui.producer.ProducerMenuActivity;
import com.example.phasetwo.common.UserType;

public class MainActivity extends AppCompatActivity {

    public static final String EXTRA_USERNAME = "com.example.phasetwo.activities.MainActivity.EXTRA_USERNAME";

    EditText usernameInput;
    EditText userPasswordInput;
    Button submitCredentialsButton;
    Button createNewAccountButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        usernameInput = (EditText) findViewById(R.id.userNameInput);
        userPasswordInput = (EditText) findViewById(R.id.userPasswordInput);
        submitCredentialsButton = (Button) findViewById(R.id.submitUserCredentialsButton);
        createNewAccountButton = (Button) findViewById(R.id.createAccountButton);

        submitCredentialsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login(view);
            }
        });

        createNewAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createAccount(view);
            }
        });
    }

    private void createAccount(View view) {
        Intent intent = new Intent(this, CreateAccountActivity.class);
        startActivity(intent);
    }

    private void login(View view) {
        String username = usernameInput.getText().toString();
        String password = userPasswordInput.getText().toString();

        //TODO: check if the user exists
        //TODO: validate password
        //TODO: if the user exists log him in according to his type
        //TODO: if the user does not exist notify him


        //TODO: check which type is the user (PRODUCER / CONSUMER)
        UserType userType = UserType.PRODUCER;
        Class<?> activityToMoveTo = userType.equals(UserType.PRODUCER) ?
                ProducerMenuActivity.class : ConsumerMainMenuActivity.class;
        Intent intent = new Intent(this, activityToMoveTo);
        intent.putExtra(EXTRA_USERNAME, username);
        startActivity(intent);
    }
}
