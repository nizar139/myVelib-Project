package fr.cs.Group13.myVelib.Cards;

import fr.cs.Group13.myVelib.Bicycle.Bicycle;
import fr.cs.Group13.myVelib.DockingStation.DockingStation;

public interface Card {
    public double computeCharge(Bicycle b, int start, int end);
    public void updateBalance(DockingStation station);

}
