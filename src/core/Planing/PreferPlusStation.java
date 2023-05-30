/**
 * This class extends the RidesPlaning abstract class. Its purpose is to plan a ride
 * prefering PlusStation type docking stations as ending point, assuming they are available in the system.
 */
package core.Planing;

import core.Bicycle.Bicycle;
import core.DockingStation.DockingStation;
import core.DockingStation.PlusStation;
import core.DockingStation.StationStatus;

import java.util.ArrayList;

public class PreferPlusStation extends RidesPlaning{
    /**
     * This overridden method gets the plan for a ride based on the given parameters.
     * The algorithm will seek the nearest starting station and the nearest non-PlusStation ending station that has available slots.
     *
     * @param stations The list of all available docking stations.
     * @param startingGPS The GPS coordinates where the user starts.
     * @param endingGPS The GPS coordinates where the user wants to end.
     * @return A string describing the nearest starting station and the nearest PlusStation ending station.
     */
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
                if (station.getNumberOfVacantSlots() > 0 && (station instanceof PlusStation)) {
                    double endDistance = computeDistance(endingGPS, station.getGpsCord());
                    if (endDistance < minEndingDistance) {
                        minEndingDistance = endDistance;
                        nearestEndingStation = station;
                    }
                }
            }
        }

        return "Nearest starting station: " + nearestStartingStation + ", Nearest ending station prefering PlusStation: " + nearestEndingStation;
    }
}
