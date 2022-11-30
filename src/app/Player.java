package app;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import java.awt.Color;

/**
 * Class for player management,
 * the class is saved in menager.sr
 * 
 * @author Alessandro Gabriele
 */
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

    /**
     * The function is to add money to a player
     * 
     * @param money money to add
     */
    public void addMoney(int money) {
        this.money += money;
    }

    /**
     * The function is to subtract money from a player
     * 
     * @param money money to subtract
     * @throws MoneyException if the money is not enough
     */
    public void subMoney(int money) throws MoneyException {
        if (this.money - money < 0) {
            throw new MoneyException("Soldi insufficienti");
        }
        
        this.money -= money;
    }

    public Color getColor() {
        return this.color;
    }

    public int getBox() {
        return this.box;
    }

    
    /**
     * Method of moving and keeping track of a player's square
     * If box == 40 then the board is finished and you return to box 0
     */
    public void advanceBox() {
        this.box++;

        if (this.box == 40) {
            this.box = 0;
        }
    }
}