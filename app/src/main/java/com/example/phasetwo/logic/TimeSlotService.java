package com.example.phasetwo.logic;

import com.example.phasetwo.common.TimeSlotDoesNotExistException;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface TimeSlotService {

    List<TimeSlot> getAllTimeSlots();

    List<TimeSlot> getAllTimeSlotsByOwner(UserEntity owner);

    List<TimeSlot> getAllTimeSlotsByAcquirer(UserEntity acquirer);

    TimeSlot createNewTimeSlot(UserEntity owner, LocalDate date, LocalTime startingTime,
                               LocalTime endingTime);

    void bookTimeSlot(TimeSlot timeSlot, UserEntity consumer) throws TimeSlotDoesNotExistException;
}
