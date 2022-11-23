package app;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import java.awt.Color;

public class Player implements Serializable {
    private String name;
    private Color color;
    private boolean prison;    
    private int money;
    private List<Contract> contracts;
    private int box;

    public Player(String name, Color color) {
        this.name = name;
        this.color = color;
        contracts = new ArrayList<>();
        this.box = 0;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public String getName(){
        return name;
    }

    public void addContract(Contract contract){
        this.contracts.add(contract);
        contract.setOwner(this);
    }

    public List<Contract> getContract(){
        return contracts;
    }

    public void setStatus(boolean status){
        this.prison = status;
    }

    public boolean getStatus() {
        return prison;
    }

    public void addMoney(int money) {
        this.money += money;
    }

    public void subMoney(int money) throws MoneyExeption {
        if (this.money - money < 0) {
            throw new MoneyExeption("Soldi insufficienti");
        }
        
        this.money -= money;
    }

    public Color getColor() {
        return this.color;
    }

    public int getBox() {
        return this.box;
    }

    public void setBox() {
        this.box++;

        if (this.box == 40) {
            this.box = 0;
        }
    }
}