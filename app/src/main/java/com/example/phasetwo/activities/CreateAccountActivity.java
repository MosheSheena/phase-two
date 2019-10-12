package com.example.phasetwo.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

import com.example.phasetwo.R;
import com.example.phasetwo.common.UserType;
import com.example.phasetwo.iface.UserTO;

public class CreateAccountActivity extends AppCompatActivity {

    private EditText nameInput;
    private EditText emailInput;
    private EditText passwordInput;
    private EditText passwordVerifyInput;
    private RadioButton typeConsumerRadioButton;
    private RadioButton typeProducerRadioButton;
    private Button createAccountButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        nameInput = (EditText) findViewById(R.id.create_account_name_input);
        emailInput = (EditText) findViewById(R.id.create_account_email_input);
        passwordInput = (EditText) findViewById(R.id.create_account_password_input);
        passwordVerifyInput = (EditText) findViewById(R.id.create_account_password_verify_input);
        typeConsumerRadioButton = (RadioButton) findViewById(R.id.create_account_consumer_type_radio_button);
        typeProducerRadioButton = (RadioButton) findViewById(R.id.create_account_producer_type_radio_button);
        createAccountButton = (Button) findViewById(R.id.create_account_create_button);

        createAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean inputIsValid = verifyInput();
                if(!inputIsValid) {
                    return;
                }

                //TODO: check if a user with this email exists and handle accordingly

                UserType checkedUserType = typeConsumerRadioButton.isChecked() ? UserType.CONSUMER
                        : UserType.PRODUCER;

                UserTO user = new UserTO(
                        nameInput.getText().toString(),
                        emailInput.getText().toString(),
                        passwordInput.getText().toString(),
                        checkedUserType
                );

                //TODO: create a new user
            }
        });
    }

    private boolean verifyInput() {
        if(nameInput.getText().toString().isEmpty()) {
            //TODO: handle
            return false;
        }
        if(emailInput.getText().toString().isEmpty()) {
            //TODO: handle
            return false;
        }
        if(passwordInput.getText().toString().isEmpty()) {
            //TODO: handle
            return false;
        }
        if(passwordVerifyInput.getText().toString().isEmpty()) {
            //TODO: handle
            return false;
        }
        if(!passwordInput.getText().toString().equals(
                passwordVerifyInput.getText().toString())) {
            //TODO: handle
            return false;
        }
        if(!typeConsumerRadioButton.isChecked() && !typeProducerRadioButton.isChecked()) {
            //TODO: handle
            return false;
        }
        return true;
    }
}