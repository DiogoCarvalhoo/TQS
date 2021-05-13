package ua.tqs.AirQuality.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Data implements Serializable{

  @JsonProperty("city")
  private City city;

  @JsonProperty("iaqi")
  private Metrics metrics;

  public City getCity() {
    return city;
  }

  public void setCity(City city) {
    this.city = city;
  }

  public Metrics getMetrics() {
    return metrics;
  }

  public void setMetrics(Metrics metrics) {
    this.metrics = metrics;
  }
}