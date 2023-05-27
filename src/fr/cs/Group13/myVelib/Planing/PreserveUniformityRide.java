/**
 * This class extends the RidesPlaning abstract class. It is designed to help a user plan a ride
 * that aims at preserving the uniformity of distribution of both types of bikes (mechanical and electrical) across stations.
 * This strategy attempts to balance the number of bikes and vacant slots across different stations.
 */
package fr.cs.Group13.myVelib.Planing;

import fr.cs.Group13.myVelib.Bicycle.Bicycle;
import fr.cs.Group13.myVelib.DockingStation.DockingStation;
import fr.cs.Group13.myVelib.DockingStation.StationStatus;

import java.util.ArrayList;

public class PreserveUniformityRide extends RidesPlaning{
    /**
     * The method plans a ride considering the uniformity of both types of bikes (mechanical and electrical) across stations.
     * It first finds the nearest station with available bikes of any type and a station with vacant slots.
     * Then it checks for other stations within 105% of the distance of the previously found stations
     * that have a better number of bikes or vacant slots and picks those as starting and ending stations.
     *
     * @param stations ArrayList of DockingStation objects representing all available stations.
     * @param startingGPS A double array representing the GPS coordinates where the user starts.
     * @param endingGPS A double array representing the GPS coordinates where the user wants to end.
     * @return A string describing the best starting station with at least one bike of any type and the best ending station.
     */
    @Override
    public String getYourPlan(ArrayList<DockingStation> stations, ArrayList<Bicycle> bikes, double[] startingGPS, double[] endingGPS) {
        DockingStation nearestStartingStation = null;
        DockingStation bestStartingStation = null;
        DockingStation nearestEndingStation = null;
        DockingStation bestEndingStation = null;
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
        for (DockingStation station : stations){
            if (station.getStatus() == StationStatus.ONSERVICE) {
                double startDistance = computeDistance(startingGPS, station.getGpsCord());
                double endDistance = computeDistance(endingGPS, station.getGpsCord());

                if (startDistance <= 1.05 * minStartingDistance && (station.getNumberOfSlots() -
                        station.getNumberOfVacantSlots() > nearestStartingStation.getNumberOfSlots() -
                        nearestEndingStation.getNumberOfVacantSlots())) {
                    bestStartingStation = station;
                }
                if (endDistance <= 1.05 * minEndingDistance && (station.getNumberOfVacantSlots() >
                        nearestEndingStation.getNumberOfVacantSlots())) {
                    bestEndingStation = station;
                }
            }
        }

        return "Nearest starting station : " + (bestStartingStation != null ? bestStartingStation : nearestStartingStation) + ", Nearest ending station: " + (bestEndingStation != null ? bestEndingStation : nearestEndingStation);

    }
}
