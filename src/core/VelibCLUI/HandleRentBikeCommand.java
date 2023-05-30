package core.VelibCLUI;

import core.DockingStation.DockingStation;
import core.System.VelibGlobal;
import core.User.User;
import core.Bicycle.Bicycle;
import core.Bicycle.ElectricalBicycle;
import core.Bicycle.MechanicalBicycle;
import core.DockingStation.StationStatus;
import core.System.VelibSystem;

/**
 * The HandleRentBikeCommand class provides a static method to handle the "rentBike" command for renting a bike.
 * It performs the necessary operations to rent a bike for the specified user from the given station.
 */
public class HandleRentBikeCommand {
    /**
     * Handles the "rentBike" command for renting a bike.
     *
     * @param args     An array of strings containing the command arguments.
     * @throws InvalidArgumentSizeException if the number of arguments is invalid.
     */
    public static void HandleRent(String[] args){
        VelibGlobal instance = VelibGlobal.getInstance();
        if (args.length!=3 && args.length!=4){
            throw new InvalidArgumentSizeException("Invalid number of arguments. Usage : <userID> <biketype> <stationID> or <userID> <biketype> <GPS_x> <GPS_y>");
        }
        int bikeType = Integer.parseInt(args[1]);
        int userId = Integer.parseInt(args[0]);
        Object[] temp = instance.searchSystemByUserId(userId);
        VelibSystem velibSystem = (VelibSystem) temp[0];
        User user = (User) temp[1];
        Bicycle bike;
        if (args.length==3){
            int stationId = Integer.parseInt(args[2]);
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
                case 2:
                    bike = ElectricalBicycle.findBikeAtStation(station);
                    break;
                default:
                    throw new RuntimeException("invalid number for bikeType");
            }
        } else {
            double gpsX = Double.parseDouble(args[2]);
            double gpsY = Double.parseDouble(args[3]);
            double[] gpsCord = {gpsX,gpsY};
            bike = velibSystem.searchBicycleByGPS(gpsCord);
        }
        user.rentBicycle(bike);
        System.out.println("Bike Successfully rented by "+user.getFirstName());
    }
}
