package fr.cs.group13.myVelib.core.planing;

import fr.cs.group13.myVelib.core.bicycle.Bicycle;
import fr.cs.group13.myVelib.core.dockingstation.DockingStation;

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
