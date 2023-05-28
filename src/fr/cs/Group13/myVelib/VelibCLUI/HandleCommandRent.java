package fr.cs.Group13.myVelib.VelibCLUI;

import fr.cs.Group13.myVelib.Bicycle.Bicycle;
import fr.cs.Group13.myVelib.Bicycle.ElectricalBicycle;
import fr.cs.Group13.myVelib.Bicycle.MechanicalBicycle;
import fr.cs.Group13.myVelib.DockingStation.DockingStation;
import fr.cs.Group13.myVelib.DockingStation.StationStatus;
import fr.cs.Group13.myVelib.System.VelibGlobal;
import fr.cs.Group13.myVelib.System.VelibSystem;
import fr.cs.Group13.myVelib.User.User;

public class HandleCommandRent {
    public static void HandleRent(String[] args, int bikeType){
        VelibGlobal instance = VelibGlobal.getInstance();
        if (args.length!=2){
            System.out.println("Invalid number of arguments. Usage : rentBike <userId> <stationId>");
            return;
        }
        int userId = Integer.parseInt(args[0]);
        int stationId = Integer.parseInt(args[0]);
        Object[] temp = instance.searchSystemByUserId(userId, stationId);
        VelibSystem velibSystem = (VelibSystem) temp[0];
        User user = (User) temp[1];
        DockingStation station = velibSystem.searchStationById(stationId);
        if (station.getStatus()== StationStatus.OFFLINE){
            throw new RuntimeException("Station is offline");
        }
        Bicycle bike;
        switch (bikeType){
            case 0 :
                bike = Bicycle.findBikeAtStation(station);
                break;
            case 1 :
                bike = MechanicalBicycle.findBikeAtStation(station);
                break;
            case 3 :
                bike = ElectricalBicycle.findBikeAtStation(station);
                break;
            default :
                throw new RuntimeException("invalid number for bikeType");
        }
        user.rentBicycle(bike);
    }
}
