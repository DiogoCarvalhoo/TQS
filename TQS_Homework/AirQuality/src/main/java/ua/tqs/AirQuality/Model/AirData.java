package ua.tqs.AirQuality.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;


@JsonIgnoreProperties(ignoreUnknown = true)
public class AirData implements Serializable {
    @JsonProperty("data")
    private Data data;

    public Data getData() {
      return data;
    }
  
    public void setData(Data data) {
      this.data = data;
    }
}

