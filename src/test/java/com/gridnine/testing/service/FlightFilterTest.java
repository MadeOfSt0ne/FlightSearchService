package com.gridnine.testing.service;

import com.gridnine.testing.model.Flight;
import com.gridnine.testing.model.Segment;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class FlightFilterTest {

    static Flight correct;
    static Flight departsInPast;
    static Flight arrivalBeforeDeparture;
    static Flight twoHoursOnTheGround;
    static Flight moreThanTwoHoursOnTheGround;
    static Flight lessThanTwoHoursOnTheGround;

    @BeforeAll
    static void setUp() {
        correct = new Flight(List.of(
                new Segment(LocalDateTime.now().plusHours(1), LocalDateTime.now().plusHours(2))));
        departsInPast = new Flight(List.of(
                new Segment(LocalDateTime.now().minusMinutes(5), LocalDateTime.now().plusHours(1))));
        arrivalBeforeDeparture = new Flight(List.of(
                new Segment(LocalDateTime.now().plusHours(3), LocalDateTime.now().plusHours(2))));
        twoHoursOnTheGround = new Flight(List.of(
                new Segment(LocalDateTime.now().plusHours(1), LocalDateTime.now().plusHours(2)),
                new Segment(LocalDateTime.now().plusHours(4), LocalDateTime.now().plusHours(5))));
        moreThanTwoHoursOnTheGround = new Flight(List.of(
                new Segment(LocalDateTime.now().plusHours(1), LocalDateTime.now().plusHours(2)),
                new Segment(LocalDateTime.now().plusMinutes(250), LocalDateTime.now().plusHours(5))));
        lessThanTwoHoursOnTheGround = new Flight(List.of(
                new Segment(LocalDateTime.now().plusHours(1), LocalDateTime.now().plusHours(2)),
                new Segment(LocalDateTime.now().plusMinutes(3), LocalDateTime.now().plusHours(5))));
    }

    @Test
    void returnFalseIfDepartsInPast() {
        assertFalse(FlightFilter.departsInPast(departsInPast));
    }

    @Test
    void returnTrueIfDepartsCorrectly() {
        assertTrue(FlightFilter.departsInPast(correct));
    }

    @Test
    void returnFalseIfArrivesBeforeDeparts() {
        assertFalse(FlightFilter.arrivesBeforeDeparts(arrivalBeforeDeparture));
    }

    @Test
    void returnTrueIfArrivesAfterDeparts() {
        assertTrue(FlightFilter.arrivesBeforeDeparts(lessThanTwoHoursOnTheGround));
    }

    @Test
    void returnFalseIfMoreThanTwoHoursOnTheGround() {
        assertFalse(FlightFilter.moreThanTwoHoursOnTheGround(moreThanTwoHoursOnTheGround));
    }

    @Test
    void returnTrueIfStraightTwoHoursOnTheGround() {
        assertTrue(FlightFilter.moreThanTwoHoursOnTheGround(twoHoursOnTheGround));
    }

    @Test
    void returnTrueIfLessThanTwoHoursOnTheGround() {
        assertTrue(FlightFilter.moreThanTwoHoursOnTheGround(lessThanTwoHoursOnTheGround));
    }
}