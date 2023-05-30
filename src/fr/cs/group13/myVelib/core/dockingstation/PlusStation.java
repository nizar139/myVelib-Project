/**
 * The PlusStation class represents a docking station with special features, extending the DockingStation class.
 * It provides additional functionality and behavior specific to Plus stations.
 * Plus stations offer a credit bonus when users return bikes to these stations.
 * It implements the PricingVisitor interface for bonus granting.
 *
 * @author Oussama and Nizar
 */
package fr.cs.group13.myVelib.core.dockingstation;


import fr.cs.group13.myVelib.core.cards.PricingVisitor;
import fr.cs.group13.myVelib.core.system.VelibSystem;


public class PlusStation extends DockingStation {

    /**
     * Default constructor for PlusStation.
     */
    public PlusStation() {super();}

    /**
     * Constructor for PlusStation with specified parameters.
     *
     * @param gpsCord The GPS coordinates of the docking station.
     * @param numberOfSlots The total number of parking slots in the station.
     * @param numberOfVacantSlots The number of vacant parking slots in the station.
     * @param numberOfElectricalBikes The number of electrical bikes in the station.
     */
    public PlusStation(VelibSystem vlibSys, double[] gpsCord, int numberOfSlots, int numberOfVacantSlots, int numberOfElectricalBikes) {
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
