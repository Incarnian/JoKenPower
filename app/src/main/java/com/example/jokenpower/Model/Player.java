package com.example.jokenpower.Model;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class Player implements Serializable {

    private String name;
    private int gold;
    private int goldPerFive;
    private double goldPerWin;
    private double baseGold;
    private ArrayList<Double> multGold = new ArrayList<>();
    private ArrayList<Integer> addGold = new ArrayList<>();

    public Player(String name, int gold, int goldPerFive, int baseGold) {
        this.name = name;
        this.gold = gold;
        this.goldPerFive = goldPerFive;
        this.baseGold = baseGold;
        this.goldPerWin = baseGold;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getGold() {
        return gold;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }

    public int getGoldPerFive() {
        return goldPerFive;
    }

    public void setGoldPerFive(int goldPerFive) {
        this.goldPerFive = goldPerFive;
    }

    public double getGoldPerWin() {
        return goldPerWin;
    }

    public void setGoldPerWin(int goldPerWin) {
        this.goldPerWin = goldPerWin;
    }

    public void addGoldPerWin(int gold) {
        addGold.add(gold);
    }

    public void multGoldPerWin(double gold) {
        multGold.add(gold);
    }

    public void calculateGoldPerWin() {
        int aux = 1;
        int gold = 0;
        for(int i = 0;i< addGold.size();i++) {
            gold += addGold.get(i);
        }
        for(int i = 0;i<multGold.size();i++) {
            aux += multGold.get(i);
        }

        this.goldPerWin = (baseGold + gold) * aux;
    }

    public void earnGold() {
        this.gold += getGoldPerWin();
    }

    public void buyItem(int price) {
        this.gold -= price;
    }
}
