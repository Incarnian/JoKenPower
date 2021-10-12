package com.example.jokenpower.Model;

import android.widget.Toast;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;

public class Player implements Serializable {
    //Por enquanto não estou usando goldPerFive
    private String name;
    private int gold;
    private int goldPerFive;
    private double goldPerWin;
    private double baseGold;
    private int critChance;
    private ArrayList<Boolean> upgrades = new ArrayList<>();
    private ArrayList<Double> multGold = new ArrayList<>();
    private ArrayList<Integer> addGold = new ArrayList<>();

    //Construtor do jogador
    public Player(String name, int gold, int goldPerFive, int baseGold) {
        this.name = name;
        this.gold = gold;
        this.goldPerFive = goldPerFive;
        this.baseGold = baseGold;
        this.goldPerWin = baseGold;
        this.critChance = 0;

        for(int i = 0;i<5;i++) {
            this.upgrades.add(false);
        }
    }
    //Obtém o nome do jogador para mostrar na tela
    public String getName() {
        return name;
    }
    //Obtém o ouro do jogador para mostrar na tela
    public int getGold() {
        return gold;
    }
    //Obtém o ouro por vitória do jogador para somar ao ouro total
    public double getGoldPerWin() {
        return goldPerWin;
    }
    //Obtém a chance de crítico
    public int getCritChance() {
        return critChance;
    }
    //Verifica se aconteceu um acerto crítico
    public boolean isCrit() {
        Random random = new Random();
        if(random.nextInt(100)<=this.getCritChance() && critChance != 0) {
            return true;
        }
        else {
            return false;
        }
    }
    //Adiciona ouro para o jogador baseado no getGoldPerWin
    public void earnGold() {
        if(isCrit()){
            this.gold += 5*getGoldPerWin();
        }
        else{
            this.gold += getGoldPerWin();
        }

    }
    //Verifica se o jogador consegue comprar um Upgrade
    public boolean canBuy(Upgrade upgrade) {
        if(upgrade.getPrice() > this.getGold()) {
            return false;
        }
        else{
            return true;
        }
    }
    //Adiciona chance de crítico ao jogador
    public void addCrit(int crit) {
        if(this.critChance + crit > 100) {
            this.critChance = 100;
        }
        else {
            this.critChance += crit;
        };
    }
    //Adiciona os status do Upgrade ao jogador, chama a função addCrit
    public void buyItem(Upgrade upgrade) {
            this.gold -= upgrade.getPrice();
            addGold.add(upgrade.getAddValue());
            multGold.add(upgrade.getMultValue());
            this.addCrit(upgrade.getCritChance());
        }
    //Pega o valor base de uma vitória
    public int getBaseWin() {
        int base = 1;
        for (int i = 0;i<addGold.size();i++) {
            base += addGold.get(i);
        }
        return base;
    }
    //Pega o multiplicador de uma vitória
    public double getMultWin() {
        double aux = 1;
        for(int i = 0;i<multGold.size();i++) {
            aux += multGold.get(i);
        }
        return aux;
    }
    //Atualiza os valores do jogador
    public void refreshGoldPerWin() {
        int base = getBaseWin();
        double aux = getMultWin();
        this.goldPerWin = base * aux;
    }
    //Verifica se um upgrade já foi comprado ou não
    public boolean checkUpgrades(int pos) {
        return upgrades.get(pos);
    }
    //Coloca no jogador que ele comprou um certo upgrade
    public void changeUpgrades(int pos) {
        upgrades.set(pos, true);
    }

}
