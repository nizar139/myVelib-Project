/**
 * The CardIdGenerator class is a singleton that generates unique IDs for each Card object.
 * It ensures that each Card in the system has a unique identifier.
 *
 * @author Oussama and Nizar
 */
package fr.cs.Group13.myVelib.Cards;


/**
 * A class responsible for generating unique card IDs.
 */
public class CardIdGenerator {
    private static CardIdGenerator instance = null;
    private int num;


    /**
     * Private constructor to prevent instantiation from outside.
     */
    private CardIdGenerator(){}

    /**
     * Method to retrieve the singleton instance of CardIdGenerator.
     * If it does not exist, a new instance is created.
     *
     * @return The singleton instance of CardIdGenerator.
     */
    public static CardIdGenerator getInstance() {
        if (instance==null) {
            instance = new CardIdGenerator();
        }
        return instance;
    }

    /**
     * Public method to obtain the next unique Card ID.
     *
     * @return The next unique Card ID.
     */
    public int getNextCardId(){
        return num++;
    }
}
