package com.example.phasetwo.activities.ui.producer;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.phasetwo.activities.data.Result;
import com.example.phasetwo.activities.data.producer.ProducerRepository;
import com.example.phasetwo.logic.TimeSlot;

import java.util.List;

class ProducerMenuViewModel extends ViewModel {

    private MutableLiveData<List<TimeSlot>> timeSlots = new MutableLiveData<>();
    private ProducerRepository producerRepository;

    ProducerMenuViewModel(ProducerRepository producerRepository) {
        this.producerRepository = producerRepository;
    }

    public LiveData<List<TimeSlot>> getTimeSlots() {
        return timeSlots;
    }

    public void fetchTimeSlots(String userName, int numberOfTimeSlots) {
        Result<List<TimeSlot>> result = producerRepository.getTimeSlots(userName, numberOfTimeSlots);
        if (result instanceof Result.Success) {
            List<TimeSlot> data = ((Result.Success<List<TimeSlot>>) result).getData();
            timeSlots.setValue(data);
        }
    }
}
