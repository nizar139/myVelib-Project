/**
 * The HandleDisplaySystemsCommand class provides a command handler for displaying Velib systems.
 */
package fr.cs.Group13.myVelib.VelibCLUI;

import fr.cs.Group13.myVelib.System.VelibGlobal;
import fr.cs.Group13.myVelib.System.VelibSystem;

import java.util.ArrayList;

public class HandleDisplaySystemsCommand {
    /**
     * Handles the displaySystems command.
     * Prints the names of all Velib networks.
     */
    public static void handleDisplaySystems(){
        VelibGlobal instance = VelibGlobal.getInstance();
        ArrayList<VelibSystem> systems = instance.getListOfVelibSystems();
        System.out.println("There are "+systems.size()+" Velib networks");
        for (VelibSystem s:systems){
            System.out.println("\t"+s.getName());
        }
    }
}
