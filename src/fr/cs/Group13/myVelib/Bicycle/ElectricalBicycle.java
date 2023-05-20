package fr.cs.Group13.myVelib.Bicycle;

public class ElectricalBicycle implements Bicycle{
    private int id;
    private double gpsCord[];
    private double rentTime;

    public ElectricalBicycle() {
        BicycleIdGenerator instance = BicycleIdGenerator.getInstance();
        this.id = instance.getNextBicycleID();
    }
}
