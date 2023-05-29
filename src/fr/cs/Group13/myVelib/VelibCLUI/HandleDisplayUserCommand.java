package fr.cs.Group13.myVelib.VelibCLUI;

import fr.cs.Group13.myVelib.System.VelibGlobal;
import fr.cs.Group13.myVelib.System.VelibSystem;
import fr.cs.Group13.myVelib.User.User;

public class HandleDisplayUserCommand {
    public static void handleDisplayUser(String[] args){
        if (args.length !=2){
            throw new IllegalStateException("Invalid number of arguments. Usage: displayUser <velibnetworkName> <userID>");
        }
        VelibGlobal instance = VelibGlobal.getInstance();
        String VelibNetworkName = args[0];
        int userId = Integer.parseInt(args[1]);
        VelibSystem system = instance.searchSystemByName(VelibNetworkName);
        User user = system.searchUserById(userId);
        user.display();
    }
}
