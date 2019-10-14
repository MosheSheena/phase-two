package com.example.phasetwo.models;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.phasetwo.logic.TimeSlotEntity;

import java.util.List;

public class ProducerMainMenuViewModel extends ViewModel {

    private MutableLiveData<List<TimeSlotEntity>> producerTimeSlots;

    public LiveData<List<TimeSlotEntity>> getProducerTimeSlots() {
        if (producerTimeSlots == null) {
            producerTimeSlots = new MutableLiveData<List<TimeSlotEntity>>();

            fetchProducerTimeSlots();
        }
        return producerTimeSlots;
    }

    private void fetchProducerTimeSlots() {

    }
}
