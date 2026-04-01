package ui;

import fr.cpe.service.ICurrencyObserver;

public class HUD implements ICurrencyObserver {
    private int currentGold;

    public HUD(int currentGold) {
        this.currentGold = currentGold;
    }

    public void onGoldChanged(int newGold) {
        this.currentGold = newGold;
        updateDisplay();
    }
    private void updateDisplay() {
        // Code to update the HUD display with the new gold amount
        System.out.println("Current Gold: " + currentGold);
    }
}
