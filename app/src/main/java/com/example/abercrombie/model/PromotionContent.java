package com.example.abercrombie.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PromotionContent {

    // POJO class for inner array "content"

    @SerializedName("target")
    @Expose
    private   String target;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("elementtype")
    @Expose
    private String elementtype;

    public PromotionContent(String target, String title, String elementtype) {
        this.target = target;
        this.title = title;
        this.elementtype = elementtype;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getElementtype() {
        return elementtype;
    }

    public void setElementtype(String elementtype) {
        this.elementtype = elementtype;
    }
}
