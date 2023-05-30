/**
 * The StationIdGenerator class is a singleton that generates unique IDs for each Docking station object.
 * It ensures that each Docking station in the system has a unique identifier.
 *
 * @author Oussama and Nizar
 */
package core.DockingStation;

public class StationIdGenerator {
    private static StationIdGenerator instance = null;
    private int num;

    /**
     * Private constructor to prevent instantiation from outside.
     */
    private StationIdGenerator(){}

    /**
     * Method to retrieve the singleton instance of StationIdGenerator.
     * If it does not exist, a new instance is created.
     *
     * @return The singleton instance of StationIdGenerator.
     */
    public static StationIdGenerator getInstance() {
        if (instance==null) {
            instance = new StationIdGenerator();
        }
        return instance;
    }

    /**
     * Public method to obtain the next unique Docking station ID.
     *
     * @return The next unique Docking station ID.
     */
    public int getNextStationID(){
        return num++;
    }
}
