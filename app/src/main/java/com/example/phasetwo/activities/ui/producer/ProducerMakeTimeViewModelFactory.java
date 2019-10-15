package com.example.phasetwo.activities.ui.producer;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.phasetwo.activities.data.producer.TimeSlotDataSource;
import com.example.phasetwo.activities.data.producer.TimeSlotRepository;

public class ProducerMakeTimeViewModelFactory implements ViewModelProvider.Factory {

    @NonNull
    @Override
    @SuppressWarnings("unchecked")
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(ProducerMakeTimeViewModel.class)) {
            return (T) new ProducerMakeTimeViewModel(TimeSlotRepository.getInstance(new TimeSlotDataSource()));
        } else {
            throw new IllegalArgumentException("Unknown ViewModel class");
        }
    }
}
