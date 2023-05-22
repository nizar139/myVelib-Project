package fr.cs.Group13.myVelib.Cards;

import fr.cs.Group13.myVelib.Bicycle.Bicycle;
import fr.cs.Group13.myVelib.Bicycle.ElectricalBicycle;
import fr.cs.Group13.myVelib.Bicycle.MechanicalBicycle;
import fr.cs.Group13.myVelib.DockingStation.DockingStation;

public class Vlibre implements Card, PricingVisitor {
    private final int id;
    private double creditBalance;

    public Vlibre() {
        CardIdGenerator instance = CardIdGenerator.getInstance();
        this.id = instance.getNextCardId();
    }

    public int getId() {
        return id;
    }

    public double getCreditBalance() {
        return creditBalance;
    }

    public void setCreditBalance(double creditBalance) {
        this.creditBalance = creditBalance;
    }

    @Override
    public double[] visit(ElectricalBicycle bicycle) {
        return new double[]{1,2};
    }

    @Override
    public double[] visit(MechanicalBicycle bicycle) {
        return new double[]{0,1};
    }
    @Override
    public double computeCharge(Bicycle b, int start, int end, double duration) {
        double[] priceHour = b.accept(this);
        double effectiveDuration = Math.min(0, duration-this.getCreditBalance());
        this.setCreditBalance(Math.min(0, this.getCreditBalance()-duration));
        double basePrice = priceHour[0]*Math.min(effectiveDuration, 60) + priceHour[1]*Math.max(effectiveDuration-60, 0);
        double finalPrice = basePrice*(1-0.1*(end-start));
        return finalPrice/60;
    }

    @Override
    public void updateBalance(DockingStation station) {

    }

}
