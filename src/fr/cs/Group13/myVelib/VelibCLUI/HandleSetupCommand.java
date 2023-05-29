package fr.cs.Group13.myVelib.VelibCLUI;

import fr.cs.Group13.myVelib.System.VelibGlobal;
import fr.cs.Group13.myVelib.System.VelibSystem;

/**
 * The HandleSetupCommand class provides a static method to handle the "setup" command.
 * It sets up a Velib system based on the specified arguments.
 */
public class HandleSetupCommand {
    /**
     * Handles the "setup" command.
     *
     * @param args An array of strings containing the command arguments.
     * @return The created VelibSystem instance.
     */
    public static VelibSystem handleSetup(String[] args){
        VelibGlobal instance = VelibGlobal.getInstance();
        VelibSystem velibSys;
        if (args.length == 1){
            String name = args[0];
            try{
                velibSys = instance.searchSystemByName(name);
                System.out.println("A system with the given name already exists");
            }
            catch (Exception e) {
                velibSys = instance.setupVelib(name, 10, 10, 4, 75);
            }
        }
        else if (args.length==5){
            String name = args[0];
            int nStations = Integer.parseInt(args[1]);
            int nSlots = Integer.parseInt(args[2]);
            double s = Double.parseDouble(args[3]);
            int nBikes = Integer.parseInt(args[4]);
            try{
                velibSys = instance.searchSystemByName(name);
                System.out.println("A system with the given name already exists");
            }
            catch (Exception e) {
                velibSys = instance.setupVelib(name, nStations, nSlots, s, nBikes);
            }
        }
        else {
            System.out.println("Invalid number of arguments. Usage: setup <name> or setup <name> <nstations> <nslots> <s> <nbikes>");
            velibSys = null;
        }
        return velibSys;
    }

}

