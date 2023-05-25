package fr.cs.Group13.myVelib.Bicycle;

import fr.cs.Group13.myVelib.Cards.PricingVisitor;
import fr.cs.Group13.myVelib.DockingStation.ParkingSlot;

public abstract class Bicycle{
    private int id;
    private double[] gpsCord;
    private int fromAStation = 1;
    public Bicycle() {
        BicycleIdGenerator instance = BicycleIdGenerator.getInstance();
        this.id = instance.getNextBicycleID();
    }
    public Bicycle(double[] gpsCord) {
        BicycleIdGenerator instance = BicycleIdGenerator.getInstance();
        this.id = instance.getNextBicycleID();
        this.gpsCord = gpsCord;
    }
    public int getfromAStation() {
        return fromAStation;
    }
    public void setFromAStation(int fromAStation) {
        this.fromAStation = fromAStation;
    }
    public abstract double[] accept(PricingVisitor visitor);
    public abstract void setBikeCountFree(ParkingSlot slot);
    public abstract void setBikeCountFill(ParkingSlot slot);
}
