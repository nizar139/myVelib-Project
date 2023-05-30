/**
 * This interface defines the PricingVisitor pattern used for differentiating pricing based on the type of bicycle
 * and calculating bonus for the type of station.
 * It has methods to visit each type of bicycle (Electrical and Mechanical) and each type of station (Regular and Plus).
 * Each method returns a double or an array of doubles representing the specific pricing for the visited object
 * or the bonus applied when visiting a certain station.
 *
 * @author Oussama and Nizar
 */
package core.Cards;

import core.DockingStation.PlusStation;
import core.DockingStation.RegularStation;
import core.Bicycle.ElectricalBicycle;
import core.Bicycle.MechanicalBicycle;

/**
 * Represents a visitor for pricing calculations based on different bicycle types and station types.
 */
public interface PricingVisitor {
    /**
     * Calculates the price for an electrical bicycle.
     *
     * @param bicycle the electrical bicycle to calculate the price for
     * @return an array of doubles representing the price breakdown
     */
    double[] visit(ElectricalBicycle bicycle);

    /**
     * Calculates the price for a mechanical bicycle.
     *
     * @param bicycle the mechanical bicycle to calculate the price for
     * @return an array of doubles representing the price breakdown
     */
    double[] visit(MechanicalBicycle bicycle);

    /**
     * Calculates the price for a regular station.
     *
     * @param station the regular station to calculate the price for
     * @return the calculated price
     */
    double visit(RegularStation station);

    /**
     * Calculates the price for a plus station.
     *
     * @param station the plus station to calculate the price for
     * @return the calculated price
     */
    double visit(PlusStation station);
}
