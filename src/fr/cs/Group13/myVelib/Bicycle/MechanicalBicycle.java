package fr.cs.Group13.myVelib.Bicycle;

public class MechanicalBicycle implements Bicycle{
    private int id;
    private double gpsCord [];
    private double rentTime;

    public MechanicalBicycle() {
        BicycleIdGenerator instance = BicycleIdGenerator.getInstance();
        this.id = instance.getNextBicycleID();
    }
}
