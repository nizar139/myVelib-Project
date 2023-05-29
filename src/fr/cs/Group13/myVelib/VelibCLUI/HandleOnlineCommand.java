package fr.cs.Group13.myVelib.VelibCLUI;

import fr.cs.Group13.myVelib.System.VelibGlobal;

/**
 * The HandleOnlineCommand class provides a static method to handle the "online" command for setting a station online.
 * It performs the necessary operations to set the specified station in the given Velib network online.
 */
public class HandleOnlineCommand {
    /**
     * Handles the "online" command for setting a station online.
     *
     * @param args An array of strings containing the command arguments.
     */
    public static void handleOnline(String[] args){
        VelibGlobal instance = VelibGlobal.getInstance();
        if (args.length != 2){
            System.out.print("Invalid number of arguments. Usage : online <velibNetworkName> <stationId>");
            return;
        }
        String velibNetworkName = args[0];
        int stationId = Integer.parseInt(args[1]);
        instance.setOnline(velibNetworkName, stationId);
        System.out.println("changed station "+stationId+" status to online");
    }
}
