package com.example.phasetwo.logic;

import java.util.Date;

public class TimeSlot {

    private String id;
    private String producerId;
    private Date startPoint;
    private Date endPoint;
    private boolean booked;
    private String consumerId;
    private boolean cancelled;
    private String cancellationReason;
    private boolean complete;

    public TimeSlot() {}

    public TimeSlot(String producerId, Date startPoint, Date endPoint) {
        this.id = null;
        this.producerId = producerId;
        this.startPoint = startPoint;
        this.endPoint = endPoint;
        this.booked = false;
        this.consumerId = null;
        this.cancelled = false;
        this.cancellationReason = null;
        this.complete = false;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProducerId() {
        return producerId;
    }

    public void setProducerId(String producerId) {
        this.producerId = producerId;
    }

    public Date getStartPoint() {
        return startPoint;
    }

    public void setStartPoint(Date startPoint) {
        this.startPoint = startPoint;
    }

    public Date getEndPoint() {
        return endPoint;
    }

    public void setEndPoint(Date endPoint) {
        this.endPoint = endPoint;
    }

    public boolean isBooked() {
        return booked;
    }

    public void setBooked(boolean booked) {
        this.booked = booked;
    }

    public String getConsumerId() {
        return consumerId;
    }

    public void setConsumerId(String consumerId) {
        this.consumerId = consumerId;
    }

    public boolean isCancelled() {
        return cancelled;
    }

    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }

    public String getCancellationReason() {
        return cancellationReason;
    }

    public void setCancellationReason(String cancellationReason) {
        this.cancellationReason = cancellationReason;
    }

    public boolean isComplete() {
        return complete;
    }

    public void setComplete(boolean complete) {
        this.complete = complete;
    }

    @Override
    public String toString() {
        return "TimeSlot{" +
                "producerId='" + producerId + '\'' +
                ", startPoint=" + startPoint +
                ", endPoint=" + endPoint +
                ", booked=" + booked +
                ", consumerId='" + consumerId + '\'' +
                '}';
    }
}
