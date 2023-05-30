/**
 * RidesPlaning is an abstract class that represents the planning of rides.
 * It contains methods for calculating the distance between two GPS coordinates
 * and for getting a plan given a list of DockingStation, starting GPS, and ending GPS.
 */
package fr.cs.group13.myVelib.core.planing;

import fr.cs.group13.myVelib.core.bicycle.Bicycle;
import fr.cs.group13.myVelib.core.dockingstation.DockingStation;

import java.util.ArrayList;

public abstract class RidesPlaning {
    /**
     * This method calculates the Euclidean distance between two GPS coordinates.
     *
     * @param gps1 The first GPS coordinate. It is an array containing two elements: the latitude and the longitude.
     * @param gps2 The second GPS coordinate. It is an array containing two elements: the latitude and the longitude.
     * @return The Euclidean distance between the two given GPS coordinates.
     */
    public double computeDistance(double[] gps1, double[] gps2) {
        return Math.sqrt(Math.pow((gps1[0] - gps2[0]), 2) + Math.pow((gps1[1] - gps2[1]), 2));
    }

    // a reverifier les parametres!
    /**
     * This method is responsible for generating a plan for the ride.
     * The details of how this plan is generated is left to the concrete classes that extend this abstract class.
     *
     * @param stations An ArrayList of DockingStation that represent the docking stations in the network.
     * @param startingGPS The starting GPS coordinates of the ride.
     * @param endingGPS The ending GPS coordinates of the ride.
     * @return A string representing the ride plan.
     */
    public abstract String getYourPlan(ArrayList<DockingStation> stations, ArrayList<Bicycle> bikes, double[] startingGPS, double[] endingGPS);
}
