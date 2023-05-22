package fr.cs.Group13.myVelib.Bicycle;

import fr.cs.Group13.myVelib.Cards.PricingVisitor;

public class MechanicalBicycle extends Bicycle{
    public MechanicalBicycle() {
        super();
    }

    public double[] accept(PricingVisitor visitor) {
        return visitor.visit(this);
    }
}

