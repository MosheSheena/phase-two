package com.example.phasetwo.activities.data.producer;

import com.example.phasetwo.activities.data.Result;
import com.example.phasetwo.logic.TimeSlot;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class ProducerRepository {
    
    private static volatile ProducerRepository instance;
    private ProducerDataSource dataSource;
    
    private ProducerRepository(ProducerDataSource dataSource) { this.dataSource = dataSource; }

    public static ProducerRepository getInstance(ProducerDataSource dataSource) {
        if (instance == null)
            instance = new ProducerRepository(dataSource);

        return instance;
    }

    public Result<List<TimeSlot>> getTimeSlots(String userName, int numberOfTimeSlots) {
        return dataSource.getTimeSlots(userName, numberOfTimeSlots);
    }

    public Result<Boolean> createNewTimeSlot(String userId, LocalDate date,
                                             LocalTime startTime, LocalTime endTime) {
        return dataSource.createNewTimeSlot(userId, date, startTime, endTime);
    }
}
