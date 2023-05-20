package fr.cs.Group13.myVelib.Bicycle;

public class BicycleIdGenerator {
    private static BicycleIdGenerator instance = null;
    private int num;
    // private constructor: returns the unique SerialNumberGenerator object
    private BicycleIdGenerator(){}
    // public getInstance method
    public static BicycleIdGenerator getInstance() {
        if (instance==null) {
            instance = new BicycleIdGenerator();
        }
        return instance;
    }
    // public method to obtain next unique serialNumber
    public int getNextBicycleID(){
        return num++;
    }

}
