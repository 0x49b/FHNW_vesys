package ch.fhnw.ds.rest.transport.types2;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Prognosis {

    @SerializedName("platform")
    @Expose
    private Object platform;
    @SerializedName("arrival")
    @Expose
    private Object arrival;
    @SerializedName("departure")
    @Expose
    private String departure;
    @SerializedName("capacity1st")
    @Expose
    private Object capacity1st;
    @SerializedName("capacity2nd")
    @Expose
    private Object capacity2nd;

    public Object getPlatform() {
        return platform;
    }

    public void setPlatform(Object platform) {
        this.platform = platform;
    }

    public Object getArrival() {
        return arrival;
    }

    public void setArrival(Object arrival) {
        this.arrival = arrival;
    }

    public String getDeparture() {
        return departure;
    }

    public void setDeparture(String departure) {
        this.departure = departure;
    }

    public Object getCapacity1st() {
        return capacity1st;
    }

    public void setCapacity1st(Object capacity1st) {
        this.capacity1st = capacity1st;
    }

    public Object getCapacity2nd() {
        return capacity2nd;
    }

    public void setCapacity2nd(Object capacity2nd) {
        this.capacity2nd = capacity2nd;
    }

}
