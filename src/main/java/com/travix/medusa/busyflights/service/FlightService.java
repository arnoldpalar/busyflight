package com.travix.medusa.busyflights.service;

import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsRequest;
import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.stream.Stream;

@Service
public class FlightService {

    private final FlightSupplierService flightSupplierService;

    @Autowired
    public FlightService(FlightSupplierService flightSupplierService) {
        this.flightSupplierService = flightSupplierService;
    }

    public Stream<BusyFlightsResponse> search(BusyFlightsRequest flightRequest) throws Exception {
        Assert.notNull(flightRequest, "Empty request");

        //TODO TBI
        // Map flightRequest to every supplier request object
        // Call flightSupplierService.search(supplierRequest) concurrently for evey supplier
        // Wait until every thread is finished

        return Stream.empty();
    }

}