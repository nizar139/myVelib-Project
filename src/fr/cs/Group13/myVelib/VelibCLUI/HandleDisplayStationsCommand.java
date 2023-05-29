package fr.cs.Group13.myVelib.VelibCLUI;

import fr.cs.Group13.myVelib.DockingStation.DockingStation;
import fr.cs.Group13.myVelib.System.VelibGlobal;
import fr.cs.Group13.myVelib.System.VelibSystem;

import java.util.ArrayList;

public class HandleDisplayStationsCommand {
    public static void handleDisplayStations(String[] args){
        if (args.length !=1){
            throw new IllegalStateException("Invalid number of arguments. Usage: displayStations <velibnetworkName>");
        }
        VelibGlobal instance = VelibGlobal.getInstance();
        VelibSystem system = instance.searchSystemByName(args[0]);
        ArrayList<DockingStation> stations = system.getListOfStations();
        System.out.println("There are "+stations.size()+" station in "+args[0]+" Velib Network");
        int i = 1;
        for (DockingStation s:stations){
            System.out.println("\tstation "+i+" :"+s);
            i++;
        }
    }
}
