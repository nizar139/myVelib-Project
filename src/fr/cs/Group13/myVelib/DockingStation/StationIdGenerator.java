package fr.cs.Group13.myVelib.DockingStation;

public class StationIdGenerator {
    private static StationIdGenerator instance = null;
    private int num;
    // private constructor: returns the unique SerialNumberGenerator object
    private StationIdGenerator(){}
    // public getInstance method
    public static StationIdGenerator getInstance() {
        if (instance==null) {
            instance = new StationIdGenerator();
        }
        return instance;
    }
    // public method to obtain next unique serialNumber
    public int getNextStationID(){
        return num++;
    }
}
