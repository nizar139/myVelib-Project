/**
 * The VlibSystem class represents the overall system of the Vlib application.
 * It manages the docking stations, street bikes, and users in the system.
 * The class provides various methods to manipulate the system, such as adding stations, users, and street bikes,
 * generating random coordinates, searching for users and bicycles, and generating system summaries.
 * It also has a createUseCaseSys() method to create a use-case scenario system.
 */
package fr.cs.Group13.myVelib.System;

import fr.cs.Group13.myVelib.Bicycle.Bicycle;
import fr.cs.Group13.myVelib.Cards.NoCard;
import fr.cs.Group13.myVelib.Cards.Vlibre;
import fr.cs.Group13.myVelib.Cards.Vmax;
import fr.cs.Group13.myVelib.DockingStation.DockingStation;
import fr.cs.Group13.myVelib.DockingStation.ParkingSlot;
import fr.cs.Group13.myVelib.DockingStation.PlusStation;
import fr.cs.Group13.myVelib.DockingStation.RegularStation;
import fr.cs.Group13.myVelib.User.User;

import java.util.ArrayList;
import java.util.Random;


public class VlibSystem {
    private ArrayList<DockingStation> listOfStations;
    private ArrayList<Bicycle> listOfStreetBikes;
    private ArrayList<User> listOfUsers;

    /**
     * Private constructor to prevent direct instantiation of the VlibSystem class.
     */
    private VlibSystem() {}

    /**
     * Constructor for VlibSystem with initial lists of stations, street bikes, and users.
     *
     * @param listOfStations   List of docking stations in the system.
     * @param listOfStreetBikes List of street bikes in the system.
     * @param listOfUsers      List of users in the system.
     */
    public VlibSystem(ArrayList<DockingStation> listOfStations, ArrayList<Bicycle> listOfStreetBikes, ArrayList<User> listOfUsers) {
        this.listOfStations = listOfStations;
        this.listOfStreetBikes = listOfStreetBikes;
        this.listOfUsers = listOfUsers;
    }

    /**
     * Resets the system by clearing the lists of stations, street bikes, and users.
     */
    public void resetSystem(){

        this.listOfStations = null;
        this.listOfUsers = null;
        this.listOfStreetBikes = null;
    }

    /**
     * Assigns a new regular station to the system with the specified parameters.
     *
     * @param gpsCord           GPS coordinates of the station.
     * @param nSlots            Number of slots in the station.
     * @param percentOfVacant   Percentage of vacant slots in the station.
     * @param percentOfElectrical Percentage of electrical bikes in the station.
     */
    public void assignNewRegularStation(double[] gpsCord,int nSlots, double percentOfVacant, double percentOfElectrical){
        double v = nSlots * percentOfVacant;
        double w = nSlots*percentOfElectrical;
        RegularStation station = new RegularStation(this, gpsCord, nSlots,(int) v,(int) w);
        this.listOfStations.add(station);
    }

    /**
     * Assigns a new plus station to the system with the specified parameters.
     *
     * @param gpsCord           GPS coordinates of the station.
     * @param nSlots            Number of slots in the station.
     * @param percentOfVacant   Percentage of vacant slots in the station.
     * @param percentOfElectrical Percentage of electrical bikes in the station.
     */
    public void assignNewPlusStation(double[] gpsCord,int nSlots, double percentOfVacant, double percentOfElectrical){
        double v = nSlots * percentOfVacant;
        double w = nSlots*percentOfElectrical;
        PlusStation station = new PlusStation(this, gpsCord, nSlots,(int) v,(int) w);
        this.listOfStations.add(station);
    }

    /**
     * Adds a non-subscriber user to the system with the specified GPS coordinates.
     *
     * @param gpsCord GPS coordinates of the user.
     */
    public void addNonSub(double[] gpsCord){
        User user = new User(this, gpsCord);
        NoCard card = new NoCard(user);
        user.setCard(card);
        this.listOfUsers.add(user);
    }

    /**
     * Adds a Vlibre user to the system with the specified GPS coordinates.
     *
     * @param gpsCord GPS coordinates of the user.
     */
    public void addVlibreUser(double[] gpsCord){
        User user = new User(this, gpsCord);
        Vlibre card = new Vlibre(user);
        user.setCard(card);
        this.listOfUsers.add(user);
    }

    /**
     * Adds a Vmax user to the system with the specified GPS coordinates.
     *
     * @param gpsCord GPS coordinates of the user.
     */
    public void addVmaxUser(double[] gpsCord) {
        User user = new User(this, gpsCord);
        Vmax card = new Vmax(user);
        user.setCard(card);
        this.listOfUsers.add(user);
    }

    /**
     * Generates random coordinates within the specified maximum range.
     *
     * @param maxX Maximum X-coordinate value.
     * @param maxY Maximum Y-coordinate value.
     * @return An array containing the generated random coordinates [x, y].
     */
    public double[] getRandomCord(double maxX, double maxY){
        Random random = new Random();
        double x = random.nextDouble()*maxX;
        double y = random.nextDouble()*maxY;
        return new double[]{x, y};
    }

    /**
     * Generates a map of docking stations in the system.
     *
     * @param nRegularStations    Number of regular stations to generate.
     * @param nPlusStations       Number of plus stations to generate.
     * @param nSlotsPerStation    Number of slots per station.
     * @param percentOfVacant     Percentage of vacant slots in each station.
     * @param percentOfElectrical Percentage of electrical bikes in each station.
     * @param maxX                Maximum X-coordinate value for generating random coordinates.
     * @param maxY                Maximum Y-coordinate value for generating random coordinates.
     */
    public void generateStationMap(int nRegularStations,int nPlusStations, int nSlotsPerStation, double percentOfVacant, double percentOfElectrical, double maxX, double maxY){
        this.listOfStations = new ArrayList<>();
        for (int i=0; i<nRegularStations; i++){
            double[] gps = getRandomCord(maxX, maxY);
            assignNewRegularStation(gps, nSlotsPerStation, percentOfVacant, percentOfElectrical);
        }
        for (int i=0; i<nPlusStations; i++){
            double[] gps = getRandomCord(maxX, maxY);
            assignNewPlusStation(gps, nSlotsPerStation, percentOfVacant, percentOfElectrical);
        }
    }

    /**
     * Generates a list of users in the system.
     *
     * @param nNonSub       Number of non-subscriber users to generate.
     * @param nVlibreUser   Number of Vlibre users to generate.
     * @param nVmaxUser     Number of Vmax users to generate.
     * @param maxX          Maximum X-coordinate value for generating random coordinates.
     * @param maxY          Maximum Y-coordinate value for generating random coordinates.
     */
    public void generateUserList(int nNonSub, int nVlibreUser, int nVmaxUser, double maxX, double maxY){
        listOfUsers = new ArrayList<>();
        for (int i=0; i<nNonSub; i++){
            double[] gps = getRandomCord(maxX, maxY);
            addNonSub(gps);
        }
        for (int i=0; i<nVlibreUser; i++){
            double[] gps = getRandomCord(maxX, maxY);
            addVlibreUser(gps);
        }
        for (int i=0; i<nVmaxUser; i++) {
            double[] gps = getRandomCord(maxX, maxY);
            addVmaxUser(gps);
        }
    }

    /**
     * Adds a street bike to the system.
     *
     * @param b The bicycle to be added as a street bike.
     */
    public void addStreetBike (Bicycle b){
        this.listOfStreetBikes.add(b);
    }

    /**
     * Removes a street bike from the system.
     *
     * @param b The bicycle to be removed from street bikes.
     */
    public void removeStreetBike (Bicycle b){
        this.listOfStreetBikes.remove(b);
    }

    /**
     * Creates a use-case scenario system with pre-defined stations, users, and street bikes.
     *
     * @return The created VlibSystem object.
     */
    public static VlibSystem createUseCaseSys(){
        VlibSystem vlibSys = new VlibSystem();
        vlibSys.generateStationMap(10, 10,10,0.4,0.3,10,10);
        vlibSys.generateUserList(20,10,10,10,10);
        vlibSys.listOfStreetBikes = new ArrayList<>();
        return vlibSys;
    }

    /**
     * Searches for a user in the system based on the specified ID.
     *
     * @param id The ID of the user to search for.
     * @return The User object matching the ID.
     * @throws IllegalStateException if no user is found with the specified ID.
     */
    public User searchUserById(int id){
        for (User user:this.listOfUsers){
            if (user.getId()==id){return user;}
        }
        throw new IllegalStateException("no user Matching this id was found");
    }

    /**
     * Searches for a docking station in the system based on the specified ID.
     *
     * @param id The ID of the docking station to search for.
     * @return The DockingStation object matching the ID.
     * @throws IllegalStateException if no docking station is found with the specified ID.
     */
    public DockingStation searchStationById(int id){
        for (DockingStation station:this.listOfStations){
            if (station.getId()==id){return station;}
        }
        throw new IllegalStateException("no user Matching this id was found");
    }

    /**
     * Searches for a bicycle in the system based on the specified GPS coordinates.
     *
     * @param gpsCord The GPS coordinates of the bicycle to search for.
     * @return The Bicycle object found at the specified coordinates.
     * @throws IllegalStateException if no bicycle is found at the given coordinates.
     */
    public Bicycle searchBicycleByGPS(double[] gpsCord){
        for (Bicycle bike:this.listOfStreetBikes){
            if (gpsCord==bike.getGpsCord()){return bike;}
        }
        for (DockingStation station:this.listOfStations){
            if (gpsCord == station.getGpsCord()){
                System.out.println("station found at these gps coordinates");
                for (ParkingSlot slot: station.getParkingSlotArraylist()){
                    if (slot.isOccupied()){return slot.getBicycle();}
                }
            }
        }
        throw new IllegalStateException("no bicycles found at the given coordinates");
    }

    /**
     * Returns a string representation of the VlibSystem object.
     *
     * @return A string representation of the VlibSystem.
     */
    @Override
    public String toString() {
        return "VlibSystem{" +
                "listOfStations=" + listOfStations +
                ", listOfStreetBikes=" + listOfStreetBikes +
                ", listOfUsers=" + listOfUsers +
                '}';
    }

    /**
     * Prints a summary of the system by displaying the VlibSystem object.
     */
    public void systemSummary(){
        System.out.println(this);
    }
}
