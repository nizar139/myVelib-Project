package fr.cs.Group13.myVelib.VelibCLUI;

import fr.cs.Group13.myVelib.System.VelibGlobal;

public class HandleOnlineCommand {
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
