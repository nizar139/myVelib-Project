package fr.cs.Group13.myVelib.VelibCLUI;

import fr.cs.Group13.myVelib.Bicycle.Bicycle;
import fr.cs.Group13.myVelib.DockingStation.DockingStation;
import fr.cs.Group13.myVelib.System.VelibGlobal;
import fr.cs.Group13.myVelib.System.VelibSystem;
import fr.cs.Group13.myVelib.User.User;

public class HandleReturnBikeCommand {
    public static void handleReturnBike(String[] args){
        VelibGlobal instance = VelibGlobal.getInstance();
        if ()
        if (args.length == 3) {
            int userID = Integer.parseInt(args[0]);
            double gps_x = Double.parseDouble(args[1]);
            double gps_y = Double.parseDouble(args[2]);
            double gps_y = Double.parseDouble(args[2]);
            VelibSystem system = instance.getListOfVelibSystems().get(instance.getListOfVelibSystems().size()-1);
            Bicycle b = system.searchBicycleByGPS(new double[]{gps_x, gps_y});
            User user = system.searchUserById(userID);
            user.rentBicycle(b);
            System.out.println(user.getFirstName()+ " rented the bicycle "+b);
        }else if (args.length == 2){
            int userID = Integer.parseInt(args[0]);
            int stationId = Integer.parseInt(args[1]);
            VelibSystem system = instance.getListOfVelibSystems().get(instance.getListOfVelibSystems().size()-1);
            DockingStation station = system.searchStationById(stationId);

        }
    }
}
