/**
 * The DockingStation class represents a docking station in the system.
 * It manages parking slots and bicycles.
 * Concrete subclasses must implement the accept() method to accept a PricingVisitor.
 *
 * @author Oussama and Nizar
 */
package fr.cs.Group13.myVelib.DockingStation;

import fr.cs.Group13.myVelib.Bicycle.ElectricalBicycle;
import fr.cs.Group13.myVelib.Cards.PricingVisitor;

import java.util.ArrayList;
import java.util.Arrays;

public abstract class DockingStation {
    private int id;
    private double[] gpsCord;
    private StationStatus status = StationStatus.ONSERVICE;
    private ArrayList<ParkingSlot> parkingSlotArraylist;
    private int numberOfSlots;
    private int numberOfElectricalBikes;
    private int numberOfVacantSlots;
    private int totalRents;
    private int totalReturns;

    public void incrementRents(){
        this.totalRents ++;
    }
    public void incrementReturns(){
        this.totalReturns ++;
    }


    /**
     * Returns the number of electrical bikes in the docking station.
     *
     * @return The number of electrical bikes.
     */
    public int getNumberOfElectricalBikes() {
        return numberOfElectricalBikes;
    }

    /**
     * Sets the number of electrical bikes in the docking station.
     *
     * @param numberOfElectricalBikes The number of electrical bikes.
     */
    public void setNumberOfElectricalBikes(int numberOfElectricalBikes) {
        this.numberOfElectricalBikes = numberOfElectricalBikes;
    }

    /**
     * Returns the number of vacant slots in the docking station.
     *
     * @return The number of vacant slots.
     */
    public int getNumberOfVacantSlots() {
        return numberOfVacantSlots;
    }

    /**
     * Sets the number of vacant slots in the docking station.
     *
     * @param numberOfVacantSlots The number of vacant slots.
     */
    public void setNumberOfVacantSlots(int numberOfVacantSlots) {
        this.numberOfVacantSlots = numberOfVacantSlots;
    }

    /**
     * Returns the number of mechanical bikes in the docking station.
     *
     * @return The number of mechanical bikes.
     */
    public int getNumberOfMechanicalBikes(){
        int i = this.numberOfSlots - this.numberOfVacantSlots -this.numberOfElectricalBikes;
        return i;
    }

    /**
     * Constructs a DockingStation object with a unique ID.
     */
    public DockingStation() {
        StationIdGenerator instance = StationIdGenerator.getInstance();
        this.id = instance.getNextStationID();
    }

    /**
     * Constructs a DockingStation object with the specified parameters.
     *
     * @param gpsCord                The GPS coordinates of the docking station.
     * @param numberOfSlots          The total number of slots in the docking station.
     * @param numberOfVacantSlots    The number of initially vacant slots in the docking station.
     * @param numberOfElectricalBikes The number of initially available electrical bikes in the docking station.
     */
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

    /**
     * Method to be implemented by concrete subclasses to accept a PricingVisitor.
     *
     * @param visitor The PricingVisitor object.
     * @return a double.
     */
    public abstract double accept(PricingVisitor visitor);

    /**
     * Returns the status of the docking station.
     *
     * @return The status of the docking station.
     */
    public StationStatus getStatus() {
        return status;
    }

    /**
     * Returns the number of slots in the docking station.
     *
     * @return The number of slots.
     */
    public int getNumberOfSlots() {
        return numberOfSlots;
    }

    /**
     * Returns the GPS coordinates of the docking station.
     *
     * @return The GPS coordinates.
     */
    public double[] getGpsCord() {
        return gpsCord;
    }
    public void getBalance() {
        System.out.println("Station " + this.id +": \r\n\t total number of rent operations : "+this.totalRents+" \r\n\t total number of return operation : "+this.totalReturns);
    }

    @Override
    public String toString() {
        return "Station " + id + ", situated" + Arrays.toString(gpsCord);
    }
}
