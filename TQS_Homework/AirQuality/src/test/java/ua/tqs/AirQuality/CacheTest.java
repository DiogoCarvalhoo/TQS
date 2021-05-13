package ua.tqs.AirQuality;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ua.tqs.AirQuality.Model.*;

import static org.junit.jupiter.api.Assertions.*;
import java.util.concurrent.TimeUnit;


class CacheTest {
    private Cache cache;
    private AirData data;
    
    @BeforeEach
    public void setUp() throws JsonMappingException, JsonProcessingException {
        String json = "{\"data\":{\"city\":{\"name\":\"Entrecampos, Lisboa, Portugal\",\"geo\":[38.748611111111,-9.1488888888889]},\"iaqi\":{\"dew\":{\"v\":6.0},\"h\":{\"v\":64.0},\"no2\":{\"v\":11.2},\"o3\":{\"v\":40.3},\"p\":{\"v\":1014.5},\"pm10\":{\"v\":5.0},\"pm25\":{\"v\":18.0},\"so2\":{\"v\":0.3},\"t\":{\"v\":12.5},\"w\":{\"v\":2.8},\"wg\":{\"v\":11.3}}}}";
        ObjectMapper mapper = new ObjectMapper();
        data =  mapper.readValue(json, AirData.class);

        this.cache = new Cache(5);
        this.cache.saveData("key", data);
    }

    @AfterEach
    public void tearDown() {
    }

    @Test
    void testInitialStatisticsValues() throws JsonMappingException, JsonProcessingException {
        assertEquals(0, cache.getNumberOfRequests(), "Number of initial Request is not 0");
        assertEquals(0, cache.getNumberOfHits(),  "Number of initial Hits is not 0");
        assertEquals(0, cache.getNumberOfMisses(), "Number of initial Misses is not 0");
    }


    @Test
    void testgetDataMethod() throws JsonMappingException, JsonProcessingException {
        assertEquals("Entrecampos, Lisboa, Portugal", this.cache.getData("key").getData().getCity().getName(),"saveData: wrong data was returned!");
    }


    @Test
    void testSaveDataMethod() throws JsonMappingException, JsonProcessingException {
        String json = "{\"data\":{\"city\":{\"name\":\"Sobreiras-Lordelo do Ouro, Porto, Portugal\",\"geo\":[41.1475,-8.6588888888889]},\"iaqi\":{\"dew\":null,\"h\":{\"v\":82.7},\"no2\":null,\"o3\":{\"v\":37.0},\"p\":{\"v\":1020.6},\"pm10\":null,\"pm25\":{\"v\":17.0},\"so2\":null,\"t\":{\"v\":14.4},\"w\":{\"v\":5.0},\"wg\":{\"v\":13.7}}}}";
        ObjectMapper mapper = new ObjectMapper();
        AirData newdata =  mapper.readValue(json, AirData.class);
        this.cache.saveData("additionalData", newdata);

        assertEquals("Sobreiras-Lordelo do Ouro, Porto, Portugal", this.cache.getData("additionalData").getData().getCity().getName(),"saveData: wrong data was returned!");
    }

    @Test
    void testCheckKeyMethod() {
        assertFalse(this.cache.checkKey("inexistent"), "CheckKey: Inexistent Key was found!");
        assertTrue(this.cache.checkKey("key"), "CheckKey: Existent Key was not found!");
    }

    @Test
    void testKeyDoNotExistRequest() {
        int numberOfMisses = cache.getNumberOfMisses();
        int numberOfRequests = cache.getNumberOfRequests();
        this.cache.getData("inexistent");
        assertEquals(numberOfMisses+1, cache.getNumberOfMisses(), "Number of Misses do not increment on key do not exist request");
        assertEquals(numberOfRequests+1, cache.getNumberOfRequests(), "Number of Request do not increment on key do not exist request");
    }

    @Test
    void testKeyExistRequest() {
        int numberOfHits = cache.getNumberOfHits();
        int numberOfRequests = cache.getNumberOfRequests();
        this.cache.getData("key");
        assertEquals(numberOfHits+1, cache.getNumberOfHits(), "Number of Hits do not increment on key exist request");
        assertEquals(numberOfRequests+1, cache.getNumberOfRequests(),  "Number of Request do not increment on key exist request");
    }

    @Test
    void testKeyExpiredRequest() throws InterruptedException {
        int numberOfMisses = cache.getNumberOfMisses();
        int numberOfRequests = cache.getNumberOfRequests();
        TimeUnit.SECONDS.sleep(6);
        this.cache.getData("key");
        assertEquals(numberOfMisses+1, cache.getNumberOfMisses(), "Number of Misses do not increment on key expired request");
        assertEquals(numberOfRequests+1, cache.getNumberOfRequests(), "Number of Request do not increment on key expired request");
    }
}
