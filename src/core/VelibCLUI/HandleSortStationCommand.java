/**
 * The HandleSortStationCommand class provides a command handler for sorting stations in a Velib network.
 */
package core.VelibCLUI;

import core.System.VelibGlobal;
import core.System.VelibSystem;

public class HandleSortStationCommand {
    /**
     * Handles the sortStation command.
     * Sorts the stations in the specified Velib network based on the specified sorting policy.
     *
     * @param args The command arguments containing the Velib network name and sorting policy.
     * @throws InvalidArgumentSizeException if the number of arguments is invalid.
     * @throws InvalidSortingPolicyException if the sorting policy is not recognized.
     */
    public static void handleSortStation(String[] args){
        if (args.length !=2){
            throw new InvalidArgumentSizeException("Invalid number of arguments. Usage: displayUser <velibnetworkName> <userID>");
        }
        VelibGlobal instance = VelibGlobal.getInstance();
        String VelibNetworkName = args[0];
        String sortingPolicy = args[1];
        VelibSystem system = instance.searchSystemByName(VelibNetworkName);
        if (sortingPolicy.equalsIgnoreCase("leastOccupiedStation")){
            system.leastOccupiedSort();
        } else if (sortingPolicy.equalsIgnoreCase("mostusedstation")) {
            system.mostUsedSort();
        }else{
            throw new InvalidSortingPolicyException("There is no such sorting policy.");
        }
    }
}
