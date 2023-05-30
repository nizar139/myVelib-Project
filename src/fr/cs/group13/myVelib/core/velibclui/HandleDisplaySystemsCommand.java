/**
 * The HandleDisplaySystemsCommand class provides a command handler for displaying Velib systems.
 *
 * @author Oussama and Nizar
 */
package fr.cs.group13.myVelib.core.velibclui;

import fr.cs.group13.myVelib.core.system.VelibGlobal;
import fr.cs.group13.myVelib.core.system.VelibSystem;

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
