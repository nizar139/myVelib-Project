package fr.cs.Group13.myVelib.Cards;

import fr.cs.Group13.myVelib.Bicycle.Bicycle;
import fr.cs.Group13.myVelib.Bicycle.ElectricalBicycle;
import fr.cs.Group13.myVelib.Bicycle.MechanicalBicycle;
import fr.cs.Group13.myVelib.DockingStation.DockingStation;

public class NoCard implements Card, PricingVisitor {

    @Override
    public double visit(ElectricalBicycle bicycle) {
        return 2;
    }

    @Override
    public double visit(MechanicalBicycle bicycle) {
        return 1;
    }

    @Override
    public double computeCharge(Bicycle b, int start, int end) {
        return 0;
    }

    @Override
    public void updateBalance(DockingStation station) {

    }
}

