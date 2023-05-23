package fr.cs.Group13.myVelib.DockingStation;


import fr.cs.Group13.myVelib.Cards.PricingVisitor;

import java.util.ArrayList;

public class PlusStation implements DockingStation {
    private int id;
    private double [] gpsCord;
    private StationStatus status;
    private ArrayList<ParkingSlot> parkingSlotArraylist;
    private int numberOfSlots;
    private int numberOfVacantSlots;

    public PlusStation() {
        StationIdGenerator instance = StationIdGenerator.getInstance();
        this.id = instance.getNextStationID();
    }
    public double accept(PricingVisitor visitor){
        return visitor.visit(this);
    }
}
