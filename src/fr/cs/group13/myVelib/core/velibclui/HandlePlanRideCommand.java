package fr.cs.group13.myVelib.core.velibclui;

import fr.cs.group13.myVelib.core.planing.RideType;
import fr.cs.group13.myVelib.core.planing.RidesPlaning;
import fr.cs.group13.myVelib.core.system.VelibGlobal;
import fr.cs.group13.myVelib.core.system.VelibSystem;

public class HandlePlanRideCommand {
    public static void handlePlanRide(String[] args){
        if (args.length !=6){
            throw new InvalidArgumentSizeException("Invalid number of arguments. Usage: planRide <userID> <planningstrategy> <startingGPS_x> <startingGPS_y> <endingGPS_x> <endingGPS_y>");
        }
        int userid = Integer.parseInt(args[0]);
        String ridePlanningType = args[1];
        double starting_x = Double.parseDouble(args[2]);
        double starting_y = Double.parseDouble(args[3]);
        double ending_x = Double.parseDouble(args[4]);
        double ending_y = Double.parseDouble(args[5]);
        VelibGlobal instance = VelibGlobal.getInstance();
        VelibSystem system = (VelibSystem) instance.searchSystemByUserId(userid)[0];
        RideType ridetype = new RideType();
        RidesPlaning planner = ridetype.getYourStrat(ridePlanningType);
        System.out.println(planner.getYourPlan(system.getListOfStations(),system.getListOfStreetBikes(),new double[]{starting_x,starting_y}, new double[]{ending_x,ending_y}));
    }
}
