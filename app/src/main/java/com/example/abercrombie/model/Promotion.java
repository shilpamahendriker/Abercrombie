package com.example.abercrombie.model;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Promotion {

    // POJO class for outer array
    @SerializedName("title")
    @Expose
    private String title;

    @SerializedName("bgImage")
    @Expose
    private String backGroundImage;


    private ArrayList<PromotionContent> contentList;

    @SerializedName("message")
    @Expose
    private String promoMessage;

    @SerializedName("topDescription")
    @Expose
    private String topDescription;

    @SerializedName("bottomDescription")
    @Expose
    private   String bottomDescription;

    public Promotion(String title, String backGroundImage, ArrayList<PromotionContent> contentList, String promoMessage, String topDescription, String bottomDescription) {
        this.title = title;
        this.backGroundImage = backGroundImage;
        this.contentList = contentList;
        this.promoMessage = promoMessage;
        this.topDescription = topDescription;
        this.bottomDescription = bottomDescription;
    }

    public String getTitle() {
        return title;
    }

    public String getBackGroundImage() {
        return backGroundImage;
    }

    public ArrayList<PromotionContent> getContentList() {
        return contentList;
    }

    public String getPromoMessage() {
        return promoMessage;
    }

    public String getTopDescription() {
        return topDescription;
    }

    public String getBottomDescription() {
        return bottomDescription;
    }
}
