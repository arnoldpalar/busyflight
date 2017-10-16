package com.travix.medusa.busyflights.domain.toughjet;

import com.travix.medusa.busyflights.domain.SupplierRequest;
import lombok.*;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class ToughJetRequest implements SupplierRequest {

    private String from;
    private String to;
    private Date outboundDate;
    private Date inboundDate;
    private Short numberOfAdults;

}
