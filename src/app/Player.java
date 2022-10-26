package app;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Player implements Serializable {
    private String name;
    private String pawn;
    private boolean prison;    
    private int money;
    private List<Contract> contracts;

    public Player(String name, String pawn) {
        this.name = name;
        this.pawn = pawn;
        contracts = new ArrayList<>();
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

    public String getPawn() {
        return this.pawn;
    }
}