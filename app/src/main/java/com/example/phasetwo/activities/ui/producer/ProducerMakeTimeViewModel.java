package com.example.phasetwo.activities.ui.producer;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.phasetwo.activities.data.producer.ProducerRepository;
import com.example.phasetwo.logic.TimeSlot;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;

import java.util.Date;

public class ProducerMakeTimeViewModel extends ViewModel {

    private static final String TAG = ProducerMakeTimeViewModel.class.getSimpleName();

    private ProducerRepository repository;

    private MutableLiveData<TimeSlotCreationResult> creationResult = new MutableLiveData<>();

    ProducerMakeTimeViewModel(ProducerRepository repository) {
        this.repository = repository;
    }

    public LiveData<TimeSlotCreationResult> getCreationResult() {
        return creationResult;
    }

    public void createNewTimeSlot(String producerUid, Date startPoint, Date endPoint) {
        final TimeSlot timeSlot = new TimeSlot(producerUid, startPoint, endPoint);

        repository.createTimeSlot(timeSlot, new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {
                Log.d(TAG, "DocumentSnapshot written with ID: " + documentReference.getId());
                creationResult.setValue(new TimeSlotCreationResult(timeSlot));
            }
        }, new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.w(TAG, "Error adding document", e);
                creationResult.setValue(new TimeSlotCreationResult(0));
            }
        });
    }
}
