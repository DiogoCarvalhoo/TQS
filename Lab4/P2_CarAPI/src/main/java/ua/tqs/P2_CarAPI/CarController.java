package ua.tqs.P2_CarAPI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/api")

public class CarController {

    @Autowired
    private CarManagerService carManagerService;

    @PostMapping("/cars")
    public ResponseEntity<Car> createCar(@RequestBody Car car){
        Car saved = carManagerService.save(car);

        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    @GetMapping("/cars")
    public List<Car> getAllCars(){
        return carManagerService.getAllCars();
    }

    @GetMapping("/car")
    public ResponseEntity<Car> getCarById(@RequestParam("carid") Long carid){   
        Car car = carManagerService.getCarDetails(carid);

        return new ResponseEntity<>(car, HttpStatus.OK);
    }

}