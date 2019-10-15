package com.example.phasetwo.activities.data.producer;

import com.example.phasetwo.activities.data.Result;
import com.example.phasetwo.logic.TimeSlotEntity;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class TimeSlotRepository {
    
    private static volatile TimeSlotRepository instance;
    
    private TimeSlotDataSource dataSource;
    
    private TimeSlotRepository(TimeSlotDataSource dataSource) { this.dataSource = dataSource; }

    public static TimeSlotRepository getInstance(TimeSlotDataSource dataSource) {
        if (instance == null)
            instance = new TimeSlotRepository(dataSource);

        return instance;
    }

    public Result<List<TimeSlotEntity>> getTimeSlots(String userName, int numberOfTimeSlots) {
        Result<List<TimeSlotEntity>> result = dataSource.getTimeSlots(userName, numberOfTimeSlots);

        if (result instanceof Result.Success) {
            //TODO: caching happens here
        }
        return result;
    }

    public Result<Boolean> createNewTimeSlot(String userId, LocalDate date,
                                             LocalTime startTime, LocalTime endTime) {
        return dataSource.createNewTimeSlot(userId, date, startTime, endTime);
    }
}
