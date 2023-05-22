package fr.cs.Group13.myVelib.Bicycle;

import fr.cs.Group13.myVelib.Cards.PricingVisitor;

public class MechanicalBicycle implements Bicycle{
    private int id;
    private double gpsCord [];
    private double rentTime;
    private int fromAStation;

    public int getFromAStation() {
        return fromAStation;
    }
    public void setFromAStation(int fromAStation) {
        this.fromAStation = fromAStation;
    }

    public MechanicalBicycle() {
        BicycleIdGenerator instance = BicycleIdGenerator.getInstance();
        this.id = instance.getNextBicycleID();
    }
    public double[] accept(PricingVisitor visitor) {
        return visitor.visit(this);
    }
}

