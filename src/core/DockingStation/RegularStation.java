/**
 * The RegularStation class represents a standard docking station, extending the DockingStation class.
 * It provides basic functionality and behavior for regular stations.
 * Regular stations do not offer any special features or bonuses.
 * It implements the PricingVisitor interface for bonus granting.
 *
 * @author Oussama and Nizar
 */
package core.DockingStation;

import core.System.VelibSystem;
import core.Cards.PricingVisitor;

public class RegularStation extends DockingStation{
    /**
     * Default constructor for RegularStation.
     */
    public RegularStation() {super();}

    /**
     * Constructor for RegularStation with specified parameters.
     *
     * @param gpsCord The GPS coordinates of the docking station.
     * @param numberOfSlots The total number of parking slots in the station.
     * @param numberOfVacantSlots The number of vacant parking slots in the station.
     * @param numberOfElectricalBikes The number of electrical bikes in the station.
     */
    public RegularStation(VelibSystem vlibSys, double[] gpsCord, int numberOfSlots, int numberOfVacantSlots, int numberOfElectricalBikes) {
        super(vlibSys, gpsCord, numberOfSlots, numberOfVacantSlots, numberOfElectricalBikes);
    }

    /**
     * Accepts a PricingVisitor to grant bonus after returning a bike.
     *
     * @param visitor The PricingVisitor object.
     */
    public double accept(PricingVisitor visitor){
        return visitor.visit(this);
    }
}
