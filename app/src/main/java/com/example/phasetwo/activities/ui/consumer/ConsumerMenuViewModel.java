package com.example.phasetwo.activities.ui.consumer;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.phasetwo.activities.data.Result;
import com.example.phasetwo.activities.data.consumer.ConsumerRepository;
import com.example.phasetwo.logic.TimeSlot;

import java.util.List;

class ConsumerMenuViewModel extends ViewModel {

    private MutableLiveData<List<TimeSlot>> bookings = new MutableLiveData<>();
    private ConsumerRepository repository;

    ConsumerMenuViewModel(ConsumerRepository repository) {
        this.repository = repository;
    }

    public LiveData<List<TimeSlot>> getBookings() {
        return bookings;
    }

    public void getConsumerBookings(String userName, int numberOfBookings) {
        Result<List<TimeSlot>> result = repository.getBookings(userName, numberOfBookings);

        if (result instanceof Result.Success) {
            List<TimeSlot> data = ((Result.Success<List<TimeSlot>>) result).getData();
            bookings.setValue(data);
        }
    }
}
