package com.example.phasetwo.activities.ui.consumer;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.phasetwo.activities.data.consumer.ConsumerRepository;

public class ConsumerBookingViewModelFactory implements ViewModelProvider.Factory {

    @NonNull
    @Override
    @SuppressWarnings("unchecked")
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(ConsumerBookingViewModel.class)) {
            return (T) new ConsumerBookingViewModel(ConsumerRepository.getInstance());
        } else {
            throw new IllegalArgumentException("Unknown ViewModel class");
        }
    }
}
