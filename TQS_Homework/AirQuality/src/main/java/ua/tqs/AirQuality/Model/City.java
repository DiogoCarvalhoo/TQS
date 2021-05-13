package ua.tqs.AirQuality.Model;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;


@JsonIgnoreProperties(ignoreUnknown = true)
public class City implements Serializable {
  
  @JsonProperty("name")
  private String name;

  @JsonProperty("geo")
  private List<Double> loc;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public List<Double> getLoc() {
    return loc;
  }

  public void setLoc(List<Double> loc) {
    this.loc = loc;
  }

}