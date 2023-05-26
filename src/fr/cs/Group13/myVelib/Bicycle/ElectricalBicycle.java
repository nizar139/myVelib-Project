/**
 * The ElectricalBicycle class extends the Bicycle class, representing an electrical bicycle in the system.
 * It provides methods to modify the bicycle count at docking stations as well as accepting visitor for pricing.
 *
 * @author Oussama and Nizar
 */
package fr.cs.Group13.myVelib.Bicycle;

import fr.cs.Group13.myVelib.Cards.PricingVisitor;
import fr.cs.Group13.myVelib.DockingStation.DockingStation;
import fr.cs.Group13.myVelib.DockingStation.ParkingSlot;

public class ElectricalBicycle extends Bicycle{
    /**
     * Default constructor for the ElectricalBicycle class, calls the super class constructor.
     */
    public ElectricalBicycle() {
        super();
    }

    /**
     * Constructor for the ElectricalBicycle class with GPS coordinates parameter.
     *
     * @param gpsCord GPS coordinates for the bicycle.
     */
    public ElectricalBicycle(double[] gpsCord) {
        super(gpsCord);
    }

    /**
     * Method to accept a PricingVisitor, allowing for implementation of the Visitor pattern for pricing.
     *
     * @param visitor The PricingVisitor visiting this instance of ElectricalBicycle.
     * @return Result of the visit.
     */
    @Override
    public double[] accept(PricingVisitor visitor) {
        return visitor.visit(this);
    }

    /**
     * Method to update bicycle count when an electrical bicycle is removed from a ParkingSlot.
     *
     * @param slot The ParkingSlot from which the bicycle is removed.
     */
    @Override
    public void setBikeCountFree(ParkingSlot slot) {
        slot.getStation().setNumberOfVacantSlots(slot.getStation().getNumberOfVacantSlots()+1);
        slot.getStation().setNumberOfElectricalBikes(slot.getStation().getNumberOfElectricalBikes()-1);
    }

    /**
     * Method to update bicycle count when an electrical bicycle is added to a ParkingSlot.
     *
     * @param slot The ParkingSlot to which the bicycle is added.
     */
    public void setBikeCountFill(ParkingSlot slot) {
        slot.getStation().setNumberOfVacantSlots(slot.getStation().getNumberOfVacantSlots()-1);
        slot.getStation().setNumberOfElectricalBikes(slot.getStation().getNumberOfElectricalBikes()+1);
    }
}
