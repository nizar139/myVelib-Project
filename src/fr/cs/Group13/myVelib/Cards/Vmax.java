package fr.cs.Group13.myVelib.Cards;

import fr.cs.Group13.myVelib.Bicycle.Bicycle;
import fr.cs.Group13.myVelib.DockingStation.DockingStation;

public class Vmax implements Card{
    private int id;
    private double CreditBalance;
    private static int count;
    private synchronized int generateUniqueId() {
        return count++;
    }
    @Override
    public double computeCharge(Bicycle b, int start, int end) {
        return 0;
    }

    @Override
    public void updateBalance(DockingStation station) {

    }


}
