package com.example.phasetwo.logic.stubs;

import com.example.phasetwo.common.TimeSlotDoesNotExistException;
import com.example.phasetwo.logic.TimeSlotEntity;
import com.example.phasetwo.logic.TimeSlotService;
import com.example.phasetwo.logic.UserEntity;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class StubTimeSlotService implements TimeSlotService {

    private List<TimeSlotEntity> timeSlots;

    public StubTimeSlotService() {
        this.timeSlots = new ArrayList<>();
    }

    @Override
    public List<TimeSlotEntity> getAllTimeSlots() {
        return timeSlots;
    }

    @Override
    public List<TimeSlotEntity> getAllTimeSlotsByOwner(UserEntity owner) {
        List<TimeSlotEntity> results = new ArrayList<>();

        for (TimeSlotEntity timeSlot: timeSlots
             ) {
            if (timeSlot.getOwner().equals(owner))
                results.add(timeSlot);
        }
        return results;
    }

    @Override
    public List<TimeSlotEntity> getAllTimeSlotsByAcquirer(UserEntity acquirer) {
        List<TimeSlotEntity> results = new ArrayList<>();

        for (TimeSlotEntity timeSlot: timeSlots
        ) {
            if (timeSlot.getAcquirer().equals(acquirer))
                results.add(timeSlot);
        }
        return results;
    }

    @Override
    public TimeSlotEntity createNewTimeSlot(UserEntity owner, LocalDate date,
                                            LocalTime startingTime, LocalTime endingTime) {
        return new TimeSlotEntity(owner, date, startingTime, endingTime);
    }

    @Override
    public void bookTimeSlot(TimeSlotEntity timeSlot, UserEntity consumer) throws TimeSlotDoesNotExistException {
        if (!timeSlots.contains(timeSlot))
            throw new TimeSlotDoesNotExistException("no such time slot: " + timeSlot);

        timeSlot.setBooked(true);
        timeSlot.setAcquirer(consumer);

        int index = timeSlots.indexOf(timeSlot);
        timeSlots.set(index, timeSlot);
    }
}
