package com.example.phasetwo.logic.stubs;

import com.example.phasetwo.common.UserDoesNotExistException;
import com.example.phasetwo.logic.ProducerTimeManagementService;
import com.example.phasetwo.logic.TimeSlot;
import com.example.phasetwo.logic.TimeSlotService;
import com.example.phasetwo.logic.UserEntity;
import com.example.phasetwo.logic.UserManagementService;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class StubProducerTimeManagementService implements ProducerTimeManagementService {

    private UserManagementService usersService;
    private TimeSlotService timeSlotService;

    public StubProducerTimeManagementService(UserManagementService usersService,
                                             TimeSlotService timeSlotService) {
        this.usersService = usersService;
        this.timeSlotService = timeSlotService;
    }

    @Override
    public void createNewTimeSlot(String producerEmail, LocalDate date, LocalTime startingTime,
                                  LocalTime endingTime) throws UserDoesNotExistException {
        UserEntity producer = usersService.getUserByEmail(producerEmail);
        timeSlotService.createNewTimeSlot(producer, date, startingTime, endingTime);
    }

    @Override
    public void cancelBookedTimeSlot(String producerEmail, TimeSlot timeSlot) {
        //TODO: needs implementation
    }

    @Override
    public List<TimeSlot> getAllTimeSlots(String producerEmail) throws UserDoesNotExistException {
        UserEntity producer = usersService.getUserByEmail(producerEmail);

        return timeSlotService.getAllTimeSlotsByOwner(producer);
    }

    @Override
    public List<TimeSlot> getAllBookedTimeSlots(String producerEmail) throws UserDoesNotExistException {
        List<TimeSlot> results = new ArrayList<>();

        List<TimeSlot> allTimeSlots = getAllTimeSlots(producerEmail);
        for (TimeSlot timeSlot : allTimeSlots
        ) {
            if (timeSlot.isBooked())
                results.add(timeSlot);
        }

        return results;
    }

    @Override
    public List<TimeSlot> getAllUnBookedTimeSlots(String producerEmail) throws UserDoesNotExistException {
        List<TimeSlot> results = new ArrayList<>();

        List<TimeSlot> allTimeSlots = getAllTimeSlots(producerEmail);
        for (TimeSlot timeSlot : allTimeSlots
        ) {
            if (!timeSlot.isBooked())
                results.add(timeSlot);
        }

        return results;
    }
}
