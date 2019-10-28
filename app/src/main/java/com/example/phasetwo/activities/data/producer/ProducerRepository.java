package com.example.phasetwo.activities.data.producer;

import com.example.phasetwo.activities.data.Result;
import com.example.phasetwo.logic.TimeSlot;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.Date;

public class ProducerRepository {

    private static final String TAG = ProducerRepository.class.getSimpleName();

    private static final ProducerRepository instance = new ProducerRepository();

    private ProducerFirebaseHelper firebaseHelper;

    private ProducerRepository() {
        firebaseHelper = ProducerFirebaseHelper.getInstance();
    }

    public static ProducerRepository getInstance() {
        return instance;
    }

    public void getTimeSlots(String producerUid,
                             OnCompleteListener<QuerySnapshot> onCompleteListener) {
        firebaseHelper.getTimeSlots(producerUid, onCompleteListener);
    }

    public void getTimeSlots(String producerUid, Date since, int numberOfTimeSlots,
                                               OnCompleteListener<QuerySnapshot> onCompleteListener) {
    }

    public void getBookedTimeSlots(String producerUid,
                                   OnCompleteListener<QuerySnapshot> onCompleteListener) {
        firebaseHelper.getBookedTimeSlots(producerUid, onCompleteListener);
    }

    public void getBookedTimeSlots(String producerUid, Date since, int numberOfTimeSlots,
                                   OnCompleteListener<QuerySnapshot> onCompleteListener) {
        firebaseHelper.getBookedTimeSlots(producerUid, onCompleteListener);
    }

    public void createTimeSlot(TimeSlot timeSlot,
                               OnSuccessListener<DocumentReference> onSuccessListener,
                               OnFailureListener onFailureListener) {
        firebaseHelper.createNewTimeSlot(timeSlot, onSuccessListener, onFailureListener);
    }

    public void checkIfTimeSlotExists(TimeSlot timeSlot,
                                      OnCompleteListener<QuerySnapshot> onCompleteListener) {
        firebaseHelper.checkIfTimeSlotExists(timeSlot, onCompleteListener);
    }

    public void getAllUnCompletedTimeSlots(TimeSlot timeSlot,
                                           OnCompleteListener<QuerySnapshot> onCompleteListener) {
        firebaseHelper.getAllUnCompletedTimeSlots(timeSlot, onCompleteListener);
    }

    public Result<Boolean> cancelBookedTimeSlot(String timeSlotId, String reason) {
        return null;
    }
}
