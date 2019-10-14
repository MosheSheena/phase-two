package com.example.phasetwo.logic;

import com.example.phasetwo.common.TimeSlotDoesNotExistException;
import com.example.phasetwo.common.UserDoesNotExistException;

import java.util.List;

public interface ConsumerBookingService {

    List<UserEntity> getAllProducers();

    void registerConsumerToProducer(String consumerEmail, String producerEmail) throws UserDoesNotExistException;

    List<TimeSlotEntity> getProducerAvailableTimeSlotsForConsumer(String consumerEmail) throws UserDoesNotExistException;

    void bookTimeSlot(String consumerEmail, TimeSlotEntity timeSlot) throws UserDoesNotExistException, TimeSlotDoesNotExistException;

    void cancelBookedTimeSlot(String consumerEmail, TimeSlotEntity timeSlot);
}
