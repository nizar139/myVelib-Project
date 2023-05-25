package fr.cs.Group13.myVelib.Planing;

import fr.cs.Group13.myVelib.Bicycle.Bicycle;
import fr.cs.Group13.myVelib.DockingStation.DockingStation;
import fr.cs.Group13.myVelib.DockingStation.StationStatus;

import java.util.ArrayList;

public class PreferStreetBike extends RidesPlaning{

    @Override
    public String getYourPlan(ArrayList<DockingStation> stations, ArrayList<Bicycle> bikes, double[] startingGPS, double[] endingGPS) {
        Bicycle nearestBike = null;
        double minStartingDistance = Double.MAX_VALUE;

        for (Bicycle bike : bikes) {

            double startDistance = computeDistance(startingGPS, bike.getGpsCord());

            if (startDistance < minStartingDistance) {
                minStartingDistance = startDistance;
                nearestBike = bike;
            }
            }


        return "Nearest Bike to the starting point is : " + nearestBike;
    }
}
