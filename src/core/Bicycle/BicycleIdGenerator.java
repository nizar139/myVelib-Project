/**
 * The BicycleIdGenerator class is a singleton that generates unique IDs for each Bicycle object.
 * It ensures that each Bicycle in the system has a unique identifier.
 *
 * @author Oussama and Nizar
 */
package core.Bicycle;

public class BicycleIdGenerator {
    /**
     * Singleton instance of the BicycleIdGenerator
      */
    private static BicycleIdGenerator instance = null;

    /**
     * Counter for the current unique bicycle ID
     */
    private int num;

    /**
     * Private constructor to prevent instantiation from outside.
     */
    private BicycleIdGenerator(){}

    /**
     * Method to retrieve the singleton instance of BicycleIdGenerator.
     * If it does not exist, a new instance is created.
     *
     * @return The singleton instance of BicycleIdGenerator.
     */
    public static BicycleIdGenerator getInstance() {
        if (instance==null) {
            instance = new BicycleIdGenerator();
        }
        return instance;
    }

    /**
     * Public method to obtain the next unique Bicycle ID.
     *
     * @return The next unique Bicycle ID.
     */
    public int getNextBicycleID(){
        return num++;
    }

}
