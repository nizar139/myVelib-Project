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
import fr.cs.Group13.myVelib.System.VelibSystem;

import java.util.ArrayList;
import java.util.Arrays;

public abstract class DockingStation {
    private int id;
    private double[] gpsCord;
    private VelibSystem vlibSys; // Vlib system in which the station is registered
    private StationStatus status = StationStatus.ONSERVICE;
    private ArrayList<ParkingSlot> parkingSlotArraylist;
    private int numberOfSlots;
    private int numberOfElectricalBikes;
    private int numberOfVacantSlots;
    private int totalRents;
    private int totalReturns;

    /**
     * Increments the total number of rents for the station.
     */
    public void incrementRents(){
        this.totalRents ++;
    }
    /**
     * Increments the total number of returns for the station.
     */
    public void incrementReturns(){
        this.totalReturns ++;
    }

    /**
     * returns the unique identifier of a station
     *
     * @return int
     */
    public int getId() {
        return id;
    }

    /**
     * Returns the total number of rent operations for the station.
     *
     * @return The total number of rent operations.
     */
    public int getTotalRents() {
        return totalRents;
    }

    /**
     * Returns the total number of return operations for the station.
     *
     * @return The total number of return operations.
     */
    public int getTotalReturns() {
        return totalReturns;
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
     * Constructs a DockingStation object with the specified parameters, automatically creates ParkingSlot and Bicycle objects
     *
     * @param gpsCord                The GPS coordinates of the docking station.
     * @param numberOfSlots          The total number of slots in the docking station.
     * @param numberOfVacantSlots    The number of initially vacant slots in the docking station.
     * @param numberOfElectricalBikes The number of initially available electrical bikes in the docking station.
     */
    public DockingStation(VelibSystem vlibSys, double[] gpsCord, int numberOfSlots, int numberOfVacantSlots, int numberOfElectricalBikes) {
        //create station, generate slots, and bikes
        StationIdGenerator instance = StationIdGenerator.getInstance();
        this.id = instance.getNextStationID();
        this.vlibSys = vlibSys;
        this.numberOfSlots = numberOfSlots;
        this.numberOfVacantSlots = numberOfVacantSlots;
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

    /**
     * returns the list of all the Parking Slots in the station
     *
     * @return
     */
    public ArrayList<ParkingSlot> getParkingSlotArraylist() {
        return parkingSlotArraylist;
    }

    /**
     * Sets the list of parking slots for the station.
     *
     * @param parkingSlotArraylist the list of parking slots to set
     */
    public void setParkingSlotArraylist(ArrayList<ParkingSlot> parkingSlotArraylist) {
        this.parkingSlotArraylist = parkingSlotArraylist;
    }

    /**
     * Sets the gpsCoordinate the docking station.
     *
     * @param gpsCord The gps coordinate of the docking station.
     */
    public void setGpsCord(double[] gpsCord) {
        this.gpsCord = gpsCord;
    }

    /**
     * Sets the status of the station.
     *
     * @param status to set
     */
    public void setStatus(StationStatus status) {
        this.status = status;
    }

    /**
     * Returns an empty parking slot at the station.
     *
     * @return an empty parking slot
     * @throws RuntimeException if no empty slot is found at the station
     */
    public ParkingSlot getEmptySlot(){
        if (this.numberOfVacantSlots>0){
            for (ParkingSlot slot:this.parkingSlotArraylist){
                if (!(slot.isOccupied())){
                    return slot;
                }
            }
        }
        throw new RuntimeException("No slot found at the station");
    }

    /**
     * Returns a string representation of the object.
     *
     * @return a string representation of the object
     */
    @Override
    public String toString() {
        String s = "{" + this.getStatus() + " "+this.getClass().getSimpleName()+" " + id + ", situated at " + Arrays.toString(gpsCord) + "}";
        return s;
    }

    /**
     * Prints the balance information of the station.
     */
    public void getBalance() {
        System.out.println("Station " + this.id +": \r\n\t total number of rent operations : "+this.totalRents+" \r\n\t total number of return operation : "+this.totalReturns);
    }

    /**
     * Returns a string representation of the docking station's information.
     *
     * @return a string containing the docking station's details
     */
    public  String displayInfos(){
        return "DockingStation:\n" +
                "\tStation Type =            " + this.getClass().getSimpleName()+
                "\n\tid =                      " + id +
                "\n\tgpsCord =                 " + Arrays.toString(gpsCord) +
                "\n\tstatus =                  " + status +
                "\n\tnumberOfSlots =           " + numberOfSlots +
                "\n\tnumberOfElectricalBikes = " + numberOfElectricalBikes +
                "\n\tnumberOfMechanicalBikes = " + (numberOfSlots-numberOfElectricalBikes-numberOfVacantSlots) ;
    }

    /**
     * Returns a string representation of the docking station's statistics.
     *
     * @return a string containing the docking station's statistics
     */

    public void display() {
        System.out.println(this.getId()+" Station statistics :");
        System.out.println("\tTotal Number of rents Operations   : "+this.totalRents);
        System.out.println("\tTotal Number of returns Operations : "+this.totalReturns);
    }

}
