package com.example.phasetwo.activities.ui.consumer;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.phasetwo.activities.data.Result;
import com.example.phasetwo.activities.data.consumer.ConsumerRepository;
import com.example.phasetwo.logic.TimeSlot;

import java.util.List;

class ConsumerBookingViewModel extends ViewModel {

    private MutableLiveData<List<TimeSlot>> availableBookings = new MutableLiveData<>();
    private ConsumerRepository repository;

    ConsumerBookingViewModel(ConsumerRepository repository) {
        this.repository = repository;
    }

    public LiveData<List<TimeSlot>> getAvailableBookings() {
        return availableBookings;
    }

    public void getAvailableTimeSlotsForConsumer(String userName, int numberOfSlotsToGet) {
        Result<List<TimeSlot>> result = repository.getAvailableTimeSlotsForConsumer(userName, numberOfSlotsToGet);
        if (result instanceof Result.Success) {
            List<TimeSlot> data = ((Result.Success<List<TimeSlot>>) result).getData();
            availableBookings.setValue(data);
        }
    }
}
