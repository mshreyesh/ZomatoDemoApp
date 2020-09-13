package com.soulbuster.zomatodemoapp.Model;

import java.io.Serializable;

public class Restaurant implements Serializable {

    private String name,address,latitude,longitude,avg_cost_for_two,currency,image,url,rating;

    public Restaurant(String name, String address, String latitude, String longitude, String avg_cost_for_two, String currency, String image, String url, String rating) {
        this.name = name;
        this.address = address;
        this.latitude = latitude;
        this.longitude = longitude;
        this.avg_cost_for_two = avg_cost_for_two;
        this.currency = currency;
        this.image = image;
        this.url = url;
        this.rating=rating;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getLatitude() {
        return latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public String getAvg_cost_for_two() {
        return avg_cost_for_two;
    }

    public String getCurrency() {
        return currency;
    }

    public String getImage() {
        return image;
    }

    public String getUrl() {
        return url;
    }

    public String getRating() {
        return rating;
    }


}


