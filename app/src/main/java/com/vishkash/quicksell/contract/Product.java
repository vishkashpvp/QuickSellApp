package com.vishkash.quicksell.contract;

public class Product {
    private String name, id, description, imageLink;
    private Integer price;

    public Product(String id) {
        this.id = id;
    }

    public Product(String name, String id, String description, String image, Integer price) {
        this.name = name;
        this.id = id;
        this.description = description;
        this.imageLink = image;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public String getImageLink() {
        return imageLink;
    }

    public Integer getPrice() {
        return price;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }
}