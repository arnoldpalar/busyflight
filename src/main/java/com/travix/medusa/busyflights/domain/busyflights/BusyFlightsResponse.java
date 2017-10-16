package com.travix.medusa.busyflights.domain.busyflights;

import lombok.*;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class BusyFlightsResponse {

    String airline; //Name of Airline
    String supplier;	//Eg: CrazyAir or ToughJet
    Double fare;	//Total price rounded to 2 decimals
    String departureAirportCode;	//3 letter IATA code(eg. LHR, AMS)
    String destinationAirportCode;	//3 letter IATA code(eg. LHR, AMS)
    Date departureDate; //ISO_DATE_TIME format
    Date arrivalDate;	//ISO_DATE_TIME format

}
