package ua.tqs.AirQuality;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.server.ResponseStatusException;

import ua.tqs.AirQuality.Model.AirData;
import ua.tqs.AirQuality.Repositories.AirQualityRepository;
import ua.tqs.AirQuality.Repositories.AlternativeRepository;
import ua.tqs.AirQuality.Services.DataService;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class DataServiceTest {

    private AirData dados1 = null;
    private AirData dados2 = null;

    @Mock
    private AirQualityRepository repository;

    @Mock
    private AlternativeRepository repository2;

    @InjectMocks
    private DataService service;

    @BeforeEach 
    public void setUp() throws JsonMappingException, JsonProcessingException {
        String json1 = "{\"data\":{\"city\":{\"name\":\"Entrecampos, Lisboa, Portugal\",\"geo\":[38.748611111111,-9.1488888888889]},\"iaqi\":{\"dew\":{\"v\":6.0},\"h\":{\"v\":64.0},\"no2\":{\"v\":11.2},\"o3\":{\"v\":40.3},\"p\":{\"v\":1014.5},\"pm10\":{\"v\":5.0},\"pm25\":{\"v\":18.0},\"so2\":{\"v\":0.3},\"t\":{\"v\":12.5},\"w\":{\"v\":2.8},\"wg\":{\"v\":11.3}}}}";
        String json2 = "{\"data\":{\"city\":{\"name\":\"Abuja US Embassy, Nigeria\",\"geo\":[9.0416479110718,7.4773740768433]},\"iaqi\":{\"dew\":{\"v\":23.0},\"h\":{\"v\":49.0},\"no2\":null,\"o3\":null,\"p\":{\"v\":1010.0},\"pm10\":null,\"pm25\":{\"v\":116.0},\"so2\":null,\"t\":{\"v\":35.0},\"w\":{\"v\":2.5},\"wg\":null}}}";
        
        ObjectMapper mapper = new ObjectMapper();
        this.dados1 = mapper.readValue(json1, AirData.class);
        this.dados2 = mapper.readValue(json2, AirData.class);
    }

    @AfterEach
    public void tearDown() {
        reset(repository);
        reset(repository2);

    }

    @Test
    void testGetDataByValidCityService() {
        when(repository.getDataByCity("lisbon")).thenReturn(this.dados1);

        assertThat(service.getAirDataByCity("lisbon")).isInstanceOf(AirData.class);
        assertEquals("Entrecampos, Lisboa, Portugal", service.getAirDataByCity("lisbon").getData().getCity().getName());
    }

    @Test
    void testGetDataByValidCityServiceWithFirstAPIDown() {
        when(repository.getDataByCity("lisbon")).thenReturn(null);
        when(repository2.getDataByCity("lisbon")).thenReturn(this.dados1);

        assertThat(service.getAirDataByCity("lisbon")).isInstanceOf(AirData.class);
        assertEquals("Entrecampos, Lisboa, Portugal", service.getAirDataByCity("lisbon").getData().getCity().getName());
    }


    @Test
    void testGetDataByInexistentCityService() {
        when(repository.getDataByCity("inexistent city")).thenReturn(null);
        when(repository2.getDataByCity("inexistent city")).thenReturn(null);

        assertThrows(ResponseStatusException.class, () -> {service.getAirDataByCity("inexistent city"); });
    }


    @Test
    void testGetDataByLatLocService()  {

        when(repository.getDataByLatLon(11.0, 10.0)).thenReturn(this.dados2);

        assertThat(service.getAirDataByLatLon(11.0, 10.0)).isInstanceOf(AirData.class);
        assertEquals("Abuja US Embassy, Nigeria", service.getAirDataByLatLon(11.0, 10.0).getData().getCity().getName());
    }

    @Test
    void testGetDataByLatLocServiceWithFirstAPIDown()  {
        when(repository.getDataByLatLon(11.0, 10.0)).thenReturn(null);
        when(repository2.getDataByLatLon(11.0, 10.0)).thenReturn(this.dados2);

        assertThat(service.getAirDataByLatLon(11.0, 10.0)).isInstanceOf(AirData.class);
        assertEquals("Abuja US Embassy, Nigeria", service.getAirDataByLatLon(11.0, 10.0).getData().getCity().getName());
    }
}