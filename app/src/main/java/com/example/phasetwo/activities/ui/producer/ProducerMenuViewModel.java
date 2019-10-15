package com.example.phasetwo.activities.ui.producer;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.phasetwo.activities.data.Result;
import com.example.phasetwo.activities.data.producer.TimeSlotRepository;
import com.example.phasetwo.logic.TimeSlotEntity;

import java.util.List;

class ProducerMenuViewModel extends ViewModel {

    private MutableLiveData<List<TimeSlotEntity>> timeSlots = new MutableLiveData<>();
    private TimeSlotRepository timeSlotRepository;

    //TODO: maybe add a cached member

    ProducerMenuViewModel(TimeSlotRepository timeSlotRepository) {
        this.timeSlotRepository = timeSlotRepository;
    }

    public LiveData<List<TimeSlotEntity>> getTimeSlots() {
        return timeSlots;
    }

    public void fetchTimeSlots(String userName, int numberOfTimeSlots) {
        Result<List<TimeSlotEntity>> result = timeSlotRepository.getTimeSlots(userName, numberOfTimeSlots);
        if (result instanceof Result.Success) {
            List<TimeSlotEntity> data = ((Result.Success<List<TimeSlotEntity>>) result).getData();
            timeSlots.setValue(data);
        }
    }
}
