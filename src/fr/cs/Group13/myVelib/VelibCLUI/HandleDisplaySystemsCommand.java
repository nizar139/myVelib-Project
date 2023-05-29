package fr.cs.Group13.myVelib.VelibCLUI;

import fr.cs.Group13.myVelib.System.VelibGlobal;
import fr.cs.Group13.myVelib.System.VelibSystem;

import java.util.ArrayList;

public class HandleDisplaySystemsCommand {
    public static void handleDisplaySystems(){
        VelibGlobal instance = VelibGlobal.getInstance();
        ArrayList<VelibSystem> systems = instance.getListOfVelibSystems();
        System.out.println("There are "+systems.size()+" Velib networks");
        for (VelibSystem s:systems){
            System.out.println("\t"+s.getName());
        }
    }
}
