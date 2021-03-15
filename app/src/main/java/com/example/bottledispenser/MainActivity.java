package com.example.bottledispenser;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Scanner;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    Context context = null;

    BottleDispenser dispenser = BottleDispenser.getInstance();
    private TextView drink;
    private TextView money;
    private TextView main_text;
    private TextView add_amount;
    private SeekBar money_slider;
    private Spinner drinks;



    private double size_of_bottle = 1.5;
    private String choise = "Pepsi Max";
    private int choise_index = 0;
    private String output = "";
    private String filename = "receipt.txt";
    private String[] output2 = {"", ""};
    private double add_money;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = MainActivity.this;
        drink = (TextView) findViewById(R.id.Drink);
        money = (TextView) findViewById(R.id.Money);
        main_text = (TextView) findViewById(R.id.main_text_window);
        add_amount = (TextView) findViewById(R.id.add_amount);
        money_slider = (SeekBar) findViewById(R.id.seekBar);
        drinks = (Spinner) findViewById(R.id.spinner);



        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.drinks, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        drinks.setAdapter(adapter);
        drinks.setOnItemSelectedListener(this);



        money_slider.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                add_amount.setText("Add amount: " + progress/10 + "€");
                add_money = progress/10;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


        /*
        Scanner input = new Scanner(System.in);

        int choise = 0;
        int choise2 = 0;

        while (true){


            System.out.print("*** BOTTLE DISPENSER ***\n1) Add money to the machine\n2) Buy a bottle\n3) Take money out\n4) List bottles in the dispenser\n0) End\nYour choice: ");
            choise = input.nextInt();

            if (choise == 1){
                dispenser.addMoney();
            }
            else if (choise == 2){
                dispenser.bottlesIn();
                System.out.print("Your choice: ");
                choise2 = input.nextInt();
                dispenser.buyBottle(choise2);
                dispenser.deleteBottle(choise2);
            }
            else if (choise == 3){
                dispenser.returnMoney();
            }

            else if (choise == 4){
                dispenser.bottlesIn();
            }

            else if (choise == 0){
                break;
            } else {
                System.out.print("Joku ei nyt toimi\n");
            }

            System.out.println();*/
    }





    public void pushedBuy(View v){

        output2 = dispenser.buyBottle(choise_index);
        main_text.setText(output2[0]);
        money.setText("Money: " + Double.toString(dispenser.getMoney()) + "€");
    }




    public void pushedMoney(View v){
        dispenser.addMoney(add_money);
        money.setText("Money: " + Double.toString(dispenser.getMoney()) + "€");
        main_text.setText("Klink! Added more money!");
        money_slider.setProgress(0);
    }

    public void removeMoney(View v){
        output = dispenser.returnMoney();
        money.setText("Money: " + Double.toString(dispenser.getMoney()) + "€");
        main_text.setText(output);
    }

    public void pushedReciept(View v){
        String line = output2[1];

        try{

            OutputStreamWriter osw = new OutputStreamWriter(context.openFileOutput(filename, context.MODE_PRIVATE));

            osw.write(line);

            osw.close();


        } catch (IOException e){
            Log.e("IOException", "Reading the file failed.");
        }


    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        choise = parent.getItemAtPosition(position).toString();
        choise_index = position;
        drink.setText(choise);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}