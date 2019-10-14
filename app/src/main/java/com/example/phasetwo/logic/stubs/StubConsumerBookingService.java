package com.example.phasetwo.logic.stubs;

import com.example.phasetwo.common.UserDoesNotExistException;
import com.example.phasetwo.logic.ConsumerBookingService;
import com.example.phasetwo.logic.ConsumerEntity;
import com.example.phasetwo.logic.TimeSlotEntity;
import com.example.phasetwo.logic.TimeSlotService;
import com.example.phasetwo.logic.UserEntity;
import com.example.phasetwo.logic.UserManagementService;

import java.util.ArrayList;
import java.util.List;

public class StubConsumerBookingService implements ConsumerBookingService {

    private static final String TAG = StubConsumerBookingService.class.getSimpleName();

    private UserManagementService userManagementService;
    private TimeSlotService timeSlotService;

    public StubConsumerBookingService(UserManagementService userManagementService,
                                      TimeSlotService timeSlotService) {
        this.userManagementService = userManagementService;
        this.timeSlotService = timeSlotService;
    }

    @Override
    public List<UserEntity> getAllProducers() {
        return userManagementService.getAllProducers();
    }

    @Override
    public void registerConsumerToProducer(String consumerEmail, String producerEmail) throws UserDoesNotExistException {
        ConsumerEntity consumer = (ConsumerEntity) userManagementService.getUserByEmail(consumerEmail);
        UserEntity producer = userManagementService.getUserByEmail(producerEmail);

        List<UserEntity> consumerProducers = consumer.getProducers();
        if (!consumerProducers.contains(producer))
            consumer.addProducer(producer);
    }

    @Override
    public List<TimeSlotEntity> getProducerAvailableTimeSlotsForConsumer(String consumerEmail) throws UserDoesNotExistException {
        List<TimeSlotEntity> results = new ArrayList<>();

        ConsumerEntity consumer = (ConsumerEntity) userManagementService.getUserByEmail(consumerEmail);

        List<UserEntity> associatedProducers = consumer.getProducers();
        for (UserEntity producer: associatedProducers
             ) {
                List<TimeSlotEntity> producerTimeSlots = timeSlotService.getAllTimeSlotsByOwner(producer);
            for (TimeSlotEntity timeSlot: producerTimeSlots
                 ) {
                if (! timeSlot.isBooked())
                    results.add(timeSlot);
            }
        }
        return results;
    }

    @Override
    public void bookTimeSlot(String consumerEmail, TimeSlotEntity timeSlot) throws UserDoesNotExistException {
        UserEntity consumer = userManagementService.getUserByEmail(consumerEmail);
        timeSlotService.bookTimeSlot(timeSlot, consumer);
    }

    @Override
    public void cancelBookedTimeSlot(String consumerEmail, TimeSlotEntity timeSlot) {
        //TODO: needs implementation
    }
}
