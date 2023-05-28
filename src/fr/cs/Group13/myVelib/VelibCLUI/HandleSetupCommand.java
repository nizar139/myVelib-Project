package fr.cs.Group13.myVelib.VelibCLUI;

import fr.cs.Group13.myVelib.System.VelibGlobal;
import fr.cs.Group13.myVelib.System.VelibSystem;

public class HandleSetupCommand {
    public static VelibSystem handleSetup(String[] args){
        VelibGlobal instance = VelibGlobal.getInstance();
        VelibSystem velibSys;
        if (args.length == 1){
            String name = args[0];
            velibSys = instance.setupVelib(name,10,10,4,75);
        }
        else if (args.length==5){
            String name = args[0];
            int nStations = Integer.parseInt(args[1]);
            int nSlots = Integer.parseInt(args[2]);
            double s = Double.parseDouble(args[3]);
            int nBikes = Integer.parseInt(args[4]);
            velibSys = instance.setupVelib(name, nStations, nSlots, s, nBikes);
        }
        else {
            System.out.println("Invalid number of arguments. Usage: setup <name> or setup <name><nstations><nslots><s><nbikes>");
            velibSys = null;
        }
        return velibSys;
    }

}

