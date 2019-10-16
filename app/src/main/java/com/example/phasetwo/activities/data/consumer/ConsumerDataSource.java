package com.example.phasetwo.activities.data.consumer;

import com.example.phasetwo.activities.data.Result;
import com.example.phasetwo.common.UserType;
import com.example.phasetwo.logic.TimeSlot;
import com.example.phasetwo.logic.UserEntity;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class ConsumerDataSource {

    public ConsumerDataSource() {
    }

    public Result<List<TimeSlot>> getBookings(String userName, int numberOfBookings) {
        //TODO: fetch the user's time slots from Firebase
        try {

            // START - EXPERIMENT STATIC DATA
            UserEntity userEntity = new UserEntity("Earl", "email", "1", UserType.PRODUCER);
            TimeSlot ts1 = new TimeSlot(userEntity,
                    LocalDate.of(2019, 10, 18),
                    LocalTime.of(7, 0),
                    LocalTime.of(7, 31));
            TimeSlot ts2 = new TimeSlot(userEntity,
                    LocalDate.of(2019, 11, 18),
                    LocalTime.of(7, 0),
                    LocalTime.of(7, 32));
            TimeSlot ts3 = new TimeSlot(userEntity,
                    LocalDate.of(2019, 12, 18),
                    LocalTime.of(7, 0),
                    LocalTime.of(7, 33));
            List<TimeSlot> l = new ArrayList<>();
            l.add(ts1);
            l.add(ts2);
            l.add(ts3);
            // END - EXPERIMENT STATIC DATA

            return new Result.Success<>(l);
        } catch (Exception e) {
            return new Result.Error(new IOException("Error fetching time slots for user=" + userName));
        }
    }

    public Result<List<TimeSlot>> getAvailableTimeSlotsForConsumer(String userName, int numberOfSlotsToGet) {
        //TODO: fetch the user's time slots from Firebase
        try {

            // START - EXPERIMENT STATIC DATA
            UserEntity userEntity = new UserEntity("Earl", "email", "1", UserType.PRODUCER);
            TimeSlot ts1 = new TimeSlot(userEntity,
                    LocalDate.of(2019, 10, 18),
                    LocalTime.of(7, 0),
                    LocalTime.of(7, 34));
            TimeSlot ts2 = new TimeSlot(userEntity,
                    LocalDate.of(2019, 11, 18),
                    LocalTime.of(7, 0),
                    LocalTime.of(7, 35));
            TimeSlot ts3 = new TimeSlot(userEntity,
                    LocalDate.of(2019, 12, 18),
                    LocalTime.of(7, 0),
                    LocalTime.of(7, 36));
            List<TimeSlot> l = new ArrayList<>();
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
