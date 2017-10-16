package com.travix.medusa.busyflights.domain.busyflights;

import lombok.*;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class BusyFlightsRequest {

    private String origin;
    private String destination;
    private Date departureDate;
    private Date returnDate;
    private Integer numberOfPassengers;

}
