package fr.cs.Group13.myVelib.VelibCLUI;

import fr.cs.Group13.myVelib.System.VelibGlobal;

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
