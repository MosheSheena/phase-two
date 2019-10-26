package com.example.phasetwo.activities.ui.producer;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.phasetwo.R;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class ProducerMakeTimeActivity extends AppCompatActivity {

    private static final String TAG = ProducerMakeTimeActivity.class.getSimpleName();

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

    private String uid;

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
            uid = intent.getStringExtra(Intent.EXTRA_USER);
        }

        dateDisplay = findViewById(R.id.producerMakeTimeDateText);
        startTimeDisplay = findViewById(R.id.producerMakeTimeStartTimeText);
        endTimeDisplay = findViewById(R.id.producerMakeTimeEndTimeText);
        dateButton = findViewById(R.id.producerMakeTimeDateButton);
        startTimeButton = findViewById(R.id.producerMakeTimeStartTimeButton);
        endTimeButton = findViewById(R.id.producerMakeTimeEndTimeButton);
        makeTimeButton = findViewById(R.id.producerMakeTimeMakeTimeButton);

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
                                        .append(":").append(chosenMinute < 10 ? "0"+chosenMinute: chosenMinute));
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
                                        .append(":").append(chosenMinute < 10 ? "0"+chosenMinute: chosenMinute));
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

        viewModel.getCreationResult().observe(this, new Observer<TimeSlotCreationResult>() {

            @Override
            public void onChanged(TimeSlotCreationResult timeSlotCreationResult) {
                if (timeSlotCreationResult.getSuccess() != null) {
                    Intent intent = new Intent(getApplicationContext(), ProducerMenuActivity.class);
                    intent.putExtra(Intent.EXTRA_USER, uid);
                    startActivity(intent);
                } else {
                    if (timeSlotCreationResult.getError() != null) {
                        int errorMessage = timeSlotCreationResult.getError();
                        Toast.makeText(getApplicationContext(), errorMessage, Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        makeTimeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validateFormFields()) {
                    Date start = new GregorianCalendar(mChosenYear, mChosenMonth, mChosenDay,
                            mChosenStartHour, mChosenStartMinute).getTime();
                    Date end = new GregorianCalendar(mChosenYear, mChosenMonth, mChosenDay,
                            mChosenEndHour, mChosenEndMinute).getTime();

                    viewModel.createNewTimeSlot(uid, start, end);
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
