package com.example.phasetwo.logic;

import com.example.phasetwo.common.TimeSlotDoesNotExistException;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface TimeSlotService {

    List<TimeSlotEntity> getAllTimeSlots();

    List<TimeSlotEntity> getAllTimeSlotsByOwner(UserEntity owner);

    List<TimeSlotEntity> getAllTimeSlotsByAcquirer(UserEntity acquirer);

    TimeSlotEntity createNewTimeSlot(UserEntity owner, LocalDate date, LocalTime startingTime,
                           LocalTime endingTime);

    void bookTimeSlot(TimeSlotEntity timeSlot, UserEntity consumer) throws TimeSlotDoesNotExistException;
}
