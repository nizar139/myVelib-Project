package fr.cs.Group13.myVelib.Bicycle;

import fr.cs.Group13.myVelib.Cards.PricingVisitor;

public interface Bicycle{
    double[] accept(PricingVisitor visitor);
}
