package fr.cs.Group13.myVelib.Cards;

import fr.cs.Group13.myVelib.Bicycle.Bicycle;
import fr.cs.Group13.myVelib.DockingStation.DockingStation;

public interface Card {
    double computeCharge(Bicycle b, int start, int end);
    void updateBalance(DockingStation station) ;
}
