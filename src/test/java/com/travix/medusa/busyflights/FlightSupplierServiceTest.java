package com.travix.medusa.busyflights;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.travix.medusa.busyflights.domain.SupplierResponse;
import com.travix.medusa.busyflights.domain.crazyair.CrazyAirRequest;
import com.travix.medusa.busyflights.domain.crazyair.CrazyAirResponse;
import com.travix.medusa.busyflights.domain.toughjet.ToughJetRequest;
import com.travix.medusa.busyflights.domain.toughjet.ToughJetResponse;
import com.travix.medusa.busyflights.service.FlightSupplierService;
import com.travix.medusa.busyflights.service.supplier.CrazyAirService;
import com.travix.medusa.busyflights.service.supplier.ToughJetService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.client.MockRestServiceServer;

import java.util.Date;

import static org.springframework.test.web.client.match.MockRestRequestMatchers.*;
import static org.springframework.test.web.client.response.MockRestResponseCreators.*;

@RunWith(SpringRunner.class)
@RestClientTest(FlightSupplierService.class)
public class FlightSupplierServiceTest {

    @Qualifier("objectMapper")
    @Autowired
    private ObjectMapper mapper;

    @Autowired
    private CrazyAirService crazyAirService;

    @Autowired
    private ToughJetService toughJetService;

    @Autowired
    private MockRestServiceServer server;

    @Value("${busyflight.supplier.crazyair.rooturl}")
    private String ROOT_URL_CRAZYAIR;

    @Value("${busyflight.supplier.toughjet.rooturl}")
    private String ROOT_URL_TOUGHJET;

    @Test
    public void testCrazyAirAPI() throws Exception {
        CrazyAirResponse crazyAirResponse = CrazyAirResponse.builder()
            .airline("CZ001")
            .price(105.50d)
            .cabinClass("BUSINESS")
            .departureAirportCode("APA")
            .destinationAirportCode("APB")
            .departureDate(new Date())
            .arrivalDate(new Date())
        .build();

        server.expect(requestTo(ROOT_URL_CRAZYAIR + "/flight/search"))
            .andRespond(withSuccess(mapper.writeValueAsString(crazyAirResponse), MediaType.APPLICATION_JSON));

        SupplierResponse supplierResponse = crazyAirService.supplierSearch(new CrazyAirRequest()).findAny().orElse(null);

        Assert.assertNotNull(supplierResponse);
        Assert.assertEquals(crazyAirResponse.getAirline(), ((CrazyAirResponse)supplierResponse).getAirline());
    }

    @Test
    public void testToughJetApi() throws Exception {
        ToughJetResponse toughJetResponse = ToughJetResponse.builder()
            .carrier("TJ001")
            .basePrice(205.50d)
            .tax(10.50d)
            .discount(7.5f)
            .departureAirportName("APA")
            .arrivalAirportName("APB")
            .outboundDateTime(new Date())
            .inboundDateTime(new Date())
        .build();

        server.expect(requestTo(ROOT_URL_CRAZYAIR + "/flight/search"))
                .andRespond(withSuccess(mapper.writeValueAsString(toughJetResponse), MediaType.APPLICATION_JSON));

        SupplierResponse supplierResponse = toughJetService.supplierSearch(new ToughJetRequest()).findAny().orElse(null);

        Assert.assertNotNull(supplierResponse);
        Assert.assertEquals(toughJetResponse.getCarrier(), ((ToughJetResponse)supplierResponse).getCarrier());
    }
}