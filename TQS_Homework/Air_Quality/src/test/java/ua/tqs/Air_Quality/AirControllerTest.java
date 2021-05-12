package ua.tqs.Air_Quality;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.internal.verification.VerificationModeFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import ua.tqs.Air_Quality.Controller.AirController;
import ua.tqs.Air_Quality.Model.AirData;
import ua.tqs.Air_Quality.Model.Cache;
import ua.tqs.Air_Quality.Services.DataService;
import static org.mockito.Mockito.*;


@WebMvcTest(AirController.class)
public class AirControllerTest {
    
    @Autowired
    private MockMvc mvcForTests;

    @MockBean
    private DataService service;

    @Test
    public void givenCityRequest_ReturnCorrectData() throws Exception {
        String json = "{\"data\":{\"city\":{\"name\":\"Entrecampos, Lisboa, Portugal\",\"geo\":[38.748611111111,-9.1488888888889]},\"iaqi\":{\"dew\":{\"v\":6.0},\"h\":{\"v\":64.0},\"no2\":{\"v\":11.2},\"o3\":{\"v\":40.3},\"p\":{\"v\":1014.5},\"pm10\":{\"v\":5.0},\"pm25\":{\"v\":18.0},\"so2\":{\"v\":0.3},\"t\":{\"v\":12.5},\"w\":{\"v\":2.8},\"wg\":{\"v\":11.3}}}}";
        ObjectMapper mapper = new ObjectMapper();
        AirData dados =  mapper.readValue(json, AirData.class);

        given(service.getAirDataByCity("lisbon")).willReturn(dados);

        mvcForTests.perform(get("/api/air?city=lisbon").contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.data.city.name").value(dados.getData().getCity().getName()));
            
        
        verify(service, VerificationModeFactory.times(1)).getAirDataByCity(Mockito.anyString());
    }

    @Test
    public void givenLatLonRequest_ReturnCorrectData() throws Exception {
        String json = "{\"data\":{\"city\":{\"name\":\"Abuja US Embassy, Nigeria\",\"geo\":[9.0416479110718,7.4773740768433]},\"iaqi\":{\"dew\":{\"v\":23.0},\"h\":{\"v\":49.0},\"no2\":null,\"o3\":null,\"p\":{\"v\":1010.0},\"pm10\":null,\"pm25\":{\"v\":116.0},\"so2\":null,\"t\":{\"v\":35.0},\"w\":{\"v\":2.5},\"wg\":null}}}";
        ObjectMapper mapper = new ObjectMapper();
        AirData dados =  mapper.readValue(json, AirData.class);

        given(service.getAirDataByLatLon(11.0, 10.0)).willReturn(dados);

        mvcForTests.perform(get("/api/air?lat=11&lon=10").contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.data.city.name").value(dados.getData().getCity().getName()));
        
        verify(service, VerificationModeFactory.times(1)).getAirDataByLatLon(Mockito.anyDouble(), Mockito.anyDouble());
    }

    @Test
    public void givenStatisticRequest_ReturnCorrectStatistics() throws Exception {
        Cache cache = new Cache(600);

        given(service.getCache()).willReturn(cache);

        mvcForTests.perform(get("/api/statistics").contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("numberOfRequests").isNumber())
            .andExpect(jsonPath("numberOfHits").isNumber())
            .andExpect(jsonPath("numberOfMisses").isNumber());
        
        verify(service, VerificationModeFactory.times(1)).getCache();
    }
}
