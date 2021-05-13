package ua.tqs.AirQuality.Model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Cache {
    private long timeToLive;
    private int numberOfRequests;
    private int numberOfHits;
    private int numberOfMisses;

    @JsonIgnore
    private Map<String, List<Object>> savedData = new HashMap<>();



    public Cache(long timeToLive) {
        this.timeToLive = timeToLive;
    }

    public void saveData(String key, AirData data) {
        Long expireTime = System.currentTimeMillis() + this.timeToLive * 1000;
        List<Object> item = new ArrayList<>();
        item.add(data);
        item.add(expireTime);
        this.savedData.put(key, item);
    }

    public AirData getData(String key) {
        this.numberOfRequests++;
        AirData data = null;

        if (!checkKey(key)) {
            this.numberOfMisses++;
        } else {
            if (System.currentTimeMillis() > (Long) this.savedData.get(key).get(1) ) {
                this.savedData.remove(key);
                this.numberOfMisses++;
            } else {
                this.numberOfHits++;
                data = (AirData) this.savedData.get(key).get(0);
            }
        }
        return data;
    }

    public int getNumberOfRequests() {
        return numberOfRequests;
    }

    public int getNumberOfHits() {
        return numberOfHits;
    }

    public int getNumberOfMisses() {
        return numberOfMisses;
    }

    public Boolean checkKey(String key) {
        return this.savedData.containsKey(key);
    }

    public String toString() {
        return "Cache statistics: [NumberOfRequest: " + numberOfRequests + ", NumberOfHits" + numberOfHits + ", NumberOfMisses" + numberOfMisses+"]";
    }

}