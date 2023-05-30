/**
 * The HandleDisplayUserCommand class provides a command handler for displaying user statistics.
 */
package core.VelibCLUI;

import core.System.VelibGlobal;
import core.System.VelibSystem;
import core.User.User;

public class HandleDisplayUserCommand {
    /**
     * Handles the displayUser command.
     *
     * @param args The command arguments.
     *             Usage: displayUser <velibnetworkName> <userID>
     * @throws InvalidArgumentSizeException if the number of arguments is invalid.
     */
    public static void handleDisplayUser(String[] args){
        if (args.length !=2){
            throw new InvalidArgumentSizeException("Invalid number of arguments. Usage: displayUser <velibnetworkName> <userID>");
        }
        VelibGlobal instance = VelibGlobal.getInstance();
        String VelibNetworkName = args[0];
        int userId = Integer.parseInt(args[1]);
        VelibSystem system = instance.searchSystemByName(VelibNetworkName);
        User user = system.searchUserById(userId);
        user.display();
    }
}
