package fr.cs.Group13.myVelib.DockingStation;

import fr.cs.Group13.myVelib.Bicycle.ElectricalBicycle;
import fr.cs.Group13.myVelib.Cards.PricingVisitor;

import java.util.ArrayList;

public abstract class DockingStation {
    private int id;
    private double[] gpsCord;
    private StationStatus status = StationStatus.ONSERVICE;
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
    public int getNumberOfMechanicalBikes(){
        int i = this.numberOfSlots - this.numberOfVacantSlots -this.numberOfElectricalBikes;
        return i;
    }

    public DockingStation() {
        StationIdGenerator instance = StationIdGenerator.getInstance();
        this.id = instance.getNextStationID();
    }
    public DockingStation(double[] gpsCord, int numberOfSlots, int numberOfVacantSlots, int numberOfElectricalBikes) {
        //create station, generate slots, and bikes
        StationIdGenerator instance = StationIdGenerator.getInstance();
        this.id = instance.getNextStationID();
        this.numberOfSlots = numberOfSlots;
        this.numberOfVacantSlots = numberOfSlots;
        this.numberOfElectricalBikes = numberOfElectricalBikes;
        this.gpsCord = gpsCord;
        this.parkingSlotArraylist = new ArrayList<ParkingSlot>(numberOfSlots);
        for (int i = 0; i<numberOfElectricalBikes; i++) {
            ElectricalBicycle b = new ElectricalBicycle(gpsCord);
            ParkingSlot slot = new ParkingSlot(this, b);
            b.addToSlot(slot);
            this.parkingSlotArraylist.add(slot);
        }
        for (int i = 0; i< this.getNumberOfMechanicalBikes(); i++) {
            ElectricalBicycle b = new ElectricalBicycle(gpsCord);
            ParkingSlot slot = new ParkingSlot(this, b);
            b.addToSlot(slot);
            this.parkingSlotArraylist.add(slot);
        }
        for (int i = 0; i<numberOfVacantSlots; i++) {
            ParkingSlot slot = new ParkingSlot(this);
            this.parkingSlotArraylist.add(slot);
        }
    }
    public abstract double accept(PricingVisitor visitor);

    public StationStatus getStatus() {
        return status;
    }

    public int getNumberOfSlots() {
        return numberOfSlots;
    }

    public double[] getGpsCord() {
        return gpsCord;
    }

}
