package com.example.phasetwo.activities.ui.producer;

import androidx.lifecycle.ViewModel;

import com.example.phasetwo.activities.data.producer.TimeSlotRepository;

import java.time.LocalDate;
import java.time.LocalTime;

public class ProducerMakeTimeViewModel extends ViewModel {

    private TimeSlotRepository repository;

    ProducerMakeTimeViewModel(TimeSlotRepository repository) {
        this.repository = repository;
    }

    public void createNewTimeSlot(String userId, LocalDate date,
                                  LocalTime startTime, LocalTime endTime) {
        repository.createNewTimeSlot(userId, date, startTime, endTime);
    }
}
