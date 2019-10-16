package com.example.phasetwo.activities.data.consumer;

import com.example.phasetwo.activities.data.Result;
import com.example.phasetwo.logic.TimeSlot;

import java.util.List;

public class ConsumerRepository {

    private static volatile ConsumerRepository instance;
    private ConsumerDataSource dataSource;

    private ConsumerRepository(ConsumerDataSource dataSource) { this.dataSource = dataSource; }

    public static ConsumerRepository getInstance(ConsumerDataSource dataSource) {
        if (instance == null)
            instance = new ConsumerRepository(dataSource);

        return instance;
    }

    public Result<List<TimeSlot>> getBookings(String userName, int numberOfBookings) {
        return dataSource.getBookings(userName, numberOfBookings);
    }
}
