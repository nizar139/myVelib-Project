package fr.cs.Group13.myVelib.Cards;

import fr.cs.Group13.myVelib.Bicycle.Bicycle;
import fr.cs.Group13.myVelib.Bicycle.ElectricalBicycle;
import fr.cs.Group13.myVelib.Bicycle.MechanicalBicycle;
import fr.cs.Group13.myVelib.DockingStation.DockingStation;
import fr.cs.Group13.myVelib.DockingStation.PlusStation;
import fr.cs.Group13.myVelib.DockingStation.RegularStation;

public class Vmax implements Card, PricingVisitor {
    private final int id;
    private double CreditBalance;
    public Vmax() {
        CardIdGenerator instance = CardIdGenerator.getInstance();
        this.id = instance.getNextCardId();
    }
    public int getId() {
        return id;
    }

    public double getCreditBalance() {
        return CreditBalance;
    }

    public void setCreditBalance(double creditBalance) {
        CreditBalance = creditBalance;
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
    public double visit(RegularStation station) {return 0;}
    @Override
    public double visit(PlusStation station) {return 5;}
    @Override
    public double computeCharge(Bicycle b, int endingIsStation, double duration) {
        double[] priceHour = b.accept(this);
        double effectiveDuration = Math.min(0, duration-this.getCreditBalance());
        this.setCreditBalance(Math.min(0, this.getCreditBalance()-duration));
        double basePrice = priceHour[0]*Math.min(effectiveDuration, 60) + priceHour[1]*Math.max(effectiveDuration-60, 0);
        double finalPrice = basePrice*(1-0.1*(endingIsStation -1));
        return finalPrice/60;
    }

    @Override
    public void updateBalance(DockingStation station) {

    }

}
