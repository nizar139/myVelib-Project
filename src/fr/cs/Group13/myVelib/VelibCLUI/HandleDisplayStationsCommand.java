package fr.cs.Group13.myVelib.VelibCLUI;

import fr.cs.Group13.myVelib.DockingStation.DockingStation;
import fr.cs.Group13.myVelib.System.VelibGlobal;
import fr.cs.Group13.myVelib.System.VelibSystem;

import java.util.ArrayList;

public class HandleDisplayStationsCommand {
    public static void handleDisplayStations(String[] args){
        VelibGlobal instance = VelibGlobal.getInstance();
        VelibSystem system = instance.searchSystemByName(args[0]);
        ArrayList<DockingStation> stations = system.getListOfStations();
        System.out.println("There are "+stations.size()+" station");
        for (DockingStation s:stations){
            System.out.println(s);
        }
    }
}
