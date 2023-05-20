package fr.cs.Group13.myVelib.DockingStation;

import java.util.ArrayList;

public class RegularStation implements DockingStation{
    private int id;
    private double [] gpsCord;
    private StationStatus status;
    private ArrayList<ParkingSlot> parkingSlotArraylist;
    private int numberOfSlots;
    private int numberOfVacantSlots;

    public RegularStation() {
        StationIdGenerator instance = StationIdGenerator.getInstance();
        this.id = instance.getNextStationID();
    }
}
