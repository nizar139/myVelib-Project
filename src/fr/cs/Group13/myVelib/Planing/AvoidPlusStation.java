/**
 * This class extends the RidesPlaning abstract class. Its purpose is to plan a ride
 * avoiding PlusStation type docking stations as ending point, assuming they are available in the system.
 */
package fr.cs.Group13.myVelib.Planing;

import fr.cs.Group13.myVelib.DockingStation.DockingStation;
import fr.cs.Group13.myVelib.DockingStation.PlusStation;
import fr.cs.Group13.myVelib.DockingStation.StationStatus;

import java.util.ArrayList;

public class AvoidPlusStation extends RidesPlaning{
    /**
     * This overridden method gets the plan for a ride based on the given parameters.
     * The algorithm will seek the nearest starting station and the nearest non-PlusStation ending station that has available slots.
     *
     * @param stations The list of all available docking stations.
     * @param startingGPS The GPS coordinates where the user starts.
     * @param endingGPS The GPS coordinates where the user wants to end.
     * @return A string describing the nearest starting station and the nearest non-PlusStation ending station.
     */
    @Override
    // a reverifier les parametres!

    public String getYourPlan(ArrayList<DockingStation> stations, double[] startingGPS, double[] endingGPS) {
        DockingStation nearestStartingStation = null;
        DockingStation nearestEndingStation = null;
        double minStartingDistance = Double.MAX_VALUE;
        double minEndingDistance = Double.MAX_VALUE;

        for (DockingStation station : stations) {
            if (station.getStatus() == StationStatus.ONSERVICE) {
                double startDistance = computeDistance(startingGPS, station.getGpsCord());

                if (startDistance < minStartingDistance) {
                    minStartingDistance = startDistance;
                    nearestStartingStation = station;
                }

                if (station.getNumberOfVacantSlots() > 0 && !(station instanceof PlusStation)) {
                    double endDistance = computeDistance(endingGPS, station.getGpsCord());
                    if (endDistance < minEndingDistance) {
                        minEndingDistance = endDistance;
                        nearestEndingStation = station;
                    }
                }
            }
        }

        return "Nearest starting station: " + nearestStartingStation + ", Nearest ending station avoiding PlusStation: " + nearestEndingStation;
    }
}
