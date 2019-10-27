package com.example.phasetwo.activities.ui.consumer;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.phasetwo.activities.data.consumer.ConsumerRepository;
import com.example.phasetwo.logic.TimeSlot;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

class ConsumerMenuViewModel extends ViewModel {

    private static final String TAG = ConsumerMenuViewModel.class.getSimpleName();

    private MutableLiveData<List<TimeSlot>> futureBookings = new MutableLiveData<>();
    private ConsumerRepository repository;

    ConsumerMenuViewModel(ConsumerRepository repository) {
        this.repository = repository;
    }

    public LiveData<List<TimeSlot>> getFutureBookings() {
        return futureBookings;
    }

    public void getConsumerFutureBookings(String consumerUid) {
        repository.getConsumerFutureBookings(consumerUid, new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    List<TimeSlot> getResults = new ArrayList<>();

                    for (QueryDocumentSnapshot document : task.getResult()) {
                        Log.d(TAG, document.getId() + " => " + document.getData());

                        // Set the TimeSlot id member for booking
                        TimeSlot timeSlot = document.toObject(TimeSlot.class);
                        timeSlot.setId(document.getId());

                        getResults.add(timeSlot);

                        futureBookings.setValue(getResults);
                    }
                } else {
                    Log.d(TAG, "Error getting documents: ", task.getException());
                }
            }
        });
    }
}
