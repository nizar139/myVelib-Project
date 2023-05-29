/**
 * The NoCard class represents a user in the system without a membership card.
 * It implements the Card and PricingVisitor interfaces.
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

public class NoCard implements Card, PricingVisitor {

    private User owner;

    /**
     * Constructor for NoCard.
     */
    public NoCard(User owner) {
        this.owner = owner;
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
     * Method to provide the cost parameters for visiting an ElectricalBicycle.
     * @param bicycle the ElectricalBicycle being visited.
     * @return an array of double values representing the base cost and the cost per hour.
     */
    @Override
    public double[] visit(ElectricalBicycle bicycle) {return new double[]{2,2};}

    /**
     * ethod to provide the cost parameters for visiting a MechanicalBicycle.
     * @param bicycle the MechanicalBicycle being visited.
     * @return an array of double values representing the base cost and the cost per hour.
     */
    @Override
    public double[] visit(MechanicalBicycle bicycle) {return new double[]{1,1};}

    /**
     * No bonus is granted when returning a bike to a RegularStation for NoCard user.
     * @param station the RegularStation being visited.
     * @return the bonus granted, which is 0 in this case.
     */
    @Override
    public double visit(RegularStation station) {return 0;}

    /**
     * No bonus is granted when returning a bike to a PlusStation for NoCard user.
     * @param station the PlusStation being visited.
     * @return the bonus granted, which is 0 in this case.
     */
    @Override
    public double visit(PlusStation station) {return 0;}

    /**
     * Computes the final rental price based on the bicycle type, the duration of the rental and
     * whether the bike was returned at a station.
     * @param b the bicycle that was rented.
     * @param endingIsStation indicates if the bike was returned at a station.
     * @param duration the duration of the rental.
     * @return the final price of the rental.
     */
    @Override
    public double computeCharge(Bicycle b, int endingIsStation, double duration) {
        double[] priceHour = b.accept(this);
        double basePrice = priceHour[0]*duration;
        double finalPrice = basePrice*(1-0.1*(endingIsStation -b.getFromAStation()));
        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        finalPrice = Double.parseDouble(decimalFormat.format(finalPrice));
        return finalPrice/60;
    }

    /**
     * No bonus is provided for a NoCard user.
     * @param station the station where the user ends the ride.
     */
    @Override
    public void applyBonus(DockingStation station) {
    }
}

