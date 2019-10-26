package com.example.phasetwo.logic.stubs;

import com.example.phasetwo.common.TimeSlotDoesNotExistException;
import com.example.phasetwo.logic.TimeSlot;
import com.example.phasetwo.logic.TimeSlotService;
import com.example.phasetwo.logic.UserEntity;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class StubTimeSlotService implements TimeSlotService {

    private List<TimeSlot> timeSlots;

    public StubTimeSlotService() {
        this.timeSlots = new ArrayList<>();
    }

    @Override
    public List<TimeSlot> getAllTimeSlots() {
        return timeSlots;
    }

    @Override
    public List<TimeSlot> getAllTimeSlotsByOwner(UserEntity owner) {
//        List<TimeSlot> results = new ArrayList<>();
//
//        for (TimeSlot timeSlot: timeSlots
//             ) {
//            if (timeSlot.getOwner().equals(owner))
//                results.add(timeSlot);
//        }
//        return results;
        return null;
    }

    @Override
    public List<TimeSlot> getAllTimeSlotsByAcquirer(UserEntity acquirer) {
        List<TimeSlot> results = new ArrayList<>();
//
//        for (TimeSlot timeSlot: timeSlots
//        ) {
//            if (timeSlot.getAcquirer().equals(acquirer))
//                results.add(timeSlot);
//        }
        return results;

    }

    @Override
    public TimeSlot createNewTimeSlot(UserEntity owner, LocalDate date,
                                      LocalTime startingTime, LocalTime endingTime) {
//        return new TimeSlot(owner, date, startingTime, endingTime);
        return null;
    }

    @Override
    public void bookTimeSlot(TimeSlot timeSlot, UserEntity consumer) throws TimeSlotDoesNotExistException {
        if (!timeSlots.contains(timeSlot))
            throw new TimeSlotDoesNotExistException("no such time slot: " + timeSlot);

        timeSlot.setBooked(true);
//        timeSlot.setAcquirer(consumer);

        int index = timeSlots.indexOf(timeSlot);
        timeSlots.set(index, timeSlot);
    }
}
