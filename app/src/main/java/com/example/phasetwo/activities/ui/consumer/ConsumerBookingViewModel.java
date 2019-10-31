package com.example.phasetwo.activities.ui.consumer;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.phasetwo.activities.data.consumer.ConsumerRepository;
import com.example.phasetwo.logic.TimeSlot;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

class ConsumerBookingViewModel extends ViewModel {

    private static final String TAG = ConsumerBookingViewModel.class.getSimpleName();

    private MutableLiveData<List<TimeSlot>> availableTimeSlots = new MutableLiveData<>();
    private ConsumerRepository repository;

    ConsumerBookingViewModel(ConsumerRepository repository) {
        this.repository = repository;
    }

    public LiveData<List<TimeSlot>> getAvailableTimeSlots() {
        return availableTimeSlots;
    }

    public void getAvailableTimeSlotsForConsumer(String consumerUid) {
        repository.getAvailableTimeSlotsForConsumer(new OnCompleteListener<QuerySnapshot>() {
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
                    }

                    availableTimeSlots.setValue(getResults);

                } else {
                    Log.d(TAG, "Error getting documents: ", task.getException());
                    availableTimeSlots.setValue(null);
                }
            }
        });
    }

    public void bookTimeSlot(String timeSlotId, String consumerUid) {
        repository.bookTimeSlot(timeSlotId, consumerUid,
                new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d(TAG, "DocumentSnapshot successfully updated!");
                    }
                }, new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error updating document", e);
                    }
                });
    }
}
