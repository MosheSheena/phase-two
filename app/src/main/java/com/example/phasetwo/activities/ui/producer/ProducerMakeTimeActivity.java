package com.example.phasetwo.activities.ui.producer;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import com.example.phasetwo.R;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Calendar;

public class ProducerMakeTimeActivity extends AppCompatActivity {

    private static final String TAG = ProducerMakeTimeActivity.class.getSimpleName();

    public static final String EXTRA_USERNAME = "com.example.phasetwo.activities.ui.producer.EXTRA_USERNAME";

    private ProducerMakeTimeViewModel viewModel;

    private int currentYear;
    private int currentMonth;
    private int currentDay;
    private int currentHour;
    private int currentMinute;

    private int mChosenYear;
    private int mChosenMonth;
    private int mChosenDay;
    private int mChosenStartHour;
    private int mChosenStartMinute;
    private int mChosenEndHour;
    private int mChosenEndMinute;

    private String userName;

    private TextView dateDisplay;
    private TextView startTimeDisplay;
    private TextView endTimeDisplay;
    private Button dateButton;
    private Button startTimeButton;
    private Button endTimeButton;
    private Button makeTimeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_producer_make_time);

        viewModel = ViewModelProviders.of(this,
                new ProducerMakeTimeViewModelFactory()).get(ProducerMakeTimeViewModel.class);

        // Get the Intent that started this activity and extract the string
        Intent intent = getIntent();

        if (intent.hasExtra(Intent.EXTRA_USER)) {
            userName = intent.getStringExtra(Intent.EXTRA_USER);
        }
        if (userName == null) {
            Log.e(TAG, "onCreate: username was not passed by MainActivity");
        }

        dateDisplay = (TextView) findViewById(R.id.producerMakeTimeDateText);
        startTimeDisplay = (TextView) findViewById(R.id.producerMakeTimeStartTimeText);
        endTimeDisplay = (TextView) findViewById(R.id.producerMakeTimeEndTimeText);
        dateButton = (Button) findViewById(R.id.producerMakeTimeDateButton);
        startTimeButton = (Button) findViewById(R.id.producerMakeTimeStartTimeButton);
        endTimeButton = (Button) findViewById(R.id.producerMakeTimeEndTimeButton);
        makeTimeButton = (Button) findViewById(R.id.producerMakeTimeMakeTimeButton);

        makeTimeButton.setEnabled(false);

        dateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar calendar = Calendar.getInstance();

                currentYear = calendar.get(Calendar.YEAR);
                currentMonth = calendar.get(Calendar.MONTH);
                currentDay = calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(view.getContext(),
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker datePicker,
                                                  int chosenYear, int chosenMonth, int chosenDay) {
                                mChosenYear = chosenYear;
                                mChosenMonth = chosenMonth;
                                mChosenDay = chosenDay;

                                dateDisplay.setText(
                                        new StringBuilder().append(chosenDay).append("/")
                                                .append(chosenMonth).append("/")
                                                .append(chosenYear).toString());
                            }
                        }, currentYear, currentMonth, currentDay);
                datePickerDialog.show();
            }
        });

        startTimeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar calendar = Calendar.getInstance();

                currentHour = calendar.get(Calendar.HOUR_OF_DAY);
                currentMinute = calendar.get(Calendar.MINUTE);

                final boolean isTwentyFourHoursView = true;

                TimePickerDialog timePickerDialog = new TimePickerDialog(view.getContext(),
                        android.R.style.Theme_Holo_Light_Dialog,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker timePicker, int chosenHour, int chosenMinute) {
                                mChosenStartHour = chosenHour;
                                mChosenStartMinute = chosenMinute;

                                startTimeDisplay.setText(new StringBuilder()
                                        .append(chosenHour)
                                        .append(":").append(chosenMinute));
                            }
                        }, currentHour, currentMinute, isTwentyFourHoursView);
                timePickerDialog.show();
            }
        });

        endTimeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar calendar = Calendar.getInstance();

                currentHour = calendar.get(Calendar.HOUR_OF_DAY);
                currentMinute = calendar.get(Calendar.MINUTE);

                final boolean isTwentyFourHoursView = true;

                TimePickerDialog timePickerDialog = new TimePickerDialog(view.getContext(),
                        android.R.style.Theme_Holo_Light_Dialog,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker timePicker, int chosenHour, int chosenMinute) {
                                mChosenEndHour = chosenHour;
                                mChosenEndMinute = chosenMinute;

                                endTimeDisplay.setText(new StringBuilder()
                                        .append(chosenHour)
                                        .append(":").append(chosenMinute));
                            }
                        }, currentHour, currentMinute, isTwentyFourHoursView);
                timePickerDialog.show();
            }
        });

        TextWatcher afterTextChangedListener = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                makeTimeButton.setEnabled(validateFormFields());
            }
        };
        dateDisplay.addTextChangedListener(afterTextChangedListener);
        startTimeDisplay.addTextChangedListener(afterTextChangedListener);
        endTimeDisplay.addTextChangedListener(afterTextChangedListener);

        makeTimeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validateFormFields()) {
                    viewModel.createNewTimeSlot(userName,
                            LocalDate.of(mChosenYear, mChosenMonth, mChosenDay),
                            LocalTime.of(mChosenStartHour, mChosenStartMinute),
                            LocalTime.of(mChosenEndHour, mChosenEndMinute));

                    Intent intent = new Intent(getApplicationContext(), ProducerMenuActivity.class);
                    intent.putExtra(Intent.EXTRA_USER, userName);
                    startActivity(intent);
                }
            }
        });
    }

    private boolean validateFormFields() {
        return !dateDisplay.getText().toString().isEmpty() &&
                !startTimeDisplay.getText().toString().isEmpty() &&
                !endTimeDisplay.getText().toString().isEmpty();
    }
}
