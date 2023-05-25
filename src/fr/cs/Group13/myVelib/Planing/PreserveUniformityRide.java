package fr.cs.Group13.myVelib.Planing;

import fr.cs.Group13.myVelib.DockingStation.DockingStation;
import fr.cs.Group13.myVelib.DockingStation.StationStatus;

import java.util.ArrayList;

public class PreserveUniformityRide extends RidesPlaning{

    @Override
    public String getYourPlan(ArrayList<DockingStation> stations, double[] startingGPS, double[] endingGPS) {
        DockingStation nearestStartingStation = null;
        DockingStation bestStartingStation = null;
        DockingStation nearestEndingStation = null;
        DockingStation bestEndingStation = null;
        double minStartingDistance = Double.MAX_VALUE;
        double minEndingDistance = Double.MAX_VALUE;

        for (DockingStation station : stations) {
            if (station.getStatus() == StationStatus.ONSERVICE) {
                double startDistance = computeDistance(startingGPS, station.getGpsCord());

                if (startDistance < minStartingDistance) {
                    minStartingDistance = startDistance;
                    nearestStartingStation = station;
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

                if (startDistance < 1.05 * minStartingDistance && (station.getNumberOfSlots() - station.getNumberOfVacantSlots()
                        > nearestStartingStation.getNumberOfSlots() - nearestEndingStation.getNumberOfVacantSlots())) {
                    nearestStartingStation = station;
                }

                if (endDistance < 1.05 * minEndingDistance && (station.getNumberOfVacantSlots() >
                        nearestEndingStation.getNumberOfVacantSlots())) {
                    nearestEndingStation = station;
                }
            }
        }

        return "Nearest starting station: " + nearestStartingStation + ", Nearest ending station: " + nearestEndingStation;
    }
}
