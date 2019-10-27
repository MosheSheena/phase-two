package com.example.phasetwo.activities.data.consumer;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

public class ConsumerFirebaseHelper {

    private static final ConsumerFirebaseHelper instance = new ConsumerFirebaseHelper();
    FirebaseFirestore db;

    private final String PRODUCER_TIME_SLOT_COLLECTION = "producerTimeSlots";
    private final String BOOKED_FIELD = "booked";
    private final String CONSUMER_UID_FIELD = "consumerId";
    private final String COMPLETE_FIELD = "complete";

    public static ConsumerFirebaseHelper getInstance() {
        return instance;
    }

    private ConsumerFirebaseHelper() { db = FirebaseFirestore.getInstance(); }

    public void getAvailableTimeSlotsForConsumer(OnCompleteListener<QuerySnapshot> onCompleteListener) {
        db.collection(PRODUCER_TIME_SLOT_COLLECTION)
                .whereEqualTo(BOOKED_FIELD, false)
                .get()
                .addOnCompleteListener(onCompleteListener);
    }

    public void getConsumerFutureBookings(String consumerUid,
                                          OnCompleteListener<QuerySnapshot> onCompleteListener) {
        db.collection(PRODUCER_TIME_SLOT_COLLECTION)
                .whereEqualTo(CONSUMER_UID_FIELD, consumerUid)
                .whereEqualTo(BOOKED_FIELD, true)
                .whereEqualTo(COMPLETE_FIELD, false)
                .get()
                .addOnCompleteListener(onCompleteListener);
    }

    public void bookTimeSlot(String timeSlotId, String consumerUid,
                             OnSuccessListener<Void> onSuccessListener,
                             OnFailureListener onFailureListener) {
        DocumentReference documentReference = db.collection(PRODUCER_TIME_SLOT_COLLECTION)
                .document(timeSlotId);

        documentReference.update(BOOKED_FIELD, true,
                CONSUMER_UID_FIELD, consumerUid)
                .addOnSuccessListener(onSuccessListener)
                .addOnFailureListener(onFailureListener);
    }
}
