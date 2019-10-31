package com.example.phasetwo.logic;

import com.example.phasetwo.common.UserType;

import java.util.ArrayList;
import java.util.List;

public class Consumer extends User {

    private List<User> producers;

    public Consumer(String name, String email, String password, UserType userType) {
        super(name, email, password, userType);
        producers = new ArrayList<>();
    }

    public Consumer(String name, String email, String password, UserType userType,
                    List<User> producers) {
        super(name, email, password, userType);
        this.producers = producers;
    }

    public List<User> getProducers() {
        return producers;
    }

    public void setProducers(List<User> producers) {
        this.producers = producers;
    }

    public void addProducer(User producer) {
        if (!producers.contains(producer))
            producers.add(producer);
    }

    public void removeProducer(User producer) {
        producers.remove(producer);
    }
}
