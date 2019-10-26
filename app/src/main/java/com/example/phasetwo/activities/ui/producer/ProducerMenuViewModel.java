package com.example.phasetwo.activities.ui.producer;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.phasetwo.activities.data.producer.ProducerRepository;
import com.example.phasetwo.logic.TimeSlot;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

class ProducerMenuViewModel extends ViewModel {

    private static final String TAG = ProducerMenuViewModel.class.getSimpleName();

    private ProducerRepository producerRepository;

    private MutableLiveData<List<TimeSlot>> timeSlots = new MutableLiveData<>();

    ProducerMenuViewModel(ProducerRepository producerRepository) {
        this.producerRepository = producerRepository;
    }

    public LiveData<List<TimeSlot>> getTimeSlots() {
        return timeSlots;
    }

    public void getBookedTimeSlots(String producerUid) {
        getBookedTimeSlotsFromRepository(producerUid, null, null);
    }

    public void getBookedTimeSlots(String producerUid, Date since, Integer numberOfTimeSlots) {
        getBookedTimeSlotsFromRepository(producerUid, since, numberOfTimeSlots);
    }

    private void getBookedTimeSlotsFromRepository(String producerUid, Date since, Integer numberOfTimeSlots) {
        OnCompleteListener<QuerySnapshot> onCompleteListener = new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    List<TimeSlot> getResults = new ArrayList<>();

                    for (QueryDocumentSnapshot document : task.getResult()) {
                        Log.d(TAG, document.getId() + " => " + document.getData());
                        getResults.add(document.toObject(TimeSlot.class));
                    }

                    timeSlots.setValue(getResults);
                } else {
                    Log.d(TAG, "Error getting documents: ", task.getException());
                    timeSlots.setValue(null);
                }
            }
        };

        if (since != null && numberOfTimeSlots != null) {
            producerRepository.getBookedTimeSlots(producerUid, since, numberOfTimeSlots, onCompleteListener);
        } else {
            producerRepository.getBookedTimeSlots(producerUid, onCompleteListener);
        }
    }
}
