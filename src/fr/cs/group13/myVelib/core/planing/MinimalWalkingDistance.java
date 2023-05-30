/**
 * MinimalWalkingDistance is a subclass of RidesPlaning.
 * It represents a classic planning strategy where all stations are considered.
 */
package fr.cs.group13.myVelib.core.planing;

import fr.cs.group13.myVelib.core.bicycle.Bicycle;
import fr.cs.group13.myVelib.core.dockingstation.DockingStation;
import fr.cs.group13.myVelib.core.dockingstation.StationStatus;

import java.util.ArrayList;

public class MinimalWalkingDistance extends RidesPlaning{
    /**
     * This method generates a plan for the ride considering all stations.
     * It finds the nearest DockingStation for starting and for ending.
     *
     * @param stations An ArrayList of DockingStation that represent the docking stations in the network.
     * @param startingGPS The starting GPS coordinates of the ride.
     * @param endingGPS The ending GPS coordinates of the ride.
     * @return A string representing the ride plan. It includes the nearest starting station
     * and the nearest ending station.
     */
    @Override
    // a reverifier les parametres!
    public String getYourPlan(ArrayList<DockingStation> stations, ArrayList<Bicycle> bikes, double[] startingGPS, double[] endingGPS) {
        DockingStation nearestStartingStation = null;
        DockingStation nearestEndingStation = null;
        double minStartingDistance = Double.MAX_VALUE;
        double minEndingDistance = Double.MAX_VALUE;

        for (DockingStation station : stations) {
            if (station.getStatus() == StationStatus.ONSERVICE) {
                if (station.getNumberOfSlots()>station.getNumberOfVacantSlots()) {
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

        return "Nearest starting station: " + nearestStartingStation + ", Nearest ending station: " + nearestEndingStation;
    }

}
