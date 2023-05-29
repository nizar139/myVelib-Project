package fr.cs.Group13.myVelib.VelibCLUI;

import fr.cs.Group13.myVelib.DockingStation.DockingStation;
import fr.cs.Group13.myVelib.DockingStation.ParkingSlot;
import fr.cs.Group13.myVelib.DockingStation.StationStatus;
import fr.cs.Group13.myVelib.System.VelibGlobal;
import fr.cs.Group13.myVelib.System.VelibSystem;
import fr.cs.Group13.myVelib.User.User;

/**
 * The HandleReturnBikeCommand class provides a static method to handle the "return" command for returning a bike.
 * It performs the necessary operations to return a bike based on the specified arguments.
 */
public class HandleReturnBikeCommand {
    /**
     * Handles the "return" command for returning a bike.
     *
     * @param args     An array of strings containing the command arguments.
     */
    public static void HandleReturn(String[] args){
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
            double charge = user.returnBicycle(slot, duration);
            System.out.println("Return Operation done.\r\nYour total charge is : "+charge+"\r\nThank you for using our service");

        } else {
            double gpsX = Double.parseDouble(args[2]);
            double gpsY = Double.parseDouble(args[3]);
            double[] gpsCord = {gpsX,gpsY};
            user.setGpsCord(gpsCord);
            double charge = user.returnBicycle(duration);
            System.out.println("Return Operation done.\r\nYour total charge is : "+charge+"\r\nThank you for using our service");
        }
    }
}
