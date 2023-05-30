/**
 * The HandleDisplayStationInfoCommand class provides a command handler for displaying information about a specific station.
 */
package fr.cs.group13.myVelib.core.velibclui;

import fr.cs.group13.myVelib.core.dockingstation.DockingStation;
import fr.cs.group13.myVelib.core.system.VelibGlobal;

public class HandleDisplayStationInfoCommand {
    /**
     * Handles the displayStationInfo command.
     * Prints detailed information about the station with the specified ID.
     *
     * @param args The command arguments containing the station ID.
     * @throws InvalidArgumentSizeException if the number of arguments is invalid.
     */
    public static void handleDisplayStationInfo(String[] args){
        if (args.length != 1) {
            throw new InvalidArgumentSizeException("Invalid number of arguments. Usage: displaystationinfo <stationID>");
        }
        VelibGlobal instance = VelibGlobal.getInstance();
        int stationId = Integer.parseInt(args[0]);
        DockingStation station = (DockingStation) instance.searchSystembyStationId(stationId)[1];
        System.out.println(station.displayInfos());
    }
}
