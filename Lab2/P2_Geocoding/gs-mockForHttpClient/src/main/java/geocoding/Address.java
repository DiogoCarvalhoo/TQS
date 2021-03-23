
package geocoding;

import java.util.Objects;

public class Address {

    private String road;
    private String city;
    private String state;
    private String zio;
    private String houseNumber;

    public Address(String road, String city, String state, String zio, String houseNumber) {
        this.road = road;
        this.city = city;
        this.state = state;
        this.zio = zio;
        this.houseNumber = houseNumber;
    }

    public String getRoad() {
        return road;
    }

    public void setRoad(String road) {
        this.road = road;
    }


    public String getCity() {
        return city;
    }

    public void setCirty(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZio() {
        return zio;
    }

    public void setZio(String zio) {
        this.zio = zio;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

    @Override
    public String toString() {
        return "Address{" + "road=" + road + ", city=" + city + ", state=" + state + ", zio=" + zio + ", houseNumber=" + houseNumber + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Address other = (Address) obj;
        if (!Objects.equals(this.road, other.road)) {
            return false;
        }
        if (!Objects.equals(this.city, other.city)) {
            return false;
        }
        if (!Objects.equals(this.state, other.state)) {
            return false;
        }
        if (!Objects.equals(this.zio, other.zio)) {
            return false;
        }
        if (!Objects.equals(this.houseNumber, other.houseNumber)) {
            return false;
        }
        return true;
    }

    
    
}
