package com.example.phasetwo.activities.data.producer;

import com.example.phasetwo.activities.data.Result;
import com.example.phasetwo.common.UserType;
import com.example.phasetwo.logic.TimeSlotEntity;
import com.example.phasetwo.logic.UserEntity;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class TimeSlotDataSource {

    public Result<List<TimeSlotEntity>> getTimeSlots(String userName, int numberOfTimeSlots) {
        //TODO: fetch the user's time slots from Firebase
        try {

            // START - EXPERIMENT STATIC DATA
            UserEntity userEntity = new UserEntity("Earl", "email", "1", UserType.PRODUCER);
            TimeSlotEntity ts1 = new TimeSlotEntity(userEntity,
                    LocalDate.of(2019, 10, 18),
                    LocalTime.of(7, 0),
                    LocalTime.of(7, 30));
            TimeSlotEntity ts2 = new TimeSlotEntity(userEntity,
                    LocalDate.of(2019, 11, 18),
                    LocalTime.of(7, 0),
                    LocalTime.of(7, 30));
            TimeSlotEntity ts3 = new TimeSlotEntity(userEntity,
                    LocalDate.of(2019, 12, 18),
                    LocalTime.of(7, 0),
                    LocalTime.of(7, 30));
            List<TimeSlotEntity> l = new ArrayList<>();
            l.add(ts1);
            l.add(ts2);
            l.add(ts3);
            // END - EXPERIMENT STATIC DATA

            return new Result.Success<>(l);
        } catch (Exception e) {
            return new Result.Error(new IOException("Error fetching time slots for user=" + userName));
        }
    }
}
