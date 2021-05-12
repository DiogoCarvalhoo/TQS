package ua.tqs.Air_Quality;

import static org.junit.jupiter.api.Assertions.assertEquals;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import ua.tqs.Air_Quality.Model.AirData;
import ua.tqs.Air_Quality.Repositories.AirQualityRepository;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
public class AirQualityRepositoryTest {
    
    @InjectMocks
    private AirQualityRepository repository;

    @BeforeEach 
    public void setUp()  {

    }


    @Test
    public void testGetDataByCityvRepositoryMethod() {
        assertThat(repository.getDataByCity("lisbon")).isInstanceOf(AirData.class);
        assertEquals("Entrecampos, Lisboa, Portugal", repository.getDataByCity("lisbon").getData().getCity().getName());

    }


    @Test
    public void testGetDataByCityvRepositoryMethodWithInvalidCity() {
        assertEquals(null, repository.getDataByCity("inexistent city"), "Inexistent City Data Search do not return null" );
    }

    @Test
    public void testGetDataByLatLonRepositoryMethod() {
        assertThat(repository.getDataByLatLon(11.0, 10.0)).isInstanceOf(AirData.class);
    }

    
}