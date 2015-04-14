package com.maro.oxadverts;

/**
 * Created by maro on 21/03/2015.
 * Class to keep an info about single advert.
 */
public class Advert {


    private String shortDescription;
    private String date;
    private String price;
    private AdvertDetails advertDetails;

    public Advert(String shortDescription, String date, String price, AdvertDetails advertDetails) {
        this.shortDescription = shortDescription;
        this.date = date;
        this.price = price;
        this.advertDetails = advertDetails;
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

    @Override
    public String toString() {
        return "Article{" +
            ", shortDescription='" + shortDescription + '\'' +
                ", date='" + date + '\'' +
                ", price='" + price + '\'' +
                ", details='" + advertDetails + '\'' +
                '}';
    }
}
