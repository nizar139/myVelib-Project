package fr.cs.Group13.myVelib.User;

import fr.cs.Group13.myVelib.Bicycle.Bicycle;
import fr.cs.Group13.myVelib.Cards.*;
import fr.cs.Group13.myVelib.DockingStation.DockingStation;
import fr.cs.Group13.myVelib.DockingStation.ParkingSlot;
import fr.cs.Group13.myVelib.Planing.RideType;
import fr.cs.Group13.myVelib.Planing.RidesPlaning;
import java.time.Duration;
import java.time.Instant;

/**
 * The User class represents a user of the bike rental system.
 * This class includes details about the user and methods to rent and return a bike.
 */
public class User {
    // instance variables
    private int id; // unique identifier for the user
    private String firstName; // user's first name
    private String lastName; // user's last name
    private double[] gpsCord; // user's GPS coordinates
    private Card card; // user's bike rental card
    private CreditCard myCard; // user's credit card
    private Bicycle currentBicycle; // the bike currently rented by the user
    private double totalCharges; // total amount charged to the user
    private int numberOfRides; // total number of bike rides by the user
    private double totalTime; // total time spent on bike rides by the user
    private double totalTimeCredit; // total time credited to the user
    private static int count; // static variable used for unique id generation
    private Instant startTime; // start time of the current bike rental
    private Instant endTime; // end time of the current bike rental

    /**
     * Constructor for User.
     * @param id the user's id
     * @param firstName the user's first name
     * @param lastName the user's last name
     * @param card the user's rental card
     * @param myCard the user's credit card
     */
    public User(int id, String firstName, String lastName, Card card, CreditCard myCard) {
        User.count ++;
        this.id = User.count;
        this.firstName = firstName;
        this.lastName = lastName;
        this.card = card;
        this.myCard = myCard;
    }

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public double[] getGpsCord() {
        return gpsCord;
    }

    public void setGpsCord(double[] gpsCord) {
        this.gpsCord = gpsCord;
    }

    public Card getCard() {
        return card;
    }

    public void setCard(Card card) {
        this.card = card;
    }

    public CreditCard getMyCard() {
        return myCard;
    }

    public void setMyCard(CreditCard myCard) {
        this.myCard = myCard;
    }

    public double getTotalCharges() {
        return totalCharges;
    }

    public void setTotalCharges(double totalCharges) {
        this.totalCharges = totalCharges;
    }

    public int getNumberOfRides() {
        return numberOfRides;
    }

    public void setNumberOfRides(int numberOfRides) {
        this.numberOfRides = numberOfRides;
    }

    public double getTotalTime() {
        return totalTime;
    }

    public void setTotalTime(double totalTime) {
        this.totalTime = totalTime;
    }

    public double getTotalTimeCredit() {
        return totalTimeCredit;
    }

    public void setTotalTimeCredit(double totalTimeCredit) {
        this.totalTimeCredit = totalTimeCredit;
    }
    /**
     * This method allows the user to rent a bike.
     * @param b the bike to be rented
     * @throws IllegalStateException if the user is already renting a bike
     */
    public void rentBicycle(Bicycle b){

        if (this.currentBicycle == null) {
            this.startTime = Instant.now();
            this.currentBicycle = b;
            this.currentBicycle.getSlot().freeSlot();
            this.currentBicycle.removeFromSlot();
        }else{
            throw new IllegalStateException("You cannot rent more than one bicycle!");
        }
    }
    /**
     * This method allows the user to return a bike to a random place which is not a slot.
     * @throws IllegalStateException if the user isn't currently renting a bike
     */
    public void returnBicycle(){
        // update totaltimecredit
        if (this.currentBicycle == null) {
            throw new IllegalStateException("you don't have a bicycle!");
        }else {
            this.endTime = Instant.now();
            double duration = Duration.between(startTime, endTime).getSeconds() / 60;
            double charge = card.computeCharge(this.currentBicycle, 0, duration);
            this.totalTime += duration;
            this.totalCharges += charge;
            this.numberOfRides++;
            this.currentBicycle.setFromAStation(0);
            this.startTime = null;
            this.endTime = null;
            this.currentBicycle = null;
        }
    }
    /**
     * This method allows the user to return a bike to a specific slot.
     * @param slot the slot where the bike will be returned
     * @throws IllegalStateException if the user isn't currently renting a bike
     */
    public void returnBicycle(ParkingSlot slot){
        // update totaltimecredit in card
        if (this.currentBicycle == null) {
            throw new IllegalStateException("you don't have a bicycle!");
        }else{
            slot.putBicycle(this.currentBicycle);
            this.endTime = Instant.now();
            double duration = Duration.between(startTime, endTime).getSeconds() / 60;
            double charge = card.computeCharge(this.currentBicycle, 1, duration);
            this.totalTime += duration;
            this.totalCharges += charge;
            this.numberOfRides ++;
            this.currentBicycle.setFromAStation(1);
            this.currentBicycle.addToSlot(slot);
            this.startTime = null;
            this.endTime = null;
            this.currentBicycle = null;
        }

    }
    /**
     * This method allows the user to plan a ride.
     * @param strategy the strategy for planning the ride
     * @return a String representing the ride plan
     */
    public String planYourRide(String strategy){
        //factory call
        RideType ride = new RideType();
        RidesPlaning plan = ride.getYourStrat(strategy);
        return "";
    }

}
