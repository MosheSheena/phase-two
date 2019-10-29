package com.example.phasetwo.activities.data.producer;

import com.example.phasetwo.logic.TimeSlot;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.time.LocalDate;

public class ProducerFirebaseHelper {

    private static volatile ProducerFirebaseHelper instance;
    FirebaseFirestore db;

    private final String PRODUCER_TIME_SLOT_COLLECTION = "producerTimeSlots";
    private final String BOOKED_FIELD = "booked";
    private final String PRODUCER_ID_FIELD = "producerId";
    private final String START_POINT_FIELD = "startPoint";
    private final String END_POINT_FIELD = "endPoint";
    private final String COMPLETE_FIELD = "complete";

    private ProducerFirebaseHelper() {
        db = FirebaseFirestore.getInstance();
    }

    public static ProducerFirebaseHelper getInstance() {
        if (instance == null)
            instance = new ProducerFirebaseHelper();

        return instance;
    }

    public void getTimeSlots(String uid,
                             OnCompleteListener<QuerySnapshot> onCompleteListener) {
        db.collection(PRODUCER_TIME_SLOT_COLLECTION)
                .whereEqualTo(PRODUCER_ID_FIELD, uid)
                .get()
                .addOnCompleteListener(onCompleteListener);

    }

    public void getTimeSlots(String uid, LocalDate since, int numberOfTimeSlots,
                             OnSuccessListener<Void> onSuccessListener,
                             OnFailureListener onFailureListener) {
    }

    public void createNewTimeSlot(TimeSlot timeSlot,
                                  OnSuccessListener<DocumentReference> onSuccessListener,
                                  OnFailureListener onFailureListener) {
        db.collection(PRODUCER_TIME_SLOT_COLLECTION)
                .add(timeSlot)
                .addOnSuccessListener(onSuccessListener)
                .addOnFailureListener(onFailureListener);
    }

    public void checkIfTimeSlotExists(TimeSlot timeSlot,
                                      OnCompleteListener<QuerySnapshot> onCompleteListener) {
        db.collection(PRODUCER_TIME_SLOT_COLLECTION)
                .whereEqualTo(PRODUCER_ID_FIELD, timeSlot.getProducerId())
                .whereEqualTo(START_POINT_FIELD, timeSlot.getStartPoint())
                .whereEqualTo(END_POINT_FIELD, timeSlot.getEndPoint())
                .get()
                .addOnCompleteListener(onCompleteListener);
    }

    public void getAllUnCompletedTimeSlots(TimeSlot timeSlot,
                                                OnCompleteListener<QuerySnapshot> onCompleteListener) {
        db.collection(PRODUCER_TIME_SLOT_COLLECTION)
                .whereEqualTo(PRODUCER_ID_FIELD, timeSlot.getProducerId())
                .whereEqualTo(COMPLETE_FIELD, false)
                .get()
                .addOnCompleteListener(onCompleteListener);
    }
}
