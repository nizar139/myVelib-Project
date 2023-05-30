package test.Cards;

import core.Bicycle.ElectricalBicycle;
import core.Bicycle.MechanicalBicycle;
import core.Cards.PricingVisitor;
import core.DockingStation.PlusStation;
import core.DockingStation.RegularStation;

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
