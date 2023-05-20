package fr.cs.Group13.myVelib.DockingStation;

import java.util.ArrayList;

public class PlusStation {
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
}
