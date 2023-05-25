package fr.cs.Group13.myVelib.DockingStation;


import fr.cs.Group13.myVelib.Cards.PricingVisitor;

public class PlusStation extends DockingStation {
    public PlusStation() {super();}

    public PlusStation(double[] gpsCord, int numberOfSlots, int numberOfVacantSlots, int numberOfElectricalBikes) {
        super(gpsCord, numberOfSlots, numberOfVacantSlots, numberOfElectricalBikes);
    }

    public double accept(PricingVisitor visitor){
        return visitor.visit(this);
    }
}
