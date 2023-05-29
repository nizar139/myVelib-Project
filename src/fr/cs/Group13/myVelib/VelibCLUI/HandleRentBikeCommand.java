package fr.cs.Group13.myVelib.VelibCLUI;

import fr.cs.Group13.myVelib.Bicycle.Bicycle;
import fr.cs.Group13.myVelib.Bicycle.ElectricalBicycle;
import fr.cs.Group13.myVelib.Bicycle.MechanicalBicycle;
import fr.cs.Group13.myVelib.DockingStation.DockingStation;
import fr.cs.Group13.myVelib.DockingStation.StationStatus;
import fr.cs.Group13.myVelib.System.VelibGlobal;
import fr.cs.Group13.myVelib.System.VelibSystem;
import fr.cs.Group13.myVelib.User.User;

/**
 * The HandleRentBikeCommand class provides a static method to handle the "rentBike" command for renting a bike.
 * It performs the necessary operations to rent a bike for the specified user from the given station.
 */
public class HandleRentBikeCommand {
    /**
     * Handles the "rentBike" command for renting a bike.
     *
     * @param args     An array of strings containing the command arguments.
     * @param bikeType The type of bike to rent (0 for any bike, 1 for mechanical bike, 2 for electrical bike).
     */
    public static void HandleRent(String[] args, int bikeType){
        VelibGlobal instance = VelibGlobal.getInstance();
        if (args.length!=2 && args.length!=3){
            System.out.println("Invalid number of arguments. Usage : rentBike <userId> <stationId>");
            return;
        }
        int userId = Integer.parseInt(args[0]);
        Object[] temp = instance.searchSystemByUserId(userId);
        VelibSystem velibSystem = (VelibSystem) temp[0];
        User user = (User) temp[1];
        Bicycle bike;
        if (args.length==2){
            int stationId = Integer.parseInt(args[1]);
            DockingStation station = velibSystem.searchStationById(stationId);
            if (station.getStatus()== StationStatus.OFFLINE){
                throw new RuntimeException("Station is offline");
            }
            switch (bikeType) {
                case 0:
                    bike = Bicycle.findBikeAtStation(station);
                    break;
                case 1:
                    bike = MechanicalBicycle.findBikeAtStation(station);
                    break;
                case 3:
                    bike = ElectricalBicycle.findBikeAtStation(station);
                    break;
                default:
                    throw new RuntimeException("invalid number for bikeType");
            }
        } else {
            double gpsX = Double.parseDouble(args[1]);
            double gpsY = Double.parseDouble(args[2]);
            double[] gpsCord = {gpsX,gpsY};
            bike = velibSystem.searchBicycleByGPS(gpsCord);
        }
        user.rentBicycle(bike);
        System.out.println("Bike Successfully rented by "+user.getFirstName());
    }
}
