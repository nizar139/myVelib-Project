/**
 * The CreditCard class represents a credit card in the system.
 * It holds important information including the card's serial number, the expiration date, the card verification code (CVC),
 * and the User who owns the card.
 *
 * @author Oussama and Nizar
 */
package fr.cs.Group13.myVelib.Cards;

import fr.cs.Group13.myVelib.User.User;

public class CreditCard {
    private int serialNumber;
    private int[] expirationDate; // in the format of [month, year]
    private int cvc;
    private User owner;

    /**
     * Creates a new CreditCard object with the provided parameters.
     */
    public CreditCard() {
    }

    /**
     * Creates a new CreditCard object with the provided parameters.
     *
     * @param serialNumber the serial number of the card.
     * @param expirationDate the expiration date of the card.
     * @param cvc the card verification code of the card.
     * @param owner the user who owns the card.
     */
    public CreditCard(int serialNumber, int[] expirationDate, int cvc, User owner) {
        this.serialNumber = serialNumber;
        this.expirationDate = expirationDate;
        this.cvc = cvc;
        this.owner = owner;
    }

    /**
     * Retrieves the serial number of the card.
     *
     * @return the serial number of the card.
     */
    public int getSerialNumber() {
        return serialNumber;
    }

    /**
     * Sets a new serial number for the card.
     *
     * @param serialNumber the new serial number to set.
     */
    public void setSerialNumber(int serialNumber) {
        this.serialNumber = serialNumber;
    }

    /**
     * Retrieves the expiration date of the card.
     *
     * @return the expiration date of the card in the format of [month, year].
     */
    public int[] getExpirationDate() {
        return expirationDate;
    }

    /**
     * Sets a new expiration date for the card.
     *
     * @param expirationDate the new expiration date to set in the format of [month, year].
     */
    public void setExpirationDate(int[] expirationDate) {
        this.expirationDate = expirationDate;
    }

    /**
     * Retrieves the card verification code (CVC) of the card.
     *
     * @return the CVC of the card.
     */
    public int getCvc() {
        return cvc;
    }

    /**
     * Sets a new card verification code (CVC) for the card.
     *
     * @param cvc the new CVC to set.
     */
    public void setCvc(int cvc) {
        this.cvc = cvc;
    }

    /**
     * Retrieves the owner of the card.
     *
     * @return the User who owns the card.
     */
    public User getOwner() {
        return owner;
    }

    /**
     * Sets a new owner for the card.
     *
     * @param owner the new User to set as the owner.
     */
    public void setOwner(User owner) {
        this.owner = owner;
    }
}
