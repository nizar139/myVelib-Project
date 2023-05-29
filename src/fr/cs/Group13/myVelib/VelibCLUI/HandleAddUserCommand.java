package fr.cs.Group13.myVelib.VelibCLUI;

import fr.cs.Group13.myVelib.System.VelibGlobal;
import fr.cs.Group13.myVelib.System.VelibSystem;

/**
 * The HandleAddUserCommand class provides a static method to handle the "addUser" command.
 * It adds a user to the Velib system based on the specified arguments.
 */
public class HandleAddUserCommand {
    /**
     * Handles the "addUser" command.
     *
     * @param args An array of strings containing the command arguments.
     */
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
