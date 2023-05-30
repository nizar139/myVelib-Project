/**
 * The HandleDisplayUserInfoCommand class provides a command handler for displaying user information.
 */
package fr.cs.Group13.myVelib.VelibCLUI;

import fr.cs.Group13.myVelib.System.VelibGlobal;
import fr.cs.Group13.myVelib.User.User;

public class HandleDisplayUserInfoCommand {

    /**
     * Handles the displayUserInfo command.
     *
     * @param args The command arguments.
     *             Usage: displayUserInfo <userID>
     * @throws InvalidArgumentSizeException if the number of arguments is invalid.
     */
    public static void handleDisplayUserInfo(String[] args){
        if (args.length != 1) {
            throw new InvalidArgumentSizeException("Invalid number of arguments. Usage: displayUserInfo <userID>");
        }
        VelibGlobal instance = VelibGlobal.getInstance();
        int stationId = Integer.parseInt(args[0]);
        User user = (User) instance.searchSystemByUserId(stationId)[1];
        System.out.println(user);
    }
}
