package com.example.phasetwo.logic.stubs;

import com.example.phasetwo.common.TimeSlotDoesNotExistException;
import com.example.phasetwo.common.UserDoesNotExistException;
import com.example.phasetwo.logic.ConsumerBookingService;
import com.example.phasetwo.logic.ConsumerEntity;
import com.example.phasetwo.logic.TimeSlot;
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
    public List<TimeSlot> getProducerAvailableTimeSlotsForConsumer(String consumerEmail) throws UserDoesNotExistException {
        List<TimeSlot> results = new ArrayList<>();

        ConsumerEntity consumer = (ConsumerEntity) userManagementService.getUserByEmail(consumerEmail);

        List<UserEntity> associatedProducers = consumer.getProducers();
        for (UserEntity producer: associatedProducers
             ) {
                List<TimeSlot> producerTimeSlots = timeSlotService.getAllTimeSlotsByOwner(producer);
            for (TimeSlot timeSlot: producerTimeSlots
                 ) {
                if (! timeSlot.isBooked())
                    results.add(timeSlot);
            }
        }
        return results;
    }

    @Override
    public void bookTimeSlot(String consumerEmail, TimeSlot timeSlot) throws UserDoesNotExistException, TimeSlotDoesNotExistException {
        UserEntity consumer = userManagementService.getUserByEmail(consumerEmail);
        timeSlotService.bookTimeSlot(timeSlot, consumer);
    }

    @Override
    public void cancelBookedTimeSlot(String consumerEmail, TimeSlot timeSlot) {
        //TODO: needs implementation
    }
}
