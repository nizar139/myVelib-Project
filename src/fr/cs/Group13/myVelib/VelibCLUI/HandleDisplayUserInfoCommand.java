package fr.cs.Group13.myVelib.VelibCLUI;

import fr.cs.Group13.myVelib.DockingStation.DockingStation;
import fr.cs.Group13.myVelib.System.VelibGlobal;
import fr.cs.Group13.myVelib.User.User;

public class HandleDisplayUserInfoCommand {
    public static void handleDisplayUserInfo(String[] args){
        if (args.length != 1) {
            throw new IllegalStateException("Invalid number of arguments. Usage: displayUserInfo <userID>");
        }
        VelibGlobal instance = VelibGlobal.getInstance();
        int stationId = Integer.parseInt(args[0]);
        User user = (User) instance.searchSystemByUserId(stationId)[1];
        System.out.println(user);
    }
}
