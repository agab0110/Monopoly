package app;

import java.io.Serializable;

public class Contract implements Serializable{
    private String name;
    private int price;
    private int rent;
    private Player owner;

    public Contract(String name, int price, int rent) {
        this.name = name;
        this.price = price;
        this.rent = rent;
    }

    public int getPrice() {
        return price;
    }

    public int getRent() {
        return rent;
    }

    public String getName() {
        return name;
    }

    public void setOwner(Player owner) {
        this.owner = owner;
    }

    public Player getOwner() {
        return owner;
    }
}
