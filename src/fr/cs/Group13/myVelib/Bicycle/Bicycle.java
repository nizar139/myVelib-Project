package fr.cs.Group13.myVelib.Bicycle;

import fr.cs.Group13.myVelib.Cards.PricingVisitor;

public abstract class Bicycle{
    private int id;
    private double gpsCord[];
    private int fromAStation;
    public Bicycle() {
        BicycleIdGenerator instance = BicycleIdGenerator.getInstance();
        this.id = instance.getNextBicycleID();
    }
    public int getfromAStation() {
        return fromAStation;
    }
    public void setFromAStation(int fromAStation) {
        this.fromAStation = fromAStation;
    }
    public abstract double[] accept(PricingVisitor visitor);
}
