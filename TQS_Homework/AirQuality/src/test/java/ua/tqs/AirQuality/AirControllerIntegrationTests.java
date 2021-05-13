package ua.tqs.AirQuality;

import com.fasterxml.jackson.databind.ObjectMapper;
import ua.tqs.AirQuality.Model.AirData;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.http.MediaType;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = AirQualityApplication.class)
@AutoConfigureMockMvc
class AirControllerIntegrationTests {
    
    @Autowired
    private MockMvc mvcForTests;

    @Test
    void givenCityRequest_ReturnCorrectData() throws Exception {
        String json = "{\"data\":{\"city\":{\"name\":\"Entrecampos, Lisboa, Portugal\",\"geo\":[38.748611111111,-9.1488888888889]},\"iaqi\":{\"dew\":{\"v\":6.0},\"h\":{\"v\":64.0},\"no2\":{\"v\":11.2},\"o3\":{\"v\":40.3},\"p\":{\"v\":1014.5},\"pm10\":{\"v\":5.0},\"pm25\":{\"v\":18.0},\"so2\":{\"v\":0.3},\"t\":{\"v\":12.5},\"w\":{\"v\":2.8},\"wg\":{\"v\":11.3}}}}";
        ObjectMapper mapper = new ObjectMapper();
        AirData dados =  mapper.readValue(json, AirData.class);

        mvcForTests.perform(get("/api/air?city=lisbon").contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.data.city.name").value(dados.getData().getCity().getName()));
        

    }


    @Test
    void givenInvalidCityRequest_ReturnCorrectResponse() throws Exception {

        mvcForTests.perform(get("/api/air?city=inexistent").contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isForbidden());        

    }


    @Test
    void givenLatLonRequest_ReturnCorrectData() throws Exception {
        //String json = "{\"data\":{\"city\":{\"name\":\"Abuja US Embassy, Nigeria\",\"geo\":[9.0416479110718,7.4773740768433]},\"iaqi\":{\"dew\":{\"v\":23.0},\"h\":{\"v\":49.0},\"no2\":null,\"o3\":null,\"p\":{\"v\":1010.0},\"pm10\":null,\"pm25\":{\"v\":116.0},\"so2\":null,\"t\":{\"v\":35.0},\"w\":{\"v\":2.5},\"wg\":null}}}";
        String json = "{\"data\":{\"city\":{\"name\":\"N'Djamena US Embassy, Chad\",\"geo\":[12.1348,15.0557]},\"iaqi\":{\"dew\":{\"v\":17.0},\"h\":{\"v\":26.0},\"no2\":null,\"o3\":null,\"p\":{\"v\":1007.0},\"pm10\":null,\"pm25\":{\"v\":76.0},\"so2\":null,\"t\":{\"v\":40.0},\"w\":{\"v\":2.5},\"wg\":{\"v\":12.3}}}}";
        ObjectMapper mapper = new ObjectMapper();
        AirData dados =  mapper.readValue(json, AirData.class);

        mvcForTests.perform(get("/api/air?lat=11&lon=10").contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.data.city.name").value(dados.getData().getCity().getName()));
    }

    @Test
    void givenStatisticRequest_ReturnCorrectStatistics() throws Exception {

        mvcForTests.perform(get("/api/statistics").contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("numberOfRequests").isNumber())
            .andExpect(jsonPath("numberOfHits").isNumber())
            .andExpect(jsonPath("numberOfMisses").isNumber());
        

    }

}