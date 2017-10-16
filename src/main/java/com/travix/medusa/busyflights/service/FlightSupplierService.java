package com.travix.medusa.busyflights.service;

import com.travix.medusa.busyflights.domain.SupplierRequest;
import com.travix.medusa.busyflights.domain.SupplierResponse;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.stream.Stream;

@Service
public class FlightSupplierService {

    public FlightSupplierService() {}

    public Stream<SupplierResponse> search(SupplierRequest supplierRequest) throws Exception {
        Assert.notNull(supplierRequest, "Empty request");

        //TODO TBI

        return Stream.empty();
    }
}