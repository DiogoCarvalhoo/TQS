package ua.tqs.AirQuality;


import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import ua.tqs.AirQuality.Model.AirData;
import ua.tqs.AirQuality.Repositories.AlternativeRepository;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class AirQualityAlternativeRepositoryTest {

    @InjectMocks
    private AlternativeRepository repository;

    @BeforeEach 
    public void setUp() {

    }

    @Test
    void testGetDataByCityvRepositoryMethod() {
        assertThat(repository.getDataByCity("lisbon")).isInstanceOf(AirData.class);
        assertEquals("Lisbon", repository.getDataByCity("lisbon").getData().getCity().getName());

    }


    @Test
    void testGetDataByCityvRepositoryMethodWithInvalidCity() {
        assertEquals(null, repository.getDataByCity("inexistent city"), "Inexistent City Data Search do not return null" );
    }

    @Test
    void testGetDataByLatLonRepositoryMethod() {
        assertThat(repository.getDataByLatLon(11.0, 10.0)).isInstanceOf(AirData.class);
    }
}
