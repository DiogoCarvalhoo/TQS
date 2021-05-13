package ua.tqs.AirQuality.Repositories;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import ua.tqs.AirQuality.Model.AirData;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Repository
public class AirQualityRepository {
    private static final Logger logger = LoggerFactory.getLogger(AirQualityRepository.class);

    private static final String API_URL = "https://api.waqi.info/feed/";

    private static final String TOKEN = "0663dcea86a7d6a6b83fedf9f3e5e7ea09b488d8";

    private final RestTemplate restTemplate = new RestTemplateBuilder().build();

    public AirData getDataByCity(String city) {
        AirData dados = null;

        try {
            String url = API_URL + city + "/?token=" + TOKEN;
            logger.info("LOGGER: Sending request to url: {}", url);
            dados = this.restTemplate.getForObject(url, AirData.class);
        }
        catch (Exception e) {
            logger.info("LOGGER: {}", e.toString());

        }

        return dados;
    }

    public AirData getDataByLatLon(Double lat, Double lon) {
        AirData dados = null;

        try {
            String url = API_URL + "geo:" + lat +";" + lon  + "/?token=" + TOKEN;
            logger.info("LOGGER: Sending request to url: {}", url);
            dados = this.restTemplate.getForObject(url, AirData.class);
        }
        catch (Exception e) {
            logger.info("LOGGER: {}", e.toString());
        }

        return dados;
    }
}