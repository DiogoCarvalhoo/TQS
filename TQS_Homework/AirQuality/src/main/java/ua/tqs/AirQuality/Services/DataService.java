package ua.tqs.AirQuality.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import ua.tqs.AirQuality.Model.AirData;
import ua.tqs.AirQuality.Model.Cache  ;
import ua.tqs.AirQuality.Repositories.AirQualityRepository;
import ua.tqs.AirQuality.Repositories.AlternativeRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class DataService {

    @Autowired
    private AirQualityRepository repository;

    @Autowired
    private AlternativeRepository repositoryAlt;

    private final Cache cache = new Cache(600L);
    private static final Logger logger = LoggerFactory.getLogger(DataService.class);

    public AirData getAirDataByCity(String city) {
        Boolean cacheAvailability = cache.checkKey(city);
        logger.info("LOGGER: Checking if data is stored in cache! Result: {}", cacheAvailability);

        AirData dados = null;
        if (Boolean.TRUE.equals(cacheAvailability)) {
            logger.info("LOGGER: Requesting data to cache!");
            dados = cache.getData(city);
            
            logger.info("LOGGER: Asking statistics to cache!");
            logger.info("{}", cache);

            if (dados == null) {
                logger.info("LOGGER: Cache data expired!");
                dados = getDataFromApiByName(city);
            }

        } else {
            dados = getDataFromApiByName(city);
        }
    

        return dados;
    }

    //Este método de pesquisa nao tem cache associado. Cache só foi implementada na pesquisa por cidades visto que pedidos de latitude&longitude podem ser quaisquer numeros, nao precisa de ser coordenadas exatas.
    public AirData getAirDataByLatLon(Double lat, Double lon) {
        AirData dados = null;
        dados = getDataFromApiByLatLon(lat, lon);
        return dados;
    }

    //Implementado com 2 apis externas que alteram caso a primeira nao esteja disponivel.
    public AirData getDataFromApiByName(String city) {
        logger.info("LOGGER: Requesting data to external API!");
        AirData dados = this.repository.getDataByCity(city);
        if (dados == null) { 
            //Requesting to alternative repository
            dados = this.repositoryAlt.getDataByCity(city);

            if (dados == null) {
                throw new ResponseStatusException(HttpStatus.FORBIDDEN,
                    "Request not valid.  Please check api token and city name");
            }
        } else {
            logger.info("LOGGER: Saving data into cache!");
            saveDataInCache(city, dados);
        }

        return dados;
    }

    //Implementado com 2 apis externas que alteram caso a primeira nao esteja disponivel.
    public AirData getDataFromApiByLatLon(Double lat, Double lon) {
        logger.info("LOGGER: Requesting data to external API!");
        AirData dados = this.repository.getDataByLatLon(lat, lon);

        if (dados == null) {
            //Requesting to alternative repository
            dados = this.repositoryAlt.getDataByLatLon(lat, lon);
            if (dados == null) {
                throw new ResponseStatusException(HttpStatus.FORBIDDEN,
                    "Request not valid.  Please check api token and lat and lon values");
            }
        }

        return dados;

    }

    public void saveDataInCache(String city, AirData dados) {
        cache.saveData(city, dados);
    }

    public Cache getCache() {
        return cache;
    }

}