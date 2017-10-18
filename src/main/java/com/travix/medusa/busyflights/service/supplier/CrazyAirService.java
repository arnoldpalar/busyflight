package com.travix.medusa.busyflights.service.supplier;

import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsRequest;
import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsResponse;
import com.travix.medusa.busyflights.domain.crazyair.CrazyAirRequest;
import com.travix.medusa.busyflights.domain.crazyair.CrazyAirResponse;
import com.travix.medusa.busyflights.service.FlightSupplierService;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.stream.Stream;

@Service
public class CrazyAirService implements FlightSupplierService<CrazyAirRequest, CrazyAirResponse> {

    public Stream<CrazyAirResponse> supplierSearch(CrazyAirRequest supplierRequest) throws Exception {
        Assert.notNull(supplierRequest, "Empty request");

        //TODO TBI

        return Stream.empty();
    }

    public static CrazyAirRequest _map(BusyFlightsRequest flightsRequest) {
        return CrazyAirRequest.builder()
                .origin(flightsRequest.getOrigin())
                .destination(flightsRequest.getDestination())
                .departureDate(flightsRequest.getDepartureDate())
                .returnDate(flightsRequest.getReturnDate())
                .passengerCount(flightsRequest.getNumberOfPassengers())
                .build();
    }

    @Override
    public CrazyAirRequest map(BusyFlightsRequest flightsRequest) {
        return CrazyAirService._map(flightsRequest);
    }

    public static BusyFlightsResponse _map(CrazyAirResponse supplierResponse) {
        return BusyFlightsResponse.builder()
                .supplier("CrazyAir")
                .airline(supplierResponse.getAirline())
                .departureDate(supplierResponse.getDepartureDate())
                .arrivalDate(supplierResponse.getArrivalDate())
                .departureAirportCode(supplierResponse.getDepartureAirportCode())
                .destinationAirportCode(supplierResponse.getDestinationAirportCode())
                .fare(supplierResponse.getPrice())
                .build();
    }

    @Override
    public BusyFlightsResponse map(CrazyAirResponse supplierResponse) {
        return CrazyAirService._map(supplierResponse);
    }
}