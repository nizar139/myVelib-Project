package fr.cs.Group13.myVelib.Cards;

import fr.cs.Group13.myVelib.Bicycle.Bicycle;
import fr.cs.Group13.myVelib.Bicycle.ElectricalBicycle;
import fr.cs.Group13.myVelib.Bicycle.MechanicalBicycle;
import fr.cs.Group13.myVelib.DockingStation.DockingStation;

public class Vmax implements Card, PricingVisitor {
    private final int id;
    private double CreditBalance;

    public Vmax() {
        CardIdGenerator instance = CardIdGenerator.getInstance();
        this.id = instance.getNextCardId();
    }

    @Override
    public double[] visit(ElectricalBicycle bicycle) {
        return new double[]{0,1};
    }
    @Override
    public double[] visit(MechanicalBicycle bicycle) {
        return new double[]{0,1};
    }
    @Override
    public double computeCharge(Bicycle b, int start, int end) {
        return 0;
    }

    @Override
    public void updateBalance(DockingStation station) {

    }

}