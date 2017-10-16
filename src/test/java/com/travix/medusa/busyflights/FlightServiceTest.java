package com.travix.medusa.busyflights;

import com.travix.medusa.busyflights.domain.SupplierResponse;
import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsRequest;
import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsResponse;
import com.travix.medusa.busyflights.domain.crazyair.CrazyAirRequest;
import com.travix.medusa.busyflights.domain.crazyair.CrazyAirResponse;
import com.travix.medusa.busyflights.domain.toughjet.ToughJetRequest;
import com.travix.medusa.busyflights.domain.toughjet.ToughJetResponse;
import com.travix.medusa.busyflights.service.FlightService;
import com.travix.medusa.busyflights.service.FlightSupplierService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.stream.Stream;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@JsonTest
public class FlightServiceTest {
    @MockBean
    private FlightSupplierService flightSupplierService;

    @MockBean
    private FlightService flightService;

    @Test
    public void testSearch() throws Exception {
        Stream<SupplierResponse> crazyAirFlights = Stream.of(
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

        Stream<SupplierResponse> toughJetFlights = Stream.of(
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

        Mockito.when(flightSupplierService.search(Mockito.any(CrazyAirRequest.class))).thenReturn(crazyAirFlights);
        Mockito.when(flightSupplierService.search(Mockito.any(ToughJetRequest.class))).thenReturn(toughJetFlights);

        Stream<BusyFlightsResponse> flights = flightService.search(new BusyFlightsRequest());

        assertEquals(2, flights.filter(flight -> "CZ001".equals(flight.getAirline())).count());
        assertEquals(2, flights.filter(flight -> "TJ001".equals(flight.getAirline())).count());
    }
}
