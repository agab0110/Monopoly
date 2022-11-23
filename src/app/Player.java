package app;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import java.awt.Color;

/**
 * Classe per la gestione dei giocatori,
 * la classe viene salvata in menager.sr
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
     * La funzione serve ad aggiungere soldi ad un giocatore
     * 
     * @param money soldi da aggiungere
     */
    public void addMoney(int money) {
        this.money += money;
    }

    /**
     * La funzione serve a sottrarre soldi da un giocatore
     * 
     * @param money soldi da sottrarre
     * @throws MoneyException se i soldi non sono sufficienti
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
     * Metodo per muovere e tener traccia della casella di un giocatore
     * Nel caso box == 40 allora il tabellone è finito e si ritorna alla casella 0
     */
    public void advanceBox() {
        this.box++;

        if (this.box == 40) {
            this.box = 0;
        }
    }
}