package ua.tqs.P2_CarAPI;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.internal.verification.VerificationModeFactory;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
public class CarServiceIT {

    // lenient is required because we load some expectations in the setup
    // that are not used in all the tests. As an alternative, the expectations
    // could move into each test method and be trimmed: no need for lenient
    @Mock( lenient = true)
    private CarRepository carRepository;

    @InjectMocks
    private CarManagerService carManagerService;

    @BeforeEach
    public void setUp() {
        Car bmw = new Car("bmw", "M4");
        bmw.setCarid(111L);

        Car opel = new Car("opel", "Corsa");
        Car mercedes = new Car("mercedes", "amg");

        List<Car> allCars = Arrays.asList(bmw, opel, mercedes);

        Mockito.when(carRepository.findByCarid(Long.valueOf("1"))).thenReturn(bmw);
        Mockito.when(carRepository.findByMaker(bmw.getMaker())).thenReturn(bmw);
        Mockito.when(carRepository.findByMaker(opel.getMaker())).thenReturn(opel);
        Mockito.when(carRepository.findByMaker("wrong_name")).thenReturn(null);
        Mockito.when(carRepository.findAll()).thenReturn(allCars);
    }

    @Test
    public void whenValidId_thenCarShouldBeFound() {
        String maker = "bmw";
        Car found = carManagerService.getCarDetails(Long.valueOf("1"));

        assertThat(found.getMaker()).isEqualTo(maker);
    }

    @Test
    public void whenInValidId_thenCarShouldNotBeFound() {
        Car fromDb = carManagerService.getCarDetails(Long.valueOf("-1"));
        assertThat(fromDb).isNull();

        verifyFindByIdIsCalledOnce();
    }


    private void verifyFindByIdIsCalledOnce() {
        Mockito.verify(carRepository, VerificationModeFactory.times(1)).findByCarid(Mockito.anyLong());
    }

}

