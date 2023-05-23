package fr.cs.Group13.myVelib.DockingStation;

import fr.cs.Group13.myVelib.Cards.PricingVisitor;

public interface DockingStation {
    double accept(PricingVisitor visitor);
}
