package fr.cs.Group13.myVelib.VelibCLUI;

import fr.cs.Group13.myVelib.DockingStation.DockingStation;
import fr.cs.Group13.myVelib.System.VelibGlobal;

public class HandleDisplayStationInfoCommand {
    public static void handleDisplayStationInfo(String[] args){
        VelibGlobal instance = VelibGlobal.getInstance();
        int stationId = Integer.parseInt(args[0]);
        DockingStation station = (DockingStation) instance.searchSystembyStationId(stationId)[1];
        System.out.println(station.displayInfos());
    }
}
