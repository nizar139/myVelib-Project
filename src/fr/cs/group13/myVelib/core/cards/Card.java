/**
 * The Card interface represents a card in the myVelib system.
 * It provides methods to compute charge for bike usage and apply bonus to a DockingStation.
 * This interface is meant to be implemented by classes representing different types of cards.
 *
 * @author Oussama and Nizar
 */
package fr.cs.group13.myVelib.core.cards;

import fr.cs.group13.myVelib.core.bicycle.Bicycle;
import fr.cs.group13.myVelib.core.dockingstation.DockingStation;

public interface Card {
    /**
     * This method calculates the charge for using a bicycle based on its type, duration of use,
     * and whether the bike is returned at a station.
     *
     * @param b The Bicycle object for which the charge is to be computed.
     * @param endingIsStation Indicator of whether the bike is returned at a station (1) or not (0).
     * @param duration Duration of the bicycle use in hours.
     * @return The computed charge .
     */
    double computeCharge(Bicycle b, int endingIsStation, double duration);

    /**
     * This method applies the bonus of this card to a specified DockingStation.
     *
     * @param station The DockingStation object to which the bonus is to be applied.
     * @return The bonus granted.
     */
    void applyBonus(DockingStation station);

    /**
     * Returns the credit balance of the user.
     *
     * @return the credit balance of the user
     */
    double getCreditBalance();
}
