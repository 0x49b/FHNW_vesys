package ch.fhnw.ds.rest.transport.types2;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Journey {

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("category")
    @Expose
    private String category;
    @SerializedName("subcategory")
    @Expose
    private Object subcategory;
    @SerializedName("categoryCode")
    @Expose
    private Object categoryCode;
    @SerializedName("number")
    @Expose
    private String number;
    @SerializedName("operator")
    @Expose
    private String operator;
    @SerializedName("to")
    @Expose
    private String to;
    @SerializedName("passList")
    @Expose
    private List<PassList> passList = null;
    @SerializedName("capacity1st")
    @Expose
    private Object capacity1st;
    @SerializedName("capacity2nd")
    @Expose
    private Object capacity2nd;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Object getSubcategory() {
        return subcategory;
    }

    public void setSubcategory(Object subcategory) {
        this.subcategory = subcategory;
    }

    public Object getCategoryCode() {
        return categoryCode;
    }

    public void setCategoryCode(Object categoryCode) {
        this.categoryCode = categoryCode;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public List<PassList> getPassList() {
        return passList;
    }

    public void setPassList(List<PassList> passList) {
        this.passList = passList;
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
