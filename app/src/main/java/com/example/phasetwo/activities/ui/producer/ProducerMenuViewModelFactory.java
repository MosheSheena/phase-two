package com.example.phasetwo.activities.ui.producer;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.phasetwo.activities.data.producer.ProducerRepository;

public class ProducerMenuViewModelFactory implements ViewModelProvider.Factory {

    @NonNull
    @Override
    @SuppressWarnings("unchecked")
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(ProducerMenuViewModel.class)) {
            return (T) new ProducerMenuViewModel(ProducerRepository.getInstance());
        } else {
            throw new IllegalArgumentException("Unknown ViewModel class");
        }
    }
}
