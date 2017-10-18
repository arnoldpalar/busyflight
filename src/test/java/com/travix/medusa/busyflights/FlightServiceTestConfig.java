package com.travix.medusa.busyflights;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.travix.medusa.busyflights.service.supplier.CrazyAirService;
import com.travix.medusa.busyflights.service.supplier.ToughJetService;
import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("FlightServiceTest")
public class FlightServiceTestConfig {

    @Bean
    @Primary
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }

    @Bean
    @Primary
    public CrazyAirService crazyAirService() {
        return Mockito.mock(CrazyAirService.class);
    }

    @Bean
    @Primary
    public ToughJetService toughJetService() {
        return Mockito.mock(ToughJetService.class);
    }
}
