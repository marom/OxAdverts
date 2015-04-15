package com.maro.oxadverts;

/**
 * Created by maro on 12/04/2015.
 */
public class AdvertDetails {

    private String dateAdded;
    private String advertHeader;
    private String price;

    private String sellerName;
    private String sellerPhone;
    private String sellerCity;
    private String sellerEmail;

    private String shortContent;
    private String fullContent;
    private String link;

    private String imageUrl1;
    private String imageUrl2;
    private String imageUrl3;


    public AdvertDetails(String dateAdded, String advertHeader, String price, String sellerName, String sellerPhone, String sellerCity, String sellerEmail, String shortContent, String fullContent, String link, String imageUrl1, String imageUrl2, String imageUrl3) {
        this.dateAdded = dateAdded;
        this.advertHeader = advertHeader;
        this.price = price;
        this.sellerName = sellerName;
        this.sellerPhone = sellerPhone;
        this.sellerCity = sellerCity;
        this.sellerEmail = sellerEmail;
        this.shortContent = shortContent;
        this.fullContent = fullContent;
        this.link = link;
        this.imageUrl1 = imageUrl1;
        this.imageUrl2 = imageUrl2;
        this.imageUrl3 = imageUrl3;
    }

    public String getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(String dateAdded) {
        this.dateAdded = dateAdded;
    }

    public String getAdvertHeader() {
        return advertHeader;
    }

    public void setAdvertHeader(String advertHeader) {
        this.advertHeader = advertHeader;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getSellerName() {
        return sellerName;
    }

    public void setSellerName(String sellerName) {
        this.sellerName = sellerName;
    }

    public String getSellerPhone() {
        return sellerPhone;
    }

    public void setSellerPhone(String sellerPhone) {
        this.sellerPhone = sellerPhone;
    }

    public String getSellerCity() {
        return sellerCity;
    }

    public void setSellerCity(String sellerCity) {
        this.sellerCity = sellerCity;
    }

    public String getSellerEmail() {
        return sellerEmail;
    }

    public void setSellerEmail(String sellerEmail) {
        this.sellerEmail = sellerEmail;
    }

    public String getShortContent() {
        return shortContent;
    }

    public void setShortContent(String shortContent) {
        this.shortContent = shortContent;
    }

    public String getFullContent() {
        return fullContent;
    }

    public void setFullContent(String fullContent) {
        this.fullContent = fullContent;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getImageUrl1() {
        return imageUrl1;
    }

    public void setImageUrl1(String imageUrl1) {
        this.imageUrl1 = imageUrl1;
    }

    public String getImageUrl2() {
        return imageUrl2;
    }

    public void setImageUrl2(String imageUrl2) {
        this.imageUrl2 = imageUrl2;
    }

    public String getImageUrl3() {
        return imageUrl3;
    }

    public void setImageUrl3(String imageUrl3) {
        this.imageUrl3 = imageUrl3;
    }



    @Override
    public String toString() {
        return "AdvertDetails{" +
                "dateAdded='" + dateAdded + '\'' +
                ", advertHeader='" + advertHeader + '\'' +
                ", price='" + price + '\'' +
                ", sellerName='" + sellerName + '\'' +
                ", sellerPhone='" + sellerPhone + '\'' +
                ", sellerCity='" + sellerCity + '\'' +
                ", sellerEmail='" + sellerEmail + '\'' +
                ", shortContent='" + shortContent + '\'' +
                ", fullContent='" + fullContent + '\'' +
                ", link='" + link + '\'' +
                ", imageUrl1='" + imageUrl1 + '\'' +
                ", imageUrl2='" + imageUrl2 + '\'' +
                ", imageUrl3='" + imageUrl3 + '\'' +
               '}';
    }
}
