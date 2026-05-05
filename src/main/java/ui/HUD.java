package ui;

import fr.cpe.service.ICurrencyObserver;
import fr.cpe.service.GameState;

public class HUD implements ICurrencyObserver { 
    private int currentGold;

    public HUD(int currentGold) {
        this.currentGold = currentGold;
    }

    @Override
    public void onGoldChanged(int newGold) {
        this.currentGold = newGold;
        updateDisplay();
    }

    private void updateDisplay() {
        System.out.println("HUD -> Or actuel : " + currentGold);
        System.out.println("Tours : " + GameState.getInstance().getNbTourConstruite());
    }
}