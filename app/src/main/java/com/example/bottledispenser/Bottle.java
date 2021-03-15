package com.example.bottledispenser;

public class Bottle {
    private String name;
    private double size;
    private double price;


    public Bottle(){
        name = "Pepsi Max";
        //manufacturer = "Pepsi";
        size = 0.5;
        price = 1.80;
    }
    public Bottle(String name, double size_off, double price){
        this.name = name;
        this.size = size_off;
        this.price = price;

    }
    public String getName(){
        return name;
    }

    public double getSize(){
        return size;
    }

    public double getPrice(){
        return price;
    }
}
