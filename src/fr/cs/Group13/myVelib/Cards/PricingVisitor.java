/**
 * This interface defines the PricingVisitor pattern used for differentiating pricing based on the type of bicycle
 * and calculating bonus for the type of station.
 * It has methods to visit each type of bicycle (Electrical and Mechanical) and each type of station (Regular and Plus).
 * Each method returns a double or an array of doubles representing the specific pricing for the visited object
 * or the bonus applied when visiting a certain station.
 *
 * @author Oussama and Nizar
 */
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
