package ua.tqs.Air_Quality.Model;

import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonProperty;


public class Metrics implements Serializable {
    @JsonProperty("dew")
    private Value dew;
    @JsonProperty("h")
    private Value h;
    @JsonProperty("no2")
    private Value no2;
    @JsonProperty("o3")
    private Value o3;
    @JsonProperty("p")
    private Value p;
    @JsonProperty("pm10")
    private Value pm10;
    @JsonProperty("pm25")
    private Value pm25;
    @JsonProperty("so2")
    private Value so2;
    @JsonProperty("t")
    private Value t;
    @JsonProperty("w")
    private Value w;
    @JsonProperty("wg")
    private Value wg;

    public Value getDew() {
        return this.dew;
    }
    public Value getH() {
        return this.h;
    }
    public Value getNo2() {
        return this.no2;
    }
    public Value getO3() {
        return this.o3;
    }
    public Value getP() {
        return this.p;
    }
    public Value getPm10() {
        return this.pm10;
    }
    public Value getPm25() {
        return this.pm25;
    }
    public Value getSo2() {
        return this.so2;
    }
    public Value getT() {
        return this.t;
    }
    public Value getW() {
        return this.w;
    }
    public Value getWg() {
        return this.wg;
    }

    public void setDew(Value val) {
        this.dew = val;
    }
    public void setH(Value val) {
        this.h = val;
    }
    public void setNo2(Value val) {
        this.no2 = val;
    }
    public void setO3(Value val) {
        this.o3 = val;
    }
    public void setP(Value val) {
        this.p = val;
    }
    public void setPm10(Value val) {
        this.pm10 = val;
    }
    public void setPm25(Value val) {
        this.pm25 = val;
    }
    public void setSo2(Value val) {
        this.so2 = val;
    }
    public void setT(Value val) {
        this.t = val;
    }
    public void setW(Value val) {
        this.w = val;
    }
    public void setWg(Value val) {
        this.wg = val;
    }

}

