/**
 * This is an abstract class that represents a Bicycle in the system.
 * Each bicycle has a unique ID and a location represented by GPS coordinates.
 * A bicycle can be in a parking slot at a docking station or on the street.
 *
 * The "fromAStation" attribute indicates if a bicycle is from a docking station (1) or from the street (0).
 *
 * @author Oussama and Nizar
 */
package fr.cs.Group13.myVelib.Bicycle;

import fr.cs.Group13.myVelib.Cards.PricingVisitor;
import fr.cs.Group13.myVelib.DockingStation.ParkingSlot;

public abstract class Bicycle{
    private int id;
    private double[] gpsCord;
    private int fromAStation = 1;
    private ParkingSlot slot;

    /**
     * Default constructor that generates a unique ID for the bicycle.
     */
    public Bicycle() {
        BicycleIdGenerator instance = BicycleIdGenerator.getInstance();
        this.id = instance.getNextBicycleID();
    }

    /**
     * Constructor that takes GPS coordinates and generates a unique ID for the bicycle.
     * @param gpsCord the GPS coordinates of the bicycle.
     */
    public Bicycle(double[] gpsCord) {
        BicycleIdGenerator instance = BicycleIdGenerator.getInstance();
        this.id = instance.getNextBicycleID();
        this.gpsCord = gpsCord;
    }

    /**
     * Constructor that takes all the attributes other than ID, which is automatically generated
     * @param gpsCord
     * @param fromAStation
     * @param slot
     */
    public Bicycle(double[] gpsCord, int fromAStation, ParkingSlot slot) {
        BicycleIdGenerator instance = BicycleIdGenerator.getInstance();
        this.id = instance.getNextBicycleID();
        this.gpsCord = gpsCord;
        this.fromAStation = fromAStation;
        this.slot = slot;
    }

    /**
     * Gets the fromAStation attribute.
     * @return the value of fromAStation.
     */
    public int getFromAStation() {
        return fromAStation;
    }

    /**
     * Sets the fromAStation attribute.
     * @param fromAStation the value to set.
     */
    public void setFromAStation(int fromAStation) {
        this.fromAStation = fromAStation;
    }

    public void setGpsCord(double[] gpsCord) {
        this.gpsCord = gpsCord;
    }

    /**
     * Gets the gps coordinate of the Bicycle.
     * @return the gps coordinate.
     */
    public double[] getGpsCord() {
        return this.gpsCord;
    }

    /**
     * Gets the parking slot where the bicycle is.
     * @return the parking slot.
     */

    public ParkingSlot getSlot() {
        return slot;
    }

    public int getId() {
        return id;
    }

    /**
     * Adds the bicycle to a parking slot.
     * @param slot the parking slot to add the bicycle to.
     */
    public void addToSlot(ParkingSlot slot) {
        this.slot = slot;
        this.fromAStation = 1;
        this.gpsCord = slot.getStation().getGpsCord();
    }

    /**
     * Removes the bicycle from its parking slot.
     */
    public void removeFromSlot() {
        this.slot = null;
    }

    /**
     * Method to be implemented by concrete subclasses to accept a PricingVisitor.
     * @param visitor the PricingVisitor.
     * @return an array of doubles.
     */
    public abstract double[] accept(PricingVisitor visitor);

    /**
     * This method adjusts the necessary attributes when a bicycle is removed from a parking slot.
     * The exact changes depend on the specific subclass of bicycle, hence this method is meant to be overridden.
     * @param slot The ParkingSlot from which the bicycle is being removed.
     */
    public abstract void setBikeCountFree(ParkingSlot slot);

    /**
     * This method adjusts the necessary attributes when a bicycle is added to a parking slot.
     * The exact changes depend on the specific subclass of bicycle, hence this method is meant to be overridden.
     * @param slot The ParkingSlot to which the bicycle is being added.
     */
    public abstract void setBikeCountFill(ParkingSlot slot);


}
