package fr.cs.Group13.myVelib.Cards;

import fr.cs.Group13.myVelib.Bicycle.Bicycle;
import fr.cs.Group13.myVelib.Bicycle.ElectricalBicycle;
import fr.cs.Group13.myVelib.Bicycle.MechanicalBicycle;
import fr.cs.Group13.myVelib.DockingStation.DockingStation;
import fr.cs.Group13.myVelib.DockingStation.PlusStation;
import fr.cs.Group13.myVelib.DockingStation.RegularStation;
import fr.cs.Group13.myVelib.User.User;

public class NoCard implements Card, PricingVisitor {

    private User owner;
    @Override
    public double[] visit(ElectricalBicycle bicycle) {return new double[]{2,2};}
    @Override
    public double[] visit(MechanicalBicycle bicycle) {return new double[]{1,1};}
    @Override
    public double visit(RegularStation station) {return 0;}
    @Override
    public double visit(PlusStation station) {return 0;}

    @Override
    public double computeCharge(Bicycle b, int endingIsStation, double duration) {
        double[] priceHour = b.accept(this);
        double basePrice = priceHour[0]*duration;
        double finalPrice = basePrice*(1-0.1*(endingIsStation -1));
        return finalPrice/60;
    }

    @Override
    public void applyBonus(DockingStation station) {
    }
}

