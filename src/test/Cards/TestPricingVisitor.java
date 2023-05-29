package test.Cards;

import fr.cs.Group13.myVelib.Bicycle.ElectricalBicycle;
import fr.cs.Group13.myVelib.Bicycle.MechanicalBicycle;
import fr.cs.Group13.myVelib.Cards.PricingVisitor;
import fr.cs.Group13.myVelib.DockingStation.PlusStation;
import fr.cs.Group13.myVelib.DockingStation.RegularStation;

public class TestPricingVisitor implements PricingVisitor {

    @Override
    public double[] visit(ElectricalBicycle bicycle) {
        return new double[0]; // Just return an empty array for testing
    }

    @Override
    public double[] visit(MechanicalBicycle bicycle) {
        return new double[0]; // Just return an empty array for testing
    }

    @Override
    public double visit(RegularStation station) {
        return 0.0; // Just return zero for testing
    }

    @Override
    public double visit(PlusStation station) {
        return 0.0; // Just return zero for testing
    }
}
