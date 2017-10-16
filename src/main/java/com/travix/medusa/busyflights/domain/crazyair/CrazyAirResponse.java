package com.travix.medusa.busyflights.domain.crazyair;

import com.travix.medusa.busyflights.domain.SupplierResponse;
import lombok.*;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class CrazyAirResponse implements SupplierResponse {

    private String airline;
    private Double price;
    private String cabinClass;
    private String departureAirportCode;
    private String destinationAirportCode;
    private Date departureDate;
    private Date arrivalDate;

}
