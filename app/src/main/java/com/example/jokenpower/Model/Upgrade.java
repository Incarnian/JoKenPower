package com.example.jokenpower.Model;

import android.widget.Button;

import com.example.jokenpower.Activity.GameActivity;

public class Upgrade {

    private int price;
    private int addValue;
    private double multValue;
    private int critChance;

    public Upgrade(int price, int addValue, double multValue, int critChance) {
        this.price = price;
        this.addValue = addValue;
        this.multValue = multValue;
        this.critChance = critChance;
    }

    public double getMultValue() {
        return multValue;
    }

    public int getAddValue() {
        return addValue;
    }

    public int getPrice() {
        return price;
    }

    public int getCritChance() {
        return critChance;
    }

}
