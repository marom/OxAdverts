package com.maro.oxadverts;

import java.io.Serializable;

/**
 * Created by maro on 21/03/2015.
 * Class to keep an info about single advert.
 */
public class Advert implements Serializable{


    private String shortDescription;
    private String date;
    private String price;
    private AdvertDetails advertDetails;
    private String imageUrl;


    public Advert(String shortDescription, String date, String price, AdvertDetails advertDetails, String imageUrl) {
        this.shortDescription = shortDescription;
        this.date = date;
        this.price = price;
        this.advertDetails = advertDetails;
        this.imageUrl = imageUrl;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public AdvertDetails getAdvertDetails() {
        return advertDetails;
    }

    public void setAdvertDetails(AdvertDetails advertDetails) {
        this.advertDetails = advertDetails;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Override
    public String toString() {
        return "Advert{" +
                "shortDescription='" + shortDescription + '\'' +
                ", date='" + date + '\'' +
                ", price='" + price + '\'' +
                ", advertDetails=" + advertDetails +
                ", imageUrl='" + imageUrl + '\'' +
                '}';
    }
}
