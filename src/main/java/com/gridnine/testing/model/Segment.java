package com.gridnine.testing.model;

import java.time.LocalDateTime;
import java.util.Objects;

import static com.gridnine.testing.utils.DateTimeFormat.FORMATTER;

/**
 * Bean that represents a flight segment.
 */
public class Segment {
    private final LocalDateTime departureDateTime;

    private final LocalDateTime arrivalDateTime;

    public Segment(final LocalDateTime dep, final LocalDateTime arr) {
        departureDateTime = Objects.requireNonNull(dep);
        arrivalDateTime = Objects.requireNonNull(arr);
    }

    public LocalDateTime getDepartureDateTime() {
        return departureDateTime;
    }

    public LocalDateTime getArrivalDateTime() {
        return arrivalDateTime;
    }

    @Override
    public String toString() {
        return '[' + departureDateTime.format(FORMATTER) + '|' + arrivalDateTime.format(FORMATTER)
                + ']';
    }
}
