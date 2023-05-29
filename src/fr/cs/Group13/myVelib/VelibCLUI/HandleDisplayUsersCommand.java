package fr.cs.Group13.myVelib.VelibCLUI;

import fr.cs.Group13.myVelib.System.VelibGlobal;
import fr.cs.Group13.myVelib.System.VelibSystem;
import fr.cs.Group13.myVelib.User.User;

import java.util.ArrayList;

public class HandleDisplayUsersCommand {
    public static void handleDisplayUsers(String[] args){
        if (args.length !=1){
            throw new IllegalStateException("Invalid number of arguments. Usage: displayUsers <velibnetworkName>");
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
