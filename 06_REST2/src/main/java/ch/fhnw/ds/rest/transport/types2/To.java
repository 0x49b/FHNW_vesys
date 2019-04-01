package ch.fhnw.ds.rest.transport.types2;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class To {

    @SerializedName("station")
    @Expose
    private Station station;
    @SerializedName("arrival")
    @Expose
    private String arrival;
    @SerializedName("arrivalTimestamp")
    @Expose
    private Integer arrivalTimestamp;
    @SerializedName("departure")
    @Expose
    private Object departure;
    @SerializedName("departureTimestamp")
    @Expose
    private Object departureTimestamp;
    @SerializedName("delay")
    @Expose
    private Object delay;
    @SerializedName("platform")
    @Expose
    private String platform;
    @SerializedName("prognosis")
    @Expose
    private Prognosis prognosis;
    @SerializedName("realtimeAvailability")
    @Expose
    private Object realtimeAvailability;
    @SerializedName("location")
    @Expose
    private Location location;

    public Station getStation() {
        return station;
    }

    public void setStation(Station station) {
        this.station = station;
    }

    public String getArrival() {
        return arrival;
    }

    public void setArrival(String arrival) {
        this.arrival = arrival;
    }

    public Integer getArrivalTimestamp() {
        return arrivalTimestamp;
    }

    public void setArrivalTimestamp(Integer arrivalTimestamp) {
        this.arrivalTimestamp = arrivalTimestamp;
    }

    public Object getDeparture() {
        return departure;
    }

    public void setDeparture(Object departure) {
        this.departure = departure;
    }

    public Object getDepartureTimestamp() {
        return departureTimestamp;
    }

    public void setDepartureTimestamp(Object departureTimestamp) {
        this.departureTimestamp = departureTimestamp;
    }

    public Object getDelay() {
        return delay;
    }

    public void setDelay(Object delay) {
        this.delay = delay;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public Prognosis getPrognosis() {
        return prognosis;
    }

    public void setPrognosis(Prognosis prognosis) {
        this.prognosis = prognosis;
    }

    public Object getRealtimeAvailability() {
        return realtimeAvailability;
    }

    public void setRealtimeAvailability(Object realtimeAvailability) {
        this.realtimeAvailability = realtimeAvailability;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

}
