package fr.cs.Group13.myVelib.Bicycle;

import fr.cs.Group13.myVelib.Cards.PricingVisitor;
import fr.cs.Group13.myVelib.DockingStation.DockingStation;
import fr.cs.Group13.myVelib.DockingStation.ParkingSlot;

public class ElectricalBicycle extends Bicycle{
    public ElectricalBicycle() {
        super();
    }

    @Override
    public double[] accept(PricingVisitor visitor) {
        return visitor.visit(this);
    }

    @Override
    public void setBikeCountFree(ParkingSlot slot) {
        slot.getStation().setNumberOfVacantSlots(slot.getStation().getNumberOfVacantSlots()+1);
        slot.getStation().setNumberOfElectricalBikes(slot.getStation().getNumberOfElectricalBikes()-1);
    }
    public void setBikeCountFill(ParkingSlot slot) {
        slot.getStation().setNumberOfVacantSlots(slot.getStation().getNumberOfVacantSlots()-1);
        slot.getStation().setNumberOfElectricalBikes(slot.getStation().getNumberOfElectricalBikes()+1);
    }
}
