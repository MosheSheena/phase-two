package com.example.phasetwo.activities.ui.consumer;

import androidx.annotation.Nullable;

public class BookingCancellationResult {

    @Nullable
    private Boolean success;

    @Nullable
    private Integer error;

    public BookingCancellationResult(@Nullable Boolean success) {
        this.success = success;
    }

    public BookingCancellationResult(@Nullable Integer error) {
        this.error = error;
    }

    @Nullable
    public Boolean getSuccess() {
        return success;
    }

    @Nullable
    public Integer getError() {
        return error;
    }
}
