/**
 * The ParkingSlot class represents a parking slot within a docking station.
 * It can be either occupied by a bicycle or vacant.
 * Each parking slot is associated with a docking station.
 *
 * @author Oussama and Nizar
 */
package fr.cs.Group13.myVelib.DockingStation;

import fr.cs.Group13.myVelib.Bicycle.Bicycle;

public class ParkingSlot {
    private boolean occupied = false ;
    private Bicycle bicycle = null;
    private DockingStation station;

    /**
     * Constructor for a parking slot with an associated bicycle.
     *
     * @param station  The docking station to which the slot belongs.
     * @param bicycle  The bicycle occupying the slot.
     */
    public ParkingSlot(DockingStation station, Bicycle bicycle) {
        this.bicycle = bicycle;
        this.station = station;
        this.occupied = true;
    }

    /**
     * Constructor for a vacant parking slot.
     *
     * @param station  The docking station to which the slot belongs.
     */
    public ParkingSlot(DockingStation station) {
        this.station = station;
    }

    /**
     * Returns the docking station associated with the parking slot.
     *
     * @return The docking station.
     */
    public DockingStation getStation() {
        return station;
    }

    /**
     * Returns the status of the parking slot.
     *
     * @return boolean.
     */
    public boolean isOccupied() {
        return occupied;
    }

    /**
     * Returns the bicycle associated with the parking slot.
     *
     * @return The bicycle occupying the parking slot.
     */
    public Bicycle getBicycle() {
        return bicycle;
    }

    /**
     * Frees up the parking slot by removing the associated bicycle and updating the occupancy status.
     */
    public void freeSlot(){
        this.bicycle.setBikeCountFree(this);
        this.bicycle.removeFromSlot();
        this.occupied = false;
        this.bicycle = null;
    }

    /**
     * Puts a bicycle into the parking slot, updating the occupancy status and associating the bicycle with the slot.
     *
     * @param b The bicycle to be put into the slot.
     * @throws IllegalStateException if the slot is already occupied.
     */
    public void putBicycle(Bicycle b){
        if (this.occupied){
            throw new IllegalStateException("Slot is already occupied");
        }
        else {
            b.addToSlot(this);
            b.setBikeCountFill(this);
            this.occupied = true;
            this.bicycle = b;
        }
    }
}
