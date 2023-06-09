package fr.cs.group13.myVelib.core.user;

import fr.cs.group13.myVelib.core.bicycle.Bicycle;
import fr.cs.group13.myVelib.core.cards.Card;
import fr.cs.group13.myVelib.core.cards.CreditCard;
import fr.cs.group13.myVelib.core.dockingstation.ParkingSlot;
import fr.cs.group13.myVelib.core.planing.RideType;
import fr.cs.group13.myVelib.core.planing.RidesPlaning;
import fr.cs.group13.myVelib.core.system.VelibSystem;

import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;

/**
 * The User class represents a user of the bike rental system.
 * This class includes details about the user and methods to rent and return a bike.
 *
 * @author Oussama and Nizar
 */
public class User {
    // instance variables
    private int id; // unique identifier for the user
    private String firstName; // user's first name
    private String lastName; // user's last name
    private double[] gpsCord; // user's GPS coordinates
    private Card card; // user's bike rental card
    private CreditCard creditCard; // user's credit card
    private Bicycle currentBicycle; // the bike currently rented by the user
    private VelibSystem vlibSys; // the Vlib System in which the user is registered
    private double totalCharges; // total amount charged to the user
    private int numberOfRides; // total number of bike rides by the user
    private double totalTime; // total time spent on bike rides by the user
    private double totalTimeCredit; // total time credited to the user
    private static int count; // static variable used for unique id generation
    private Instant startTime; // start time of the current bike rental
    private Instant endTime; // end time of the current bike rental

    /**
     * Constructor for User.
     *
     * @param firstName the user's first name
     * @param lastName the user's last name
     * @param card the user's rental card
     * @param creditCard the user's credit card
     */
    public User(String firstName, String lastName, Card card, CreditCard creditCard) {
        User.count ++;
        this.id = User.count;
        this.firstName = firstName;
        this.lastName = lastName;
        this.card = card;
        this.creditCard = creditCard;
    }

    /**
     * Constructor for User with VlibSystem and GPS coordinates.
     *
     * @param vlibSys   The Vlib System in which the user is registered.
     * @param gpsCord   The user's GPS coordinates.
     */
    public User(VelibSystem vlibSys, double[] gpsCord) {
        User.count ++;
        this.gpsCord = gpsCord;
        this.id = User.count;
        this.vlibSys = vlibSys;
    }

    /**
     * Constructor for User with first name only.
     *
     * @param firstName   The user's first name.
     */
    public User(String firstName) {
        User.count ++;
        this.id = User.count;
        this.firstName = firstName;
    }

    /**
     * Returns the ID of the user.
     *
     * @return The user's ID.
     */
    public int getId() {
        return id;
    }

    /**
     * Returns the first name of the user.
     *
     * @return The user's first name.
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets the first name of the user.
     *
     * @param firstName The user's first name.
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Returns the last name of the user.
     *
     * @return The user's last name.
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Returns the current bicycle of the user.
     *
     * @return The user's current bicycle.
     */
    public Bicycle getCurrentBicycle() {
        return currentBicycle;
    }

    /**
     * Sets the last name of the user.
     *
     * @param lastName The user's last name.
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Returns the GPS coordinates of the user.
     *
     * @return The user's GPS coordinates.
     */
    public double[] getGpsCord() {
        return gpsCord;
    }

    /**
     * Sets the GPS coordinates of the user.
     *
     * @param gpsCord The user's GPS coordinates.
     */
    public void setGpsCord(double[] gpsCord) {
        this.gpsCord = gpsCord;
    }

    /**
     * Returns the subscription card of the user.
     *
     * @return The user's subscription card.
     */
    public Card getCard() {
        return card;
    }

    /**
     * Sets the subscription card of the user.
     *
     * @param card The user's subscription card.
     */
    public void setCard(Card card) {
        this.card = card;
    }

    /**
     * Returns the credit card of the user.
     *
     * @return The user's credit card.
     */
    public CreditCard getCreditCard() {
        return creditCard;
    }

    /**
     * Sets the credit card of the user.
     *
     * @param creditCard The user's credit card.
     */
    public void setCreditCard(CreditCard creditCard) {
        this.creditCard = creditCard;
    }

    /**
     * Returns the total charges of the user.
     *
     * @return The total charges.
     */
    public double getTotalCharges() {
        return totalCharges;
    }

    /**
     * Sets the total charges of the user.
     *
     * @param totalCharges The total charges.
     */
    public void setTotalCharges(double totalCharges) {
        this.totalCharges = totalCharges;
    }

    /**
     * Returns the number of rides by the user.
     *
     * @return The number of rides.
     */
    public int getNumberOfRides() {
        return numberOfRides;
    }

    /**
     * Sets the number of rides by the user.
     *
     * @param numberOfRides The number of rides.
     */
    public void setNumberOfRides(int numberOfRides) {
        this.numberOfRides = numberOfRides;
    }

    /**
     * Returns the total time spent on bike rides by the user.
     *
     * @return The total time spent on bike rides.
     */
    public double getTotalTime() {
        return totalTime;
    }

    /**
     * Sets the total time spent on bike rides by the user.
     *
     * @param totalTime The total time spent on bike rides.
     */
    public void setTotalTime(double totalTime) {
        this.totalTime = totalTime;
    }

    /**
     * Returns the total time credited to the user.
     *
     * @return The total time credited.
     */
    public double getTotalTimeCredit() {
        return totalTimeCredit;
    }

    /**
     * Sets the total time credited to the user.
     *
     * @param totalTimeCredit The total time credited.
     */
    public void setTotalTimeCredit(double totalTimeCredit) {
        this.totalTimeCredit = totalTimeCredit;
    }

    /**
     * This method allows the user to rent a bike.
     *
     * @throws IllegalStateException if the user is already renting a bike
     */
    public void rentBicycle(Bicycle b){
        if (this.currentBicycle == null) {
            if (this.vlibSys!=null) {this.vlibSys.removeStreetBike(b);}
            this.startTime = Instant.now();
            this.currentBicycle = b;
            if (this.currentBicycle.getSlot()!=null)
            {this.currentBicycle.getSlot().getStation().incrementRents();
            this.currentBicycle.getSlot().freeSlot();}
        }else{
            throw new IllegalStateException("You cannot rent more than one bicycle!");
        }
    }

    /**
     * Returns the bicycle to a location outside of any docking station (on the street).
     * The GPS coordinates of the bicycle are assigned as the current coordinates of the user.
     *
     * @return The charge for the rental.
     * @throws IllegalStateException if the user doesn't have a bicycle.
     */
    public double returnBicycle(){
        if (this.currentBicycle == null) {
            throw new IllegalStateException("you don't have a bicycle!");
        }else {
            this.currentBicycle.setGpsCord(this.gpsCord);
            if (this.vlibSys!=null) {this.vlibSys.addStreetBike(this.currentBicycle);}
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
            return charge;
        }
    }
    /**
     * Returns the bicycle to the specified parking slot.
     *
     * @param slot The parking slot where the bicycle is being returned.
     * @return The charge for the rental.
     * @throws IllegalStateException if the user doesn't have a bicycle.
     */
    public double returnBicycle(ParkingSlot slot){
        if (this.currentBicycle == null) {
            throw new IllegalStateException("you don't have a bicycle!");
        }else{
            slot.putBicycle(this.currentBicycle);
            slot.getStation().incrementReturns();
            this.endTime = Instant.now();
            double duration = Duration.between(startTime, endTime).getSeconds() / 60;
            double charge = card.computeCharge(this.currentBicycle, 1, duration);
            this.card.applyBonus(slot.getStation());
            this.totalTime += duration;
            this.totalCharges += charge;
            this.numberOfRides ++;
            this.startTime = null;
            this.endTime = null;
            this.currentBicycle = null;
            return charge;
        }
    }
    /**
     * This method allows the user to return a bike to a random place which is not a slot.
     *
     * @param duration The duration of the bike ride in minutes.
     * @return The charge for the rental.
     * @throws IllegalStateException if the user isn't currently renting a bike
     */
    public double returnBicycle(double duration){
        if (this.currentBicycle == null) {
            throw new IllegalStateException("you don't have a bicycle!");
        }else {
            this.currentBicycle.setGpsCord(this.gpsCord);
            if (this.vlibSys!=null) {this.vlibSys.addStreetBike(this.currentBicycle);}
            double charge = card.computeCharge(this.currentBicycle, 0, duration);
            this.totalTime += duration;
            this.totalCharges += charge;
            this.numberOfRides++;
            this.currentBicycle.setFromAStation(0);
            this.startTime = null;
            this.endTime = null;
            this.currentBicycle = null;
            return charge;
        }
    }

    /**
     * This method allows the user to return a bike to a specific slot.
     *
     * @param slot     The slot where the bike will be returned.
     * @param duration The duration of the bike ride in minutes.
     * @return The charge for the rental.
     * @throws IllegalStateException if the user isn't currently renting a bike.
     */
    public double returnBicycle(ParkingSlot slot, double duration){
        if (this.currentBicycle == null) {
            throw new IllegalStateException("you don't have a bicycle!");
        }else{
            slot.putBicycle(this.currentBicycle);
            slot.getStation().incrementReturns();
            double charge = card.computeCharge(this.currentBicycle, 1, duration);
            this.card.applyBonus(slot.getStation());
            this.totalTime += duration;
            this.totalCharges += charge;
            this.numberOfRides ++;
//            this.currentBicycle.addToSlot(slot);
            this.startTime = null;
            this.endTime = null;
            this.currentBicycle = null;
            return charge;
        }
    }

    /**
     * This method allows the user to plan a ride.
     *
     * @param strategy the strategy for planning the ride
     * @return a String representing the ride plan
     */
    public String planYourRide(String strategy, VelibSystem system, double[] startingGPS, double[] endingGPS){
        //factory call
        RideType ride = new RideType();
        RidesPlaning plan = ride.getYourStrat(strategy);
        return plan.getYourPlan(system.getListOfStations(), system.getListOfStreetBikes(), startingGPS, endingGPS);
    }

    /**
     * Prints the balance information of the user.
     */
    public void userBalance(){
        System.out.println("User " + this.id+" balance :" +
                "\r\n\t number of rides : " + this.numberOfRides +
                "\r\n\t total time spent on a bike : "+ this.totalTime +
                "\r\n\t total charge : " + this.totalCharges +
                "\r\n\t total time credit earned : "+ this.totalTimeCredit);
    }

    /**
     * Checks if this User object is equal to another object.
     *
     * @param obj the object to compare with
     * @return true if the objects are equal, false otherwise
     */
    @Override
    public boolean equals(Object obj){
        if(obj == this) return true;
        if(obj instanceof User)
            return ((User)obj).getId()==this.id;
        return false;
    }

    /**
     * Returns a string representation of the User.
     *
     * @return a string representation of the User
     */
    @Override
    public String toString() {
        return  "User"+"\n\tid             = " + id +
                "\n\tFull Name       = " + firstName + " " + lastName +
                "\n\tgps Coordinates = " + Arrays.toString(gpsCord) +
                "\n\tcard            = " + card.getClass().getSimpleName() +
                "\n\thasBicycle      = " + (currentBicycle == null? "no": "yes");
    }

    /**
     * Returns a string representation of the User's statistics.
     *
     * @return a string representation of the User's statistics
     */
    public void display() {
        System.out.println(this.getFirstName()+" statistics :");
        System.out.println("\tNumber of rides : "+this.numberOfRides);
        System.out.println("\tTotal time spent on bike : "+this.totalTime);
        System.out.println("\tTotal charges : "+this.totalCharges);
        System.out.println("\tTime credit : "+this.card.getCreditBalance());
        System.out.println("\tTotal earned time credit : "+this.totalTimeCredit);
    }
}
