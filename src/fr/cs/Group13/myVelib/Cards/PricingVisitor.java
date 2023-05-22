package fr.cs.Group13.myVelib.Cards;

import fr.cs.Group13.myVelib.Bicycle.ElectricalBicycle;
import fr.cs.Group13.myVelib.Bicycle.MechanicalBicycle;
import fr.cs.Group13.myVelib.DockingStation.PlusStation;
import fr.cs.Group13.myVelib.DockingStation.RegularStation;

public interface PricingVisitor {
    double[] visit(ElectricalBicycle bicycle);
    double[] visit(MechanicalBicycle bicycle);
    double visit(RegularStation station);
    double visit(PlusStation station);
}
