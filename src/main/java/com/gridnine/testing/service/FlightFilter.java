package com.gridnine.testing.service;

import com.gridnine.testing.model.Flight;
import com.gridnine.testing.model.Segment;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

/**
 * Class containing filters for flights
 */
public class FlightFilter {

    /**
     * Исключение перелётов с временем вылета до текущего момента
     */
    public static boolean departsInPast(Flight flight) {
        return flight.getSegments().stream()
                .noneMatch(segment -> segment.getDepartureDateTime().isBefore(LocalDateTime.now()));
    }

    /**
     * Исключение перелётов, имеющих сегменты с датой прилёта раньше даты вылета
     */
    public static boolean arrivesBeforeDeparts(Flight flight) {
        return flight.getSegments().stream()
                .noneMatch(segment -> segment.getArrivalDateTime().isBefore(segment.getDepartureDateTime()));
    }

    /**
     * Исключение перелётов, в которых общее время, проведённое на земле превышает два часа
     */
    public static boolean moreThanTwoHoursOnTheGround(Flight flight) {
        final long TWO_HOURS = 120;
        List<Segment> segments = flight.getSegments();
        if (segments.size() == 1) {
            return true;
        }
        long timeOnTheGround = 0;
        for (int i = 0; i < segments.size() - 1; i++) {
            timeOnTheGround += ChronoUnit.MINUTES.between(segments.get(i).getArrivalDateTime(),
                                                          segments.get(i + 1).getDepartureDateTime());
            if (timeOnTheGround > TWO_HOURS) {
                return false;
            }
        }
        return true;
    }
}
