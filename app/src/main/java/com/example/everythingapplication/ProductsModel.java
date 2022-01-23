package com.example.everythingapplication;

public class ProductsModel {
    public ProductsModel() {
    }

    String title;
    String description;
    String price;
    String image;

    public ProductsModel(String title, String description, String price, String URL) {
        this.title = title;
        this.description = description;
        this.price = price;
        this.image = URL;
    }
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
    public String getImage() {
        return image;
    }
    public void setImage(String image) {
        this.image = image;
    }
}
