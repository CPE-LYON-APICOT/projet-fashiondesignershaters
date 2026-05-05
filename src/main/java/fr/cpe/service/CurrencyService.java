package fr.cpe.service;

import com.google.inject.Singleton;
import java.util.ArrayList;
import java.util.List;

@Singleton
public class CurrencyService {
    private int gold = 100; 
    private final List<ICurrencyObserver> observers = new ArrayList<>();

    public void addObserver(ICurrencyObserver observer) {
        observers.add(observer);
        observer.onGoldChanged(gold);
    }

    public void addGold(int amount) {
        this.gold += amount;
        notifyObservers();
    }

    public boolean spendGold(int amount) {
        if (this.gold >= amount) {
            this.gold -= amount;
            notifyObservers();
            return true;
        }
        return false;
    }

    private void notifyObservers() {
        for (ICurrencyObserver obs : observers) {
            obs.onGoldChanged(gold);
        }
    }
    
    public int getGold() { return gold; }
}