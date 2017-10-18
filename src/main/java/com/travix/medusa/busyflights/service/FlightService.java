package com.travix.medusa.busyflights.service;

import com.travix.medusa.busyflights.domain.SupplierRequest;
import com.travix.medusa.busyflights.domain.SupplierResponse;
import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsRequest;
import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;
import java.util.concurrent.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class FlightService {

    private static final Logger LOG = Logger.getLogger(FlightService.class.getName());

    private List<? extends FlightSupplierService<? extends SupplierRequest, ? extends SupplierResponse>> supplierServices;

    @Value("${busyflight.supplier.timeout}")
    private Integer supplierTimeout;

    @Autowired
    public FlightService(List<? extends FlightSupplierService<? extends SupplierRequest, ? extends SupplierResponse>> supplierServices) {
        this.supplierServices = supplierServices;
    }

    public Stream<BusyFlightsResponse> search(BusyFlightsRequest flightRequest) throws Exception {
        Assert.notNull(flightRequest, "Empty request");

        List<CompletableFuture<Stream<BusyFlightsResponse>>> futures =
            supplierServices.stream()
                            .map(ss -> CompletableFuture.supplyAsync(() -> {
                                try {
                                    return ss.search(flightRequest);
                                } catch (Exception e) {
                                    LOG.log(Level.SEVERE, e.getMessage(), e);
                                    return (Stream<BusyFlightsResponse>) null;
                                }
                            })).collect(Collectors.toList());


        Stream.Builder<BusyFlightsResponse> result = Stream.builder();

        futures.stream()
                .map(CompletableFuture::join)
                .forEach(stream -> {if(stream != null) stream.forEach(result);});

        return result.build();
    }

}