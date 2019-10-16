package com.example.phasetwo.activities.ui.consumer;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.phasetwo.activities.data.consumer.ConsumerDataSource;
import com.example.phasetwo.activities.data.consumer.ConsumerRepository;

class ConsumerMenuViewModelFactory implements ViewModelProvider.Factory {

    @NonNull
    @Override
    @SuppressWarnings("unchecked")
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(ConsumerMenuViewModel.class)) {
            return (T) new ConsumerMenuViewModel(ConsumerRepository.getInstance(new ConsumerDataSource()));
        } else {
            throw new IllegalArgumentException("Unknown ViewModel class");
        }
    }
}
