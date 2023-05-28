package fr.cs.Group13.myVelib.VelibCLUI;

import fr.cs.Group13.myVelib.System.VelibGlobal;

public class HandleOfflineCommand {
    public static void handleOffline(String[] args){
        VelibGlobal instance = VelibGlobal.getInstance();
        if (args.length != 2){
            System.out.print("Invalid number of arguments. Usage : online <velibNetworkName> <stationId>");
            return;
        }
        String velibNetworkName = args[0];
        int stationId = Integer.parseInt(args[1]);
        instance.setOffline(velibNetworkName, stationId);
        System.out.println("changed station "+stationId+" status to offline");
    }
}
