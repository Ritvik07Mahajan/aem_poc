package com.adobe.aem.guides.wknd.core.dto;

public class Item {

    String title;
    String country;
    public Item(String title, String country){
        this.title=title;
        this.country=country;
    }

    public String getTitle() {
        return title;
    }

    public String getCountry() {
        return country;
    }
}
