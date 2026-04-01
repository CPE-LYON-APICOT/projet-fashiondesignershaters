package fr.cpe.service;

public class CurrencyService {
    private int gold;

    public CurrencyService(int gold){
        this.gold = gold;
    }

    public int getGold() {
        return gold;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }
    public void addGold(int amount){
        this.gold += amount;
    }
    public void spendGold(int amount){
        if(this.gold >= amount){
            this.gold -= amount;
        } else {
            throw new IllegalArgumentException("Not enough gold");
        }
    }
}
