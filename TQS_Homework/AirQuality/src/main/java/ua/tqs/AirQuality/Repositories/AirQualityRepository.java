package ua.tqs.AirQuality.Repositories;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import ua.tqs.AirQuality.Model.AirData;

import java.util.logging.Level;
import java.util.logging.Logger;


@Repository
public class AirQualityRepository {
    private static final Logger logger = Logger.getLogger(AirQualityRepository.class.getName());

    private static final String API_URL = "https://api.waqi.info/feed/";

    private static final String TOKEN = "0663dcea86a7d6a6b83fedf9f3e5e7ea09b488d8";

    private final RestTemplate restTemplate = new RestTemplateBuilder().build();

    public AirData getDataByCity(String city) {
        AirData dados = null;
        String loggerOutput;

        try {
            String url = API_URL + city + "/?token=" + TOKEN;
            loggerOutput = "LOGGER: Sending request to url: " + url;
            logger.log(Level.INFO, loggerOutput);
            dados = this.restTemplate.getForObject(url, AirData.class);
        }
        catch (Exception e) {
            loggerOutput = "LOGGER:" + e.toString();
            logger.log(Level.WARNING, loggerOutput);
        }

        return dados;
    }

    public AirData getDataByLatLon(Double lat, Double lon) {
        AirData dados = null;
        String loggerOutput;

        try {
            String url = API_URL + "geo:" + lat +";" + lon  + "/?token=" + TOKEN;
            loggerOutput = "LOGGER: Sending request to url: " + url;
            logger.log(Level.INFO, loggerOutput);
            dados = this.restTemplate.getForObject(url, AirData.class);
        }
        catch (Exception e) {
            loggerOutput = "LOGGER:" + e.toString();
            logger.log(Level.WARNING, loggerOutput);
        }

        return dados;
    }
}