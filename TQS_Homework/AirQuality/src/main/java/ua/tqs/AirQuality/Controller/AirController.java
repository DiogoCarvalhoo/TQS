
package ua.tqs.AirQuality.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import ua.tqs.AirQuality.Services.DataService;
import ua.tqs.AirQuality.Model.AirData;
import ua.tqs.AirQuality.Model.*;

import java.util.logging.Level;
import java.util.logging.Logger;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class AirController {

    @Autowired
    private DataService airService;

    private static final Logger logger = Logger.getLogger(AirController.class.getName());

    @GetMapping("/air")
    public AirData getAirData(@RequestParam(value = "city", required = false) String city,
                              @RequestParam(value = "lat", required = false) Double lat,
                              @RequestParam(value = "lon", required = false) Double lon) {
        
        logger.log(Level.INFO, "LOGGER: API AirData Request");

        if (city != null) {
            return airService.getAirDataByCity(city);
        }
        
        if (lat != null && lon != null) {
            return airService.getAirDataByLatLon(lat, lon);
        }

        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "USAGE: '/api/air?city=cityname' or '/api/air?lat=value&lon=value'" );
    }

    @GetMapping("/statistics")
    public Cache getCacheStatistics() {
        logger.log(Level.INFO, "LOGGER: API Statistics Request");
        return airService.getCache();
    }
}