package com.example.phasetwo.activities.ui.producer;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.phasetwo.R;
import com.example.phasetwo.activities.data.producer.ProducerRepository;
import com.example.phasetwo.logic.TimeSlot;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

        repository.checkIfTimeSlotExists(timeSlot, new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    if (task.getResult().isEmpty()) {
                        // No matching time slots -> now we check for overlapping ones
                        repository.getAllUnCompletedTimeSlots(timeSlot,
                                new OnCompleteListener<QuerySnapshot>() {
                                    @Override
                                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                        if (task.isSuccessful()) {
                                            List<TimeSlot> potentiallyOverlappingTimeSlots = new ArrayList<>();

                                            for (QueryDocumentSnapshot document : task.getResult()) {
                                                Log.d(TAG, document.getId() + " => " + document.getData());

                                                TimeSlot existingTimeSlot = document.toObject(TimeSlot.class);
                                                potentiallyOverlappingTimeSlots.add(existingTimeSlot);
                                            }

                                            boolean createTimeSlot = true;

                                            for (TimeSlot existingTimeSlot : potentiallyOverlappingTimeSlots) {
                                                if (existingTimeSlot.getStartPoint().before(timeSlot.getStartPoint())) {
                                                    if (!existingTimeSlot.getEndPoint().before(timeSlot.getStartPoint())) {
                                                        createTimeSlot = false;
                                                        break;
                                                    }
                                                } else {
                                                    if (!existingTimeSlot.getStartPoint().after(timeSlot.getEndPoint())) {
                                                        createTimeSlot = false;
                                                        break;
                                                    }
                                                }
                                            }

                                            if (createTimeSlot) {
                                                repository.createTimeSlot(timeSlot,
                                                        new OnSuccessListener<DocumentReference>() {
                                                            @Override
                                                            public void onSuccess(DocumentReference documentReference) {
                                                                Log.d(TAG, "DocumentSnapshot written with ID: " + documentReference.getId());
                                                                creationResult.postValue(new TimeSlotCreationResult(timeSlot));
                                                            }
                                                        },
                                                        new OnFailureListener() {
                                                            @Override
                                                            public void onFailure(@NonNull Exception e) {
                                                                Log.w(TAG, "Error adding document", e);
                                                                creationResult.postValue(new TimeSlotCreationResult(R.string.
                                                                        activity_producer_make_time_creation_fail_msg));
                                                            }
                                                        });
                                            } else {
                                                creationResult.postValue(new TimeSlotCreationResult(
                                                        R.string.activity_producer_make_time_overlap_fail_msg));
                                            }
                                        } else {
                                            Log.d(TAG, "Error getting documents: ", task.getException());
                                        }
                                    }
                                });
                    } else {
                        creationResult.postValue(new TimeSlotCreationResult(
                                R.string.activity_producer_make_time_already_exists_fail_msg));
                    }
                } else {
                    Log.d(TAG, "Error getting documents: ", task.getException());
                }
            }
        });
    }
}
