package com.example.phasetwo.activities.data.consumer;

import com.example.phasetwo.activities.data.Result;
import com.example.phasetwo.logic.TimeSlot;

import java.util.List;

public class ConsumerRepository {

    private static volatile ConsumerRepository instance;

    private ConsumerRepository() {}

    public static ConsumerRepository getInstance() {
        if (instance == null) {
            instance = new ConsumerRepository();
        }
        return instance;
    }

    public Result<List<TimeSlot>> getBookings(String userName, int numberOfBookings) {
        return null;
    }

    public Result<List<TimeSlot>> getAvailableTimeSlotsForConsumer(String userName, int numberOfSlotsToGet) {
        return null;
    }
}
