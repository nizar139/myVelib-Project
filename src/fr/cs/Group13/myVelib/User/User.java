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

    public void rentBicycle(){
        //type of bicycle, station or not,
        this.startTime = Instant.now();
    }

    public void returnBicycle(Bicycle b, DockingStation s){
        //time and charge, update totaltimecredit, totaltime, numberofrides, totalcharges
        this.endTime = Instant.now();
        if (this.startTime == null || this.endTime == null) {
            throw new IllegalStateException("Rental has not started and stopped correctly.");
        }
        double duration = Duration.between(startTime, endTime).getSeconds()/60;

    }
    public void returnBicycle(Bicycle b){
        //time and charge, update totaltimecredit, totaltime, numberofrides, totalcharges
        this.endTime = Instant.now();
        if (this.startTime == null || this.endTime == null) {
            throw new IllegalStateException("Rental has not started and stopped correctly.");
        }
        double duration = Duration.between(startTime, endTime).getSeconds()/60;

        

    }
    public String planYourRide(String strategy){
        //factory call
        RideType ride = new RideType();
        RidesPlaning plan = ride.getYourStrat(strategy);
        return plan.getYourPlan();
    }

}
