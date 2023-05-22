package fr.cs.Group13.myVelib.Bicycle;

import fr.cs.Group13.myVelib.Cards.PricingVisitor;

public class ElectricalBicycle extends Bicycle{
    public ElectricalBicycle() {
        super();
    }

    @Override
    public double[] accept(PricingVisitor visitor) {
        return visitor.visit(this);
    }
}
