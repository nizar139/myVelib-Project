package core.VelibCLUI;

import core.System.VelibGlobal;

/**
 * The HandleOfflineCommand class provides a static method to handle the "offline" command for setting a station offline.
 * It performs the necessary operations to set the specified station in the given Velib network offline.
 */
public class HandleOfflineCommand {
    /**
     * Handles the "offline" command for setting a station offline.
     *
     * @param args An array of strings containing the command arguments.
     *
     * @throws InvalidArgumentSizeException if the number of arguments is invalid.
     */
    public static void handleOffline(String[] args){
        VelibGlobal instance = VelibGlobal.getInstance();
        if (args.length != 2){
            throw new InvalidArgumentSizeException("Invalid number of arguments. Usage : online <velibNetworkName> <stationId>");
        }
        String velibNetworkName = args[0];
        int stationId = Integer.parseInt(args[1]);
        instance.setOffline(velibNetworkName, stationId);
        System.out.println("changed station "+stationId+" status to offline");
    }
}
