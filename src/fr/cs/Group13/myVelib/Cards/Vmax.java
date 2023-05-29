/**
 * The Vmax class represents a type of membership card, implementing the Card and PricingVisitor interfaces.
 * It holds information about the User who owns it, its id, and its credit balance.
 * The card provides pricing options for both ElectricalBicycle and MechanicalBicycle, and can visit different stations.
 *
 * @author Oussama and Nizar
 */
package fr.cs.Group13.myVelib.Cards;

import fr.cs.Group13.myVelib.Bicycle.Bicycle;
import fr.cs.Group13.myVelib.Bicycle.ElectricalBicycle;
import fr.cs.Group13.myVelib.Bicycle.MechanicalBicycle;
import fr.cs.Group13.myVelib.DockingStation.DockingStation;
import fr.cs.Group13.myVelib.DockingStation.PlusStation;
import fr.cs.Group13.myVelib.DockingStation.RegularStation;
import fr.cs.Group13.myVelib.User.User;

import java.text.DecimalFormat;

public class Vmax implements Card, PricingVisitor {
    private User owner;
    private final int id;
    private double creditBalance;

    public Vmax() {
        CardIdGenerator instance = CardIdGenerator.getInstance();
        this.id = instance.getNextCardId();
    }

    /**
     * Constructor for Vmax card, it also generates a unique ID for each new card.
     */
    public Vmax(User owner) {
        CardIdGenerator instance = CardIdGenerator.getInstance();
        this.id = instance.getNextCardId();
        this.owner = owner;
    }

    /**
     * Returns the ID of the card.
     *
     * @return the ID of the card
     */
    public int getId() {
        return id;
    }

    /**
     * Returns the credit balance of the user.
     *
     * @return the credit balance of the user
     */
    public double getCreditBalance() {
        return this.creditBalance;
    }

    /**
     * Sets the owner of this card.
     *
     * @param owner the user who owns this card
     */
    public void setOwner(User owner) {
        this.owner = owner;
    }

    /**
     * Sets the credit balance of the user.
     *
     * @param creditBalance the new credit balance of the user
     */
    public void setCreditBalance(double creditBalance) {
        this.creditBalance = creditBalance;
    }

    /**
     * Method to provide the cost parameters for visiting an ElectricalBicycle.
     * @param bicycle the ElectricalBicycle being visited.
     * @return an array of double values representing the base cost and the cost per hour.
     */
    @Override
    public double[] visit(ElectricalBicycle bicycle) {
        return new double[]{0,1};
    }

    /**
     * Method to provide the cost parameters for visiting an MechanicalBicycle.
     * @param bicycle the MechanicalBicycle being visited.
     * @return an array of double values representing the base cost and the cost per hour.
     */
    @Override
    public double[] visit(MechanicalBicycle bicycle) {
        return new double[]{0,1};
    }

    /**
     * No bonus is granted when returning a bike to a RegularStation .
     * @param station the RegularStation being visited.
     * @return the bonus granted, which is 0 in this case.
     */
    @Override
    public double visit(RegularStation station) {return 0;}

    /**
     * 5 minutes credit bonus is granted when returning a bike to a PlusStation.
     * @param station the PlusStation being visited.
     * @return the bonus granted, which is 5 in this case.
     */
    @Override
    public double visit(PlusStation station) {return 5;}

    /**
     * Computes the charge for a particular ride.
     * @param b The bicycle used for the ride.
     * @param endingIsStation Flag indicating if the ride ended at a station.
     * @param duration Duration of the ride.
     * @return The final charge for the ride.
     */
    @Override
    public double computeCharge(Bicycle b, int endingIsStation, double duration) {
        double[] priceHour = b.accept(this);
        double effectiveDuration = Math.max(0, duration-this.getCreditBalance());
        this.setCreditBalance(Math.max(0, this.getCreditBalance()-duration));
        double basePrice = priceHour[0]*Math.min(effectiveDuration, 60) + priceHour[1]*Math.max(effectiveDuration-60, 0);
        double finalPrice = basePrice*(1-0.1*(endingIsStation -b.getFromAStation()));
        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        finalPrice = Double.parseDouble(decimalFormat.format(finalPrice));
        return finalPrice/60;
    }

    /**
     * Applies the bonus depending on the type of station.
     * @param station The docking station.
     */
    @Override
    public void applyBonus(DockingStation station) {
        double bonus = station.accept(this);
        this.creditBalance = this.creditBalance + bonus;
        this.owner.setTotalTimeCredit(this.owner.getTotalTimeCredit()+bonus);
    }

    /**
     * Checks if this Vmax card is equal to the specified object.
     *
     * @param obj the object to compare this Vmax card to
     * @return true if the specified object is equal to this Vmax card, false otherwise
     */
    @Override
    public boolean equals(Object obj){
        if(obj == this) return true;
        if(obj instanceof Vmax)
            return ((Vmax)obj).getId()==this.getId();
        return false;
    }

}
