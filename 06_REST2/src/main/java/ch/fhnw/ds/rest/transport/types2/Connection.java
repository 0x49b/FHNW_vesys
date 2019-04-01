package ch.fhnw.ds.rest.transport.types2;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Connection {

    @SerializedName("from")
    @Expose
    private From from;
    @SerializedName("to")
    @Expose
    private To to;
    @SerializedName("duration")
    @Expose
    private String duration;
    @SerializedName("transfers")
    @Expose
    private Integer transfers;
    @SerializedName("service")
    @Expose
    private Object service;
    @SerializedName("products")
    @Expose
    private List<String> products = null;
    @SerializedName("capacity1st")
    @Expose
    private Object capacity1st;
    @SerializedName("capacity2nd")
    @Expose
    private Object capacity2nd;
    @SerializedName("sections")
    @Expose
    private List<Section> sections = null;

    public From getFrom() {
        return from;
    }

    public void setFrom(From from) {
        this.from = from;
    }

    public To getTo() {
        return to;
    }

    public void setTo(To to) {
        this.to = to;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public Integer getTransfers() {
        return transfers;
    }

    public void setTransfers(Integer transfers) {
        this.transfers = transfers;
    }

    public Object getService() {
        return service;
    }

    public void setService(Object service) {
        this.service = service;
    }

    public List<String> getProducts() {
        return products;
    }

    public void setProducts(List<String> products) {
        this.products = products;
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

    public List<Section> getSections() {
        return sections;
    }

    public void setSections(List<Section> sections) {
        this.sections = sections;
    }

}
