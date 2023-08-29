package com.gridnine.testing;

import com.gridnine.testing.factory.FlightBuilder;
import com.gridnine.testing.model.Flight;
import com.gridnine.testing.service.FlightFilter;

import java.util.List;

public class Main {
    public static void main(String[] args) {

        final List<Flight> flights = FlightBuilder.createFlights();

        System.out.println("Исключение вылетов до текущего момента времени");
        flights.stream()
                .filter(FlightFilter::departsInPast)
                .forEach(System.out::println);

        System.out.println("Исключение вылетов, имеющих сегменты с датой прилета раньше даты вылета");
        flights.stream()
                .filter(FlightFilter::arrivesBeforeDeparts)
                .forEach(System.out::println);

        System.out.println("Исключение вылетов с общим временем на земле более двух часов");
        flights.stream()
                .filter(FlightFilter::moreThanTwoHoursOnTheGround)
                .forEach(System.out::println);
    }
}