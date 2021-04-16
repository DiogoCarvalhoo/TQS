package ua.tqs.P2_CarAPI;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.TestPropertySource;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponentsBuilder;
import java.util.*;
import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestPropertySource( locations = "application-integrationtest.properties")
class CarControllerTemplateIT {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private CarRepository carRepository;

    @AfterEach
    public void resetDB(){
        carRepository.deleteAll();
    }
    
    @Test
    public void whenValidInput_thenCreateCar() throws IOException, Exception{
        Car bmw = new Car("bmw","M4");
        ResponseEntity<Car> entity = restTemplate.postForEntity("/api/cars", bmw, Car.class);
        
        List<Car> found = carRepository.findAll();
        assertThat(found).extracting(Car::getMaker).containsOnly("bmw");
    }

    @Test
    public void givenCar_whenGetCar_thenStatusOK() throws Exception{
        Car bmw = new Car("bmw","M4");
        carRepository.saveAndFlush(bmw);
        Car opel = new Car("opel","Corsa");
        carRepository.saveAndFlush(opel);

        ResponseEntity<List<Car>> response = restTemplate
                .exchange("/api/cars", HttpMethod.GET, null, new ParameterizedTypeReference<List<Car>>() {});

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).extracting(Car::getMaker).containsExactly("bmw", "opel");
    }
    
}