package com.travix.medusa.busyflights;

import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsRequest;
import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsResponse;
import com.travix.medusa.busyflights.domain.crazyair.CrazyAirResponse;
import com.travix.medusa.busyflights.domain.toughjet.ToughJetResponse;
import com.travix.medusa.busyflights.service.FlightService;
import com.travix.medusa.busyflights.service.supplier.CrazyAirService;
import com.travix.medusa.busyflights.service.supplier.ToughJetService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.*;

@ActiveProfiles("FlightServiceTest")
@RunWith(SpringRunner.class)
@SpringBootTest
public class FlightServiceTest {

    @Autowired
    private CrazyAirService crazyAirService;

    @Autowired
    private ToughJetService toughJetService;

    @Autowired
    private FlightService flightService;

    @Test
    public void testSearch() throws Exception {
        Stream<CrazyAirResponse> crazyAirFlights = Stream.of(
            CrazyAirResponse.builder()
                .airline("CZ001")
                .price(100.5d)
                .cabinClass("Economy")
                .departureAirportCode("APA")
                .destinationAirportCode("APB")
                .departureDate(new Date())
                .arrivalDate(new Date())
                .build(),
            CrazyAirResponse.builder()
                .airline("CZ001")
                .price(200.5d)
                .cabinClass("Business")
                .departureAirportCode("APA")
                .destinationAirportCode("APB")
                .departureDate(new Date())
                .arrivalDate(new Date())
                .build()
        );

        Stream<ToughJetResponse> toughJetFlights = Stream.of(
            ToughJetResponse.builder()
                .carrier("TJ001")
                .basePrice(110.5d)
                .tax(2.25d)
                .discount(5.5f)
                .departureAirportName("APA")
                .arrivalAirportName("APB")
                .outboundDateTime(new Date())
                .inboundDateTime(new Date())
                .build(),
            ToughJetResponse.builder()
                .carrier("TJ001")
                .basePrice(210.5d)
                .tax(4.50d)
                .discount(5.5f)
                .departureAirportName("APA")
                .arrivalAirportName("APB")
                .outboundDateTime(new Date())
                .inboundDateTime(new Date())
                .build()
        );

        Mockito.when(crazyAirService.search(Mockito.any(BusyFlightsRequest.class))).thenReturn(crazyAirFlights.map(CrazyAirService::_map));
        Mockito.when(toughJetService.search(Mockito.any(BusyFlightsRequest.class))).thenReturn(toughJetFlights.map(ToughJetService::_map));

        List<BusyFlightsResponse> flights = flightService.search(new BusyFlightsRequest()).collect(Collectors.toList());

        assertEquals(2, flights.stream().filter(flight -> "CZ001".equals(flight.getAirline())).count());
        assertEquals(2, flights.stream().filter(flight -> "TJ001".equals(flight.getAirline())).count());
    }
}