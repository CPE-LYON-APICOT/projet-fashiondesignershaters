package fr.cpe.service;

public interface ICurrencyObserver {

    public default void onGoldChanged(int NewGold){

    }
}
