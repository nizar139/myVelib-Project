package fr.cs.Group13.myVelib.VelibCLUI;

import fr.cs.Group13.myVelib.System.VelibGlobal;
import fr.cs.Group13.myVelib.System.VelibSystem;

public class HandleSortStationCommand {
    public static void handleSortStation(String[] args){
        if (args.length !=2){
            throw new IllegalStateException("Invalid number of arguments. Usage: displayUser <velibnetworkName> <userID>");
        }
        VelibGlobal instance = VelibGlobal.getInstance();
        String VelibNetworkName = args[0];
        String sortingPolicy = args[1];
        VelibSystem system = instance.searchSystemByName(VelibNetworkName);
        if (sortingPolicy.equalsIgnoreCase("leastOccupiedStation")){
            system.leastOccupiedSort();
        } else if (sortingPolicy.equalsIgnoreCase("mostusedstation")) {
            system.mostUsedSort();
        }else{
            throw new RuntimeException("There is no such sorting policy.")
        }
    }
}
