/**
 * The HandleDisplayUsersCommand class provides a command handler for displaying users in a Velib network.
 */
package core.VelibCLUI;

import core.System.VelibGlobal;
import core.System.VelibSystem;
import core.User.User;

import java.util.ArrayList;

public class HandleDisplayUsersCommand {
    /**
     * Handles the displayUsers command.
     *
     * @param args The command arguments.
     *             Usage: displayUsers <velibnetworkName>
     * @throws InvalidArgumentSizeException if the number of arguments is invalid.
     */
    public static void handleDisplayUsers(String[] args){
        if (args.length !=1){
            throw new InvalidArgumentSizeException("Invalid number of arguments. Usage: displayUsers <velibnetworkName>");
        }
        VelibGlobal instance = VelibGlobal.getInstance();
        VelibSystem system = instance.searchSystemByName(args[0]);
        ArrayList<User> users = system.getListOfUsers();
        System.out.println("There are "+users.size()+" user in "+args[0]+" Velib Network.");
        int i = 1;
        for (User user:users){
            System.out.println("user "+i+" :");
            System.out.println(user);
            i++;
        }
    }
}
