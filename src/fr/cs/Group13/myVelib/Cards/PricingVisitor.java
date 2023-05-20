package fr.cs.Group13.myVelib.Cards;

import fr.cs.Group13.myVelib.Bicycle.ElectricalBicycle;
import fr.cs.Group13.myVelib.Bicycle.MechanicalBicycle;

public interface PricingVisitor {
    double visit(ElectricalBicycle bicycle);
    double visit(MechanicalBicycle bicycle);
}
