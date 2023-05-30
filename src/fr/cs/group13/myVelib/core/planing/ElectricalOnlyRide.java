/**
 * ElectricalOnlyRide is a subclass of RidesPlaning.
 * It represents a planning strategy where only electrical bicycles are considered.
 */
package fr.cs.group13.myVelib.core.planing;

import fr.cs.group13.myVelib.core.dockingstation.DockingStation;
import fr.cs.group13.myVelib.core.dockingstation.StationStatus;
import fr.cs.group13.myVelib.core.bicycle.Bicycle;

import java.util.ArrayList;

public class ElectricalOnlyRide extends RidesPlaning{
    /**
     * This method generates a plan for the ride considering only electrical bikes.
     * It finds the nearest DockingStation with at least one electrical bike for starting,
     * and the nearest DockingStation for ending.
     *
     * @param stations An ArrayList of DockingStation that represent the docking stations in the network.
     * @param startingGPS The starting GPS coordinates of the ride.
     * @param endingGPS The ending GPS coordinates of the ride.
     * @return A string representing the ride plan. It includes the nearest starting station with an electrical bike
     * and the nearest ending station with at least one free slot.
     */
    @Override
    public String getYourPlan(ArrayList<DockingStation> stations, ArrayList<Bicycle> bikes, double[] startingGPS, double[] endingGPS) {
        DockingStation nearestStartingStation = null;
        DockingStation nearestEndingStation = null;
        double minStartingDistance = Double.MAX_VALUE;
        double minEndingDistance = Double.MAX_VALUE;

        for (DockingStation station : stations) {
            if (station.getStatus() == StationStatus.ONSERVICE) {
                if (station.getNumberOfElectricalBikes() > 0) {
                    double startDistance = computeDistance(startingGPS, station.getGpsCord());
                    if (startDistance < minStartingDistance) {
                        minStartingDistance = startDistance;
                        nearestStartingStation = station;
                    }
                }
                if (station.getNumberOfVacantSlots() > 0) {
                    double endDistance = computeDistance(endingGPS, station.getGpsCord());
                    if (endDistance < minEndingDistance) {
                        minEndingDistance = endDistance;
                        nearestEndingStation = station;
                    }
                }
            }
        }

        return "Nearest starting station with at least one Electrical Bike: " + nearestStartingStation + ", Nearest ending station: " + nearestEndingStation;
    }
}
