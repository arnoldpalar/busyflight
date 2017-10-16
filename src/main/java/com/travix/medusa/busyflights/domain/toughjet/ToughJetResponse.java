package com.travix.medusa.busyflights.domain.toughjet;

import com.travix.medusa.busyflights.domain.SupplierResponse;
import lombok.*;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class ToughJetResponse implements SupplierResponse {

    private String carrier;
    private Double basePrice;
    private Double tax;
    private Float discount;
    private String departureAirportName;
    private String arrivalAirportName;
    private Date outboundDateTime;
    private Date inboundDateTime;

}
