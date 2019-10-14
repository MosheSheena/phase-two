package com.example.phasetwo.logic;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

public class TimeSlotEntity {

    private UserEntity owner;
    private LocalDate date;
    private LocalTime startingTime;
    private LocalTime endingTime;
    private boolean booked;
    private UserEntity acquirer;

    public TimeSlotEntity(UserEntity owner, LocalDate date, LocalTime startingTime, LocalTime endingTime) {
        this.owner = owner;
        this.date = date;
        this.startingTime = startingTime;
        this.endingTime = endingTime;
        this.booked = false;
    }

    public UserEntity getOwner() {
        return owner;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getStartingTime() {
        return startingTime;
    }

    public void setStartingTime(LocalTime startingTime) {
        this.startingTime = startingTime;
    }

    public LocalTime getEndingTime() {
        return endingTime;
    }

    public void setEndingTime(LocalTime endingTime) {
        this.endingTime = endingTime;
    }

    public boolean isBooked() {
        return booked;
    }

    public void setBooked(boolean booked) {
        this.booked = booked;
    }

    public UserEntity getAcquirer() {
        return acquirer;
    }

    public void setAcquirer(UserEntity acquirer) {
        this.acquirer = acquirer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TimeSlotEntity timeSlot = (TimeSlotEntity) o;
        return booked == timeSlot.booked &&
                owner.equals(timeSlot.owner) &&
                date.equals(timeSlot.date) &&
                startingTime.equals(timeSlot.startingTime) &&
                endingTime.equals(timeSlot.endingTime) &&
                Objects.equals(acquirer, timeSlot.acquirer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(owner, date, startingTime, endingTime, booked, acquirer);
    }
}
