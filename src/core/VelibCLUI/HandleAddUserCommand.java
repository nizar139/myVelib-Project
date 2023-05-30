package core.VelibCLUI;

import core.System.VelibGlobal;

/**
 * The HandleAddUserCommand class provides a static method to handle the "addUser" command.
 * It adds a user to the Velib system based on the specified arguments.
 */
public class HandleAddUserCommand {
    /**
     * Handles the "addUser" command.
     *
     * @param args An array of strings containing the command arguments.
     * @throws InvalidArgumentSizeException if the number of arguments is invalid.
     */
    public static void handleAddUser(String[] args){
        VelibGlobal instance = VelibGlobal.getInstance();
        if (args.length!=3) {
            throw  new InvalidArgumentSizeException("Invalid number of arguments. Usage: addUser <userName> <cardType> <velibnetworkName>");
        }
        String userName = args[0];
        String cardType = args[1];
        String networkName = args[2];
        if (cardType.equalsIgnoreCase("none")){
            instance.addUserNoCard(userName, networkName);
            System.out.println(userName+ " user with "+cardType+" membership has been successfully added to the "+networkName+" Velib Network");
            return;
        }
        if (cardType.equalsIgnoreCase("vlibre")){
            instance.addUserVlibre(userName, networkName);
            System.out.println(userName+ " user with "+cardType+" membership has been successfully added to the "+networkName+" Velib Network");
            return;
        }
        if (cardType.equalsIgnoreCase("vmax")){
            instance.addUserVmax(userName, networkName);
            System.out.println(userName+ " user with "+cardType+" membership has been successfully added to the "+networkName+" Velib Network");
            return;
        }
        System.out.println("Invalid choice of cardtype. Please choose one of the following : None, Vlibre, Vmax");

    }
}
