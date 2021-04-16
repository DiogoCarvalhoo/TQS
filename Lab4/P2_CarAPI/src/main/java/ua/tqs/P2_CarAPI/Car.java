package ua.tqs.P2_CarAPI;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "car")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long carid;

    @Column(name = "maker")
    private String maker;

    @Column(name = "model")
    private String model;

    public Car() {

    }

    public Car(String maker, String model) {
        this.maker = maker;
        this.model = model;
    }
 
    
    public long getCarid() {
        return this.carid;
    }
    public void setCarid(Long carid) {
        this.carid = carid;
    }

    public String getMaker() {
        return this.maker;
    }
    public void setMaker(String maker) {
        this.maker = maker;
    }
 
    
    public String getModel() {
        return this.model;
    }
    public void setModel(String model) {
        this.model = model;
    }



    @Override
    public String toString() {
        return "Car [carid=" + carid + ", Maker=" + maker + ", Model="  + model +"]";
    }

 
}