package com.example.phasetwo.logic;

import com.example.phasetwo.common.TimeSlotDoesNotExistException;
import com.example.phasetwo.common.UserDoesNotExistException;

import java.util.List;

public interface ConsumerBookingService {

    List<UserEntity> getAllProducers();

    void registerConsumerToProducer(String consumerEmail, String producerEmail) throws UserDoesNotExistException;

    List<TimeSlot> getProducerAvailableTimeSlotsForConsumer(String consumerEmail) throws UserDoesNotExistException;

    void bookTimeSlot(String consumerEmail, TimeSlot timeSlot) throws UserDoesNotExistException, TimeSlotDoesNotExistException;

    void cancelBookedTimeSlot(String consumerEmail, TimeSlot timeSlot);
}
