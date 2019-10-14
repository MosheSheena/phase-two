package com.example.phasetwo.logic;

import com.example.phasetwo.common.UserType;

import java.util.ArrayList;
import java.util.List;

public class ConsumerEntity extends UserEntity {

    private List<UserEntity> producers;

    public ConsumerEntity(String name, String email, String password, UserType userType) {
        super(name, email, password, userType);
        producers = new ArrayList<>();
    }

    public ConsumerEntity(String name, String email, String password, UserType userType,
                          List<UserEntity> producers) {
        super(name, email, password, userType);
        this.producers = producers;
    }

    public List<UserEntity> getProducers() {
        return producers;
    }

    public void setProducers(List<UserEntity> producers) {
        this.producers = producers;
    }

    public void addProducer(UserEntity producer) {
        if (!producers.contains(producer))
            producers.add(producer);
    }

    public void removeProducer(UserEntity producer) {
        producers.remove(producer);
    }
}
