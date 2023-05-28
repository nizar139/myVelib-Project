/**
 * The MechanicalBicycle class extends the Bicycle class, representing a mechanical bicycle in the system.
 * It provides methods to modify the bicycle count at docking stations as well as accepting visitor for pricing.
 *
 * @author Oussama and Nizar
 */
package fr.cs.Group13.myVelib.Bicycle;

import fr.cs.Group13.myVelib.Cards.PricingVisitor;
import fr.cs.Group13.myVelib.DockingStation.DockingStation;
import fr.cs.Group13.myVelib.DockingStation.ParkingSlot;

public class MechanicalBicycle extends Bicycle{
    /**
     * Default constructor for the MechanicalBicycle class, calls the super class constructor.
     */
    public MechanicalBicycle() {
        super();
    }

    /**
     * Constructor for the MechanicalBicycle class with GPS coordinates parameter.
     *
     * @param gpsCord GPS coordinates for the bicycle.
     */
    public MechanicalBicycle(double[] gpsCord) {
        super(gpsCord);
    }

    /**
     * Constructor that takes all the attributes other than ID, which is automatically generated
     *
     * @param gpsCord
     * @param fromAStation
     * @param slot
     */
    public MechanicalBicycle(double[] gpsCord, int fromAStation, ParkingSlot slot) {
        super(gpsCord, fromAStation, slot);
    }

    /**
     * Method to accept a PricingVisitor, allowing for implementation of the Visitor pattern for pricing.
     *
     * @param visitor The PricingVisitor visiting this instance of MechanicalBicycle.
     * @return Result of the visit.
     */
    public double[] accept(PricingVisitor visitor) {
        return visitor.visit(this);
    }

    /**
     * Method to update bicycle count when a mechanical bicycle is removed from a ParkingSlot.
     *
     * @param slot The ParkingSlot from which the bicycle is removed.
     */
    public void setBikeCountFree(ParkingSlot slot) {
        slot.getStation().setNumberOfVacantSlots(slot.getStation().getNumberOfVacantSlots()+1);
    }

    /**
     * Method to update bicycle count when a mechanical bicycle is added to a ParkingSlot.
     *
     * @param slot The ParkingSlot to which the bicycle is added.
     */
    public void setBikeCountFill(ParkingSlot slot) {
        slot.getStation().setNumberOfVacantSlots(slot.getStation().getNumberOfVacantSlots()-1);
    }

    @Override
    public boolean equals(Object obj){
        if(obj == this) return true;
        if(obj instanceof MechanicalBicycle)
            return ((MechanicalBicycle)obj).getId()==this.getId();
        return false;
    }
    public static Bicycle findBikeAtStation(DockingStation station){
        for (ParkingSlot slot: station.getParkingSlotArraylist()){
            if (slot.isOccupied() && slot.getBicycle() instanceof MechanicalBicycle){
                return slot.getBicycle();
            }
        }
        throw new RuntimeException("No Mechanical Bicycle found at the given station");
    }
}

