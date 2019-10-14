package com.example.phasetwo.logic;

import com.example.phasetwo.common.UserDoesNotExistException;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface ProducerTimeManagementService {

    void createNewTimeSlot(String producerEmail, LocalDate date, LocalTime startingTime,
                           LocalTime endingTime) throws UserDoesNotExistException;

    void cancelBookedTimeSlot(String producerEmail, TimeSlotEntity timeSlot) throws UserDoesNotExistException;

    List<TimeSlotEntity> getAllTimeSlots(String producerEmail) throws UserDoesNotExistException;

    List<TimeSlotEntity> getAllBookedTimeSlots(String producerEmail) throws UserDoesNotExistException;

    List<TimeSlotEntity> getAllUnBookedTimeSlots(String producerEmail) throws UserDoesNotExistException;
}
