package ua.tqs.Air_Quality.Model;

import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Value implements Serializable {

    @JsonProperty("v")
    private Double value;

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }
}
