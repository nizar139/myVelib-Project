/**
 * The HandleDisplayStationCommand class provides a command handler for displaying statistics of a specific station.
 */
package fr.cs.group13.myVelib.core.velibclui;

import fr.cs.group13.myVelib.core.dockingstation.DockingStation;
import fr.cs.group13.myVelib.core.system.VelibGlobal;
import fr.cs.group13.myVelib.core.system.VelibSystem;

public class HandleDisplayStationCommand {
    /**
     * Handles the displayStation command.
     * Prints statistics of station with the specified ID in the specified Velib network.
     *
     * @param args The command arguments containing the Velib network name and station ID.
     * @throws InvalidArgumentSizeException if the number of arguments is invalid.
     */
    public static void handleDisplayStation(String[] args){
        if (args.length !=2){
            throw new InvalidArgumentSizeException("Invalid number of arguments. Usage: displayStation <velibnetworkName> <stationID>;");
        }
        VelibGlobal instance = VelibGlobal.getInstance();
        String VelibNetworkName = args[0];
        int stationId = Integer.parseInt(args[1]);
        VelibSystem system = instance.searchSystemByName(VelibNetworkName);
        DockingStation station = system.searchStationById(stationId);
        station.display();
    }
}
