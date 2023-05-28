package fr.cs.Group13.myVelib.VelibCLUI;

import fr.cs.Group13.myVelib.Bicycle.Bicycle;
import fr.cs.Group13.myVelib.Bicycle.ElectricalBicycle;
import fr.cs.Group13.myVelib.Bicycle.MechanicalBicycle;
import fr.cs.Group13.myVelib.DockingStation.DockingStation;
import fr.cs.Group13.myVelib.DockingStation.ParkingSlot;
import fr.cs.Group13.myVelib.DockingStation.StationStatus;
import fr.cs.Group13.myVelib.System.VelibGlobal;
import fr.cs.Group13.myVelib.System.VelibSystem;
import fr.cs.Group13.myVelib.User.User;

public class HandleReturnBikeCommand {
    public static void HandleReturn(String[] args, int bikeType){
        VelibGlobal instance = VelibGlobal.getInstance();
        if (args.length!=3 && args.length!=4){
            System.out.println("Invalid number of arguments. Usage : ");
            return;
        }
        int userId = Integer.parseInt(args[0]);
        double duration = Double.parseDouble(args[1]);
        Object[] temp = instance.searchSystemByUserId(userId);
        VelibSystem velibSystem = (VelibSystem) temp[0];
        User user = (User) temp[1];
        if (args.length==3){
            int stationId = Integer.parseInt(args[1]);
            DockingStation station = velibSystem.searchStationById(stationId);
            if (station.getStatus()== StationStatus.OFFLINE){
                throw new RuntimeException("Station is offline, cannot return Here");
            }
            ParkingSlot slot = station.getEmptySlot();
            user.returnBicycle(slot, duration);

        } else {
            double gpsX = Double.parseDouble(args[2]);
            double gpsY = Double.parseDouble(args[3]);
            double[] gpsCord = {gpsX,gpsY};
            user.setGpsCord(gpsCord);
            user.returnBicycle(duration);
        }
    }
}
