package fr.cs.Group13.myVelib.Cards;

import fr.cs.Group13.myVelib.DockingStation.StationIdGenerator;

public class CardIdGenerator {
    private static CardIdGenerator instance = null;
    private int num;
    // private constructor: returns the unique SerialNumberGenerator object
    private CardIdGenerator(){}
    // public getInstance method
    public static CardIdGenerator getInstance() {
        if (instance==null) {
            instance = new CardIdGenerator();
        }
        return instance;
    }
    // public method to obtain next unique serialNumber
    public int getNextCardId(){
        return num++;
    }
}
