package com.travix.medusa.busyflights.domain.crazyair;

import com.travix.medusa.busyflights.domain.SupplierRequest;
import lombok.*;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class CrazyAirRequest implements SupplierRequest {

    private String origin;
    private String destination;
    private Date departureDate;
    private Date returnDate;
    private Integer passengerCount;

}
