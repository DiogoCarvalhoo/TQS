package ua.tqs.P2_CarAPI;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class CarRepositoryIT {
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private CarRepository carRepository;

    
    @Test
    public void whenFindByCarid_thenReturnCar(){
        Car bmw = new Car("bmw","M4");
        entityManager.persistAndFlush(bmw);

        Car found = carRepository.findByCarid(Long.valueOf("1"));
        assertThat(found.getMaker()).isEqualTo(bmw.getMaker());
    }

    @Test
    public void whenInvalidCarid_thenReturnNull(){
        Car notfound = carRepository.findByCarid(Long.valueOf("-1"));
        assertThat(notfound).isNull();
    }

}