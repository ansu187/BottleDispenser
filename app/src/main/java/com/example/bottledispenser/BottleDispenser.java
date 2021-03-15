package com.example.bottledispenser;

import android.content.Context;

import java.util.ArrayList;

import static java.lang.String.format;


public class BottleDispenser {

    private static BottleDispenser bc = new BottleDispenser();
    private int bottles;
    ArrayList<Bottle> bottle_list = new ArrayList();
    private double money;



    private BottleDispenser() {

        bottles = 5;
        money = 0;

        //lista jossa pulloja


        Bottle pepsi05 = new Bottle("Pepsi Max", 0.5, 1.8);
        bottle_list.add(pepsi05);
        Bottle pepsi15 = new Bottle("Pepsi Max", 1.5, 2.2);
        bottle_list.add(pepsi15);
        Bottle cola05 = new Bottle("Coca-Cola Zero", 0.5, 2.0);
        bottle_list.add(cola05);
        Bottle cola15 = new Bottle("Coca-Cola Zero", 1.5, 2.5);
        bottle_list.add(cola15);
        Bottle fanta05 = new Bottle("Fanta Zero", 0.5, 1.95);
        bottle_list.add(fanta05);

    }


    public static BottleDispenser getInstance(){
        return bc;
    }

    public void addMoney(double amount) {
        money += amount;
    }

    public String[] buyBottle(int choise) {
        int counter = 0;
        String[] out = {"", ""};
        boolean found = false;
        String name = "";
        double size = 0.5;



        switch (choise){
            case 0:
                name = "Pepsi Max";
                size = 0.5;
                break;


            case 1:
                name = "Pepsi Max";
                size = 1.5;
                break;

            case 2:

                name = "Coca-Cola Zero";
                size = 0.5;
                break;

            case 3:
                name = "Coca-Cola Zero";
                size = 1.5;
                break;


            case 4:
                name = "Fanta Zero";
                size = 0.5;
                break;

        }

        for (Bottle bottle : bottle_list){
            if (bottle.getName() == name && bottle.getSize() == size) {

                if (bottle.getPrice() > money){
                    out[0] = "You don't have enough money.";
                    return out;
                }

                out[0] = "KACHUNK! " + bottle.getName() + " came out of the dispenser!";
                out[1] = "Receipt, product: " + bottle.getName() + " " + bottle.getSize() + " " + bottle.getPrice() + "€\n";
                money = money - bottle.getPrice();
                bottle_list.remove(counter);

                found = true;
                break;
            }
            counter++;

        }
        if (found == false){
            out[0] = "There is no " + name + " in size of " + size + "l in stock.";
            out[1] = "";
        }

        return out;


    }




    public String returnMoney() {


        String out = format("Klink klink. Money came out! You got %.2f€ back", money);
        money = 0;
        return out;
    }


    public double getMoney(){
        return money;
    }

    public void bottlesIn(){

        int counter = 1;
        //Printtaa ulos pullot jota koneessa on

        for (Bottle unit : bottle_list){
            System.out.println(counter + ". Name: " + unit.getName() + "\n	Size: " + unit.getSize() + "	Price: " + unit.getPrice() );
            counter++;
        }

    }
}