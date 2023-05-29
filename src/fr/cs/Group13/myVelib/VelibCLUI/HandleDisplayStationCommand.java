package fr.cs.Group13.myVelib.VelibCLUI;

import fr.cs.Group13.myVelib.DockingStation.DockingStation;
import fr.cs.Group13.myVelib.System.VelibGlobal;
import fr.cs.Group13.myVelib.System.VelibSystem;

public class HandleDisplayStationCommand {
    public static void handleDisplayStation(String[] args){
        if (args.length !=2){
            throw new IllegalStateException("Invalid number of arguments. Usage: displayStation <velibnetworkName> <stationID>;");
        }
        VelibGlobal instance = VelibGlobal.getInstance();
        String VelibNetworkName = args[0];
        int stationId = Integer.parseInt(args[1]);
        VelibSystem system = instance.searchSystemByName(VelibNetworkName);
        DockingStation station = system.searchStationById(stationId);
        station.display();
    }
}
