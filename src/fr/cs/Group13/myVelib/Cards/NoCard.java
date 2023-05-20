package fr.cs.Group13.myVelib.Cards;

import fr.cs.Group13.myVelib.Bicycle.Bicycle;
import fr.cs.Group13.myVelib.Bicycle.ElectricalBicycle;
import fr.cs.Group13.myVelib.Bicycle.MechanicalBicycle;
import fr.cs.Group13.myVelib.DockingStation.DockingStation;

public class NoCard implements Card, PricingVisitor {

    @Override
    public double[] visit(ElectricalBicycle bicycle) {
        return new double[]{2,2};
    }

    @Override
    public double[] visit(MechanicalBicycle bicycle) {
        return new double[]{1,1};
    }

    @Override
    public double computeCharge(Bicycle b, int start, int end, double duration) {
        double[] priceHour = b.accept(this);
        double price = priceHour[0]*duration/60;
        return price;
    }

    @Override
    public void updateBalance(DockingStation station) {

    }
}

