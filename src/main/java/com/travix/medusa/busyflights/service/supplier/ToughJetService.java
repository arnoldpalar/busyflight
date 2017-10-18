package com.travix.medusa.busyflights.service.supplier;

import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsRequest;
import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsResponse;
import com.travix.medusa.busyflights.domain.toughjet.ToughJetRequest;
import com.travix.medusa.busyflights.domain.toughjet.ToughJetResponse;
import com.travix.medusa.busyflights.service.FlightSupplierService;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.stream.Stream;

@Service
public class ToughJetService implements FlightSupplierService<ToughJetRequest, ToughJetResponse> {
    @Override
    public Stream<ToughJetResponse> supplierSearch(ToughJetRequest supplierRequest) throws Exception {
        Assert.notNull(supplierRequest, "Empty request");

        //TODO TBI

        return Stream.empty();
    }

    public static ToughJetRequest _map(BusyFlightsRequest flightsRequest) {
        return ToughJetRequest.builder()
                .from(flightsRequest.getOrigin())
                .to(flightsRequest.getDestination())
                .outboundDate(flightsRequest.getDepartureDate())
                .inboundDate(flightsRequest.getReturnDate())
                .build();
    }

    @Override
    public ToughJetRequest map(BusyFlightsRequest flightsRequest) {
        return ToughJetService._map(flightsRequest);
    }

    public static BusyFlightsResponse _map(ToughJetResponse supplierResponse) {
        BigDecimal fare = new BigDecimal(supplierResponse.getBasePrice()).add(new BigDecimal(supplierResponse.getTax()));
        fare = fare.subtract(fare.multiply(new BigDecimal(supplierResponse.getDiscount())).divide(new BigDecimal(100), new MathContext(2, RoundingMode.HALF_UP)));

        return BusyFlightsResponse.builder()
                .supplier("ToughJet")
                .airline(supplierResponse.getCarrier())
                .departureAirportCode(supplierResponse.getDepartureAirportName())
                .destinationAirportCode(supplierResponse.getArrivalAirportName())
                .departureDate(supplierResponse.getOutboundDateTime())
                .arrivalDate(supplierResponse.getInboundDateTime())
                .fare(fare.doubleValue())
                .build();
    }

    @Override
    public BusyFlightsResponse map(ToughJetResponse supplierResponse) {
        return ToughJetService._map(supplierResponse);
    }
}