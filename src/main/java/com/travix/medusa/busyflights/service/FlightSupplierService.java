package com.travix.medusa.busyflights.service;

import com.travix.medusa.busyflights.domain.SupplierRequest;
import com.travix.medusa.busyflights.domain.SupplierResponse;
import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsRequest;
import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsResponse;

import java.util.stream.Stream;

public interface FlightSupplierService<R extends SupplierRequest, S extends SupplierResponse> {
    Stream<S> supplierSearch(R supplierRequest) throws Exception;

    R map(BusyFlightsRequest flightsRequest);
    BusyFlightsResponse map(S supplierResponse);

    default Stream<BusyFlightsResponse> search(BusyFlightsRequest flightsRequest) throws Exception {
        return this.supplierSearch(this.map(flightsRequest)).map(this::map);
    }
}