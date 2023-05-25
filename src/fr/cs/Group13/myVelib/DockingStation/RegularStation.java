package fr.cs.Group13.myVelib.DockingStation;

import fr.cs.Group13.myVelib.Cards.PricingVisitor;

public class RegularStation extends DockingStation{
    public RegularStation() {super();}
    public RegularStation(double[] gpsCord, int numberOfSlots, int numberOfVacantSlots) {
        super(gpsCord, numberOfSlots, numberOfVacantSlots);
    }
    public double accept(PricingVisitor visitor){
        return visitor.visit(this);
    }
}
