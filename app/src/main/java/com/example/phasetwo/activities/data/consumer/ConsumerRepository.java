package com.example.phasetwo.activities.data.consumer;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.QuerySnapshot;

public class ConsumerRepository {

    private static volatile ConsumerRepository instance;

    private ConsumerFirebaseHelper fireBaseHelper;

    private ConsumerRepository() {
        fireBaseHelper = ConsumerFirebaseHelper.getInstance();
    }

    public static ConsumerRepository getInstance() {
        if (instance == null) {
            instance = new ConsumerRepository();
        }
        return instance;
    }

    public void getAvailableTimeSlotsForConsumer(OnCompleteListener<QuerySnapshot> onCompleteListener) {
        fireBaseHelper.getAvailableTimeSlotsForConsumer(onCompleteListener);
    }

    public void getConsumerBookings(String consumerUid,
                                    OnCompleteListener<QuerySnapshot> onCompleteListener) {
        fireBaseHelper.getConsumerBookings(consumerUid, onCompleteListener);
    }

    public void bookTimeSlot(String timeSlotId, String consumerUid,
                             OnSuccessListener<Void> onSuccessListener,
                             OnFailureListener onFailureListener) {
        fireBaseHelper.bookTimeSlot(timeSlotId, consumerUid, onSuccessListener, onFailureListener);
    }

    public void cancelBooking(String timeSlotId, String reason,
                              OnSuccessListener<Void> onSuccessListener,
                              OnFailureListener onFailureListener) {
        fireBaseHelper.cancelBooking(timeSlotId, reason, onSuccessListener, onFailureListener);
    }
}
