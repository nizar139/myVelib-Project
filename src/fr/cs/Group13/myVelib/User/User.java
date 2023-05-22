package fr.cs.Group13.myVelib.User;

import fr.cs.Group13.myVelib.Bicycle.Bicycle;
import fr.cs.Group13.myVelib.Cards.*;
import fr.cs.Group13.myVelib.DockingStation.DockingStation;
import fr.cs.Group13.myVelib.Planing.RideType;
import fr.cs.Group13.myVelib.Planing.RidesPlaning;
import java.time.Duration;
import java.time.Instant;

public class User {
    private int id;
    private String firstName;
    private String lastName;
    private double[] gpsCord;
    private Card card;
    private CreditCard myCard;
    private Bicycle currentBicycle = null;
    private double totalCharges;
    private int numberOfRides;
    private double totalTime;
    private double totalTimeCredit;
    private static int count;
    private Instant startTime;
    private Instant endTime;

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

    public void rentBicycle(Bicycle b){
        //type of bicycle, station or not,
        if (this.currentBicycle == null) {
            this.startTime = Instant.now();
            this.currentBicycle = b;
        }else{
            throw new IllegalStateException("You cannot rent more than one bicycle!");
        }
    }

    public void returnBicycle(){
        //time and charge, update totaltimecredit, totaltime, numberofrides, totalcharges
        if (this.currentBicycle == null) {
            throw new IllegalStateException("you don't have a bicycle!");
        }else{
            this.endTime = Instant.now();
            double duration = Duration.between(startTime, endTime).getSeconds() / 60;
            double charge = card.computeCharge(this.currentBicycle, 0, duration);

            this.startTime = null;
            this.endTime = null;
            this.currentBicycle = null;
        }
    }
    public void returnBicycle(DockingStation station){
        // update totaltimecredit, bicycle state
        if (this.currentBicycle == null) {
            throw new IllegalStateException("you don't have a bicycle!");
        }else{
            this.endTime = Instant.now();
            double duration = Duration.between(startTime, endTime).getSeconds() / 60;
            double charge = card.computeCharge(this.currentBicycle, 1, duration);
            this.totalTime += duration;
            this.totalCharges += charge;
            this.numberOfRides ++;
            this.startTime = null;
            this.endTime = null;
            this.currentBicycle = null;
        }

    }
    public String planYourRide(String strategy){
        //factory call
        RideType ride = new RideType();
        RidesPlaning plan = ride.getYourStrat(strategy);
        return plan.getYourPlan();
    }

}
