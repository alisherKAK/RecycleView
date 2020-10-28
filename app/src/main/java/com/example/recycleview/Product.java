package com.example.recycleview;

public class Product {
    private String name;
    private String description;
    private int image;
    private double price;

    Product(int image, String name, String description, double price){
        this.name = name;
        this.description = description;
        this.image = image;
        this.price = price;
    }

    public void setName(String name){
        this.name = name;
    }
    public String getName(){
        return this.name;
    }

    public void setDescription(String description){
        this.description = description;
    }
    public String getDescription(){
        return this.description;
    }

    public void setImage(int image){
        this.image = image;
    }
    public int getImage(){
        return this.image;
    }

    public void setPrice(double price){
        this.price = price;
    }
    public double getPrice(){
        return this.price;
    }
}
