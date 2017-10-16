package com.travix.medusa.busyflights;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.travix.medusa.busyflights.controller.FlightController;
import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsRequest;
import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsResponse;
import com.travix.medusa.busyflights.service.FlightService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Date;
import java.util.stream.Stream;

import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@WebMvcTest(FlightController.class)
public class FlightControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper mapper;

    @MockBean
    private FlightService flightService;

    @Test
    public void searchFlights() throws Exception {
        final BusyFlightsResponse[] flights = new BusyFlightsResponse[] {
                BusyFlightsResponse.builder()
                        .airline("CZ001")
                        .supplier("CrazyAir")
                        .fare(100.50)
                        .departureAirportCode("APA")
                        .destinationAirportCode("APB")
                        .departureDate(new Date())
                        .arrivalDate(new Date())
                        .build(),
                BusyFlightsResponse.builder()
                        .airline("TJ001")
                        .supplier("ToughJet")
                        .fare(200.50)
                        .departureAirportCode("APA")
                        .destinationAirportCode("APB")
                        .departureDate(new Date())
                        .arrivalDate(new Date())
                        .build()
        };

        Mockito.when(flightService.search(Mockito.any(BusyFlightsRequest.class))).thenReturn(Stream.of(flights));

        mvc.perform(
            post("/rest/flight/search")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(new BusyFlightsRequest())))
            .andExpect(status().isOk())
            .andDo(resHandler -> {
                String resJson = resHandler.getResponse().getContentAsString();
                BusyFlightsResponse[] res = mapper.readValue(resJson, BusyFlightsResponse[].class);

                assertEquals(flights.length, res.length);
            });
    }

}