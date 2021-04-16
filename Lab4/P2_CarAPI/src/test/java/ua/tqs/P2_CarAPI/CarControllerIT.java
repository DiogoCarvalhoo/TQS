package ua.tqs.P2_CarAPI;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.internal.verification.VerificationModeFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CarController.class)
public class CarControllerIT {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private CarManagerService service;

    @BeforeEach
    public void setUp() throws Exception {
    }

    @Test
    public void whenPostCar_thenCreateCar( ) throws Exception {
        Car bmw = new Car("bmw", "M4");

        //given(service.save(Mockito.any())).willReturn(alex);
        when( service.save(Mockito.any()) ).thenReturn(bmw);

        mvc.perform(post("/api/cars").contentType(MediaType.APPLICATION_JSON).content(JsonUtil.toJson(bmw)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.maker", is("bmw")));

        verify(service, times(1)).save(Mockito.any());

    }

    @Test
    public void givenCars_whenGetCars_thenReturnJsonArray() throws Exception {
        Car bmw = new Car("bmw", "M4");
        Car opel = new Car("opel", "Corsa");
        Car mercedes = new Car("mercedes", "amg");

        List<Car> allCars = Arrays.asList(bmw, opel, mercedes);

        given(service.getAllCars()).willReturn(allCars);

        mvc.perform(get("/api/cars").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andExpect(jsonPath("$", hasSize(3))).andExpect(jsonPath("$[0].maker", is(bmw.getMaker()))).andExpect(jsonPath("$[1].maker", is(opel.getMaker())))
                .andExpect(jsonPath("$[2].maker", is(mercedes.getMaker())));
        verify(service, VerificationModeFactory.times(1)).getAllCars();

    }

}
