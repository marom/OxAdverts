package com.maro.oxadverts;

/**
 * Created by maro on 21/03/2015.
 * Class to keep an info about single advert.
 */
public class Advert {

    private String link;
    private String shortDescription;
    private String longDescription;
    private String date;
    private String price;

    public Advert(String link, String shortDescription, String longDescription, String date, String price) {
        this.link = link;
        this.shortDescription = shortDescription;
        this.longDescription = longDescription;
        this.date = date;
        this.price = price;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getLongDescription() {
        return longDescription;
    }

    public void setLongDescription(String longDescription) {
        this.longDescription = longDescription;
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

    @Override
    public String toString() {
        return "Article{" +
                "link='" + link + '\'' +
                ", shortDescription='" + shortDescription + '\'' +
                ", longDescription='" + longDescription + '\'' +
                ", date='" + date + '\'' +
                ", price='" + price + '\'' +
                '}';
    }
}
