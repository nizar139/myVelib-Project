package fr.cs.group13.myVelib.test.cards;

import fr.cs.group13.myVelib.core.bicycle.ElectricalBicycle;
import fr.cs.group13.myVelib.core.bicycle.MechanicalBicycle;
import fr.cs.group13.myVelib.core.cards.PricingVisitor;
import fr.cs.group13.myVelib.core.dockingstation.PlusStation;
import fr.cs.group13.myVelib.core.dockingstation.RegularStation;

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
