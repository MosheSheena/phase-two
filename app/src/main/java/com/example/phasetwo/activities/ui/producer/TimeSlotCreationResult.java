package com.example.phasetwo.activities.ui.producer;

import androidx.annotation.Nullable;

import com.example.phasetwo.logic.TimeSlot;

public class TimeSlotCreationResult {

    @Nullable
    private TimeSlot success;

    @Nullable
    private Integer error;

    TimeSlotCreationResult(@Nullable Integer error) {
        this.error = error;
    }

    TimeSlotCreationResult(@Nullable TimeSlot success) {
        this.success = success;
    }

    @Nullable
    TimeSlot getSuccess() {
        return success;
    }

    @Nullable
    Integer getError() {
        return error;
    }
}
