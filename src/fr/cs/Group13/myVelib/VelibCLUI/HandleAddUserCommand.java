package fr.cs.Group13.myVelib.VelibCLUI;

import fr.cs.Group13.myVelib.System.VelibGlobal;
import fr.cs.Group13.myVelib.System.VelibSystem;

public class HandleAddUserCommand {
    public static void handleAddUser(String[] args){
        VelibGlobal instance = VelibGlobal.getInstance();
        if (args.length!=3) {
            System.out.println("Invalid number of arguments. Usage: addUser <userName> <cardType> <velibnetworkName>");
            return;
        }
        String userName = args[0];
        String cardType = args[1];
        String networkName = args[2];
        if (cardType.equalsIgnoreCase("none")){
            instance.addUserNoCard(userName, networkName);
            return;
        }
        if (cardType.equalsIgnoreCase("vlibre")){
            instance.addUserVlibre(userName, networkName);
            return;
        }
        if (cardType.equalsIgnoreCase("vmax")){
            instance.addUserVmax(userName, networkName);
            return;
        }
        System.out.println("Invalid choice of cardtype. Please choose one of the following : None, Vlibre, Vmax");

    }
}
