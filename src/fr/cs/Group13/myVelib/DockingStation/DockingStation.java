package fr.cs.Group13.myVelib.DockingStation;

import fr.cs.Group13.myVelib.Cards.PricingVisitor;

import java.util.ArrayList;

public abstract class DockingStation {
    private int id;
    private double[] gpsCord;
    private StationStatus status;
    private ArrayList<ParkingSlot> parkingSlotArraylist;
    private int numberOfSlots;
    private int numberOfElectricalBikes;
    private int numberOfVacantSlots;

    public int getNumberOfElectricalBikes() {
        return numberOfElectricalBikes;
    }
    public void setNumberOfElectricalBikes(int numberOfElectricalBikes) {
        this.numberOfElectricalBikes = numberOfElectricalBikes;
    }

    public int getNumberOfVacantSlots() {
        return numberOfVacantSlots;
    }

    public void setNumberOfVacantSlots(int numberOfVacantSlots) {
        this.numberOfVacantSlots = numberOfVacantSlots;
    }

    public DockingStation() {
        StationIdGenerator instance = StationIdGenerator.getInstance();
        this.id = instance.getNextStationID();
    }
    public DockingStation(double[] gpsCord, int numberOfSlots, int numberOfVacantSlots) {
        //create station, generate slots, and bikes
        StationIdGenerator instance = StationIdGenerator.getInstance();
        this.id = instance.getNextStationID();
    }
    public abstract double accept(PricingVisitor visitor);
}
