package ua.tqs.AirQuality.Model;

import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Value implements Serializable {

    @JsonProperty("v")
    private Double valor;

    public Double getValue() {
        return valor;
    }

    public void setValue(Double value) {
        this.valor = value;
    }
}
