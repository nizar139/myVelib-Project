/**
 * The VelibSystem class represents a Velib system that manages docking stations, street bikes, and users.
 * It provides various methods to manipulate and query the system's data.
 *
 * @author Oussama and Nizar
 */
package fr.cs.group13.myVelib.core.system;

import fr.cs.group13.myVelib.core.bicycle.Bicycle;
import fr.cs.group13.myVelib.core.cards.NoCard;
import fr.cs.group13.myVelib.core.cards.Vlibre;
import fr.cs.group13.myVelib.core.cards.Vmax;
import fr.cs.group13.myVelib.core.dockingstation.*;
import fr.cs.group13.myVelib.core.user.User;

import java.util.ArrayList;
import java.util.Collections;

public class VelibSystem {
    private int id;
    private static int count = 0;
    private String name;
    private ArrayList<DockingStation> listOfStations;
    private ArrayList<Bicycle> listOfStreetBikes;
    private ArrayList<User> listOfUsers;

    /**

     Gets the next available ID for a network.
     @return The next available ID
     */
    private static int getNextId(){
        count++;
        return count;
    }

    /**
     * Constructs a new VelibSystem object with an automatically generated ID.
     */
    public VelibSystem() {
        this.id = getNextId();
    }

    /**
     * Constructs a new VelibSystem object with an automatically generated ID and the specified name.
     *
     * @param name The name of the VelibSystem
     */
    public VelibSystem(String name) {
        this.id = getNextId();
        this.name = name;
        this.listOfUsers = new ArrayList<>();
        this.listOfStations = new ArrayList<>();
        this.listOfStreetBikes = new ArrayList<>();
    }

    /**
     * Constructs a new VelibSystem object with the specified lists of stations, street bikes, and users.
     *
     * @param listOfStations    The list of docking stations
     * @param listOfStreetBikes The list of street bikes
     * @param listOfUsers       The list of users
     */
    public VelibSystem(ArrayList<DockingStation> listOfStations, ArrayList<Bicycle> listOfStreetBikes, ArrayList<User> listOfUsers) {
        this.id = getNextId();
        this.listOfStations = listOfStations;
        this.listOfStreetBikes = listOfStreetBikes;
        this.listOfUsers = listOfUsers;
    }

    /**
     * Constructs a new VelibSystem object with the specified name, lists of stations, street bikes, and users.
     *
     * @param name              The name of the VelibSystem
     * @param listOfStations    The list of docking stations
     * @param listOfStreetBikes The list of street bikes
     * @param listOfUsers       The list of users
     */
    public VelibSystem(String name, ArrayList<DockingStation> listOfStations, ArrayList<Bicycle> listOfStreetBikes, ArrayList<User> listOfUsers) {
        this.id = getNextId();
        this.name = name;
        this.listOfStations = listOfStations;
        this.listOfStreetBikes = listOfStreetBikes;
        this.listOfUsers = listOfUsers;
    }

    /**
     * Returns the ID of the VelibSystem.
     *
     * @return The ID of the VelibSystem
     */
    public int getId() {
        return id;
    }

    /**
     * Returns the name of the VelibSystem.
     *
     * @return The name of the VelibSystem
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the list of docking stations in the VelibSystem.
     *
     * @return The list of docking stations
     */
    public ArrayList<DockingStation> getListOfStations() {
        return listOfStations;
    }

    /**
     * Returns the list of street bikes in the VelibSystem.
     *
     * @return The list of street bikes
     */
    public ArrayList<Bicycle> getListOfStreetBikes() {
        return listOfStreetBikes;
    }

    /**
     * Returns the list of users in the VelibSystem.
     *
     * @return The list of users
     */
    public ArrayList<User> getListOfUsers() {
        return listOfUsers;
    }

    /**
     * Resets the VelibSystem by creating new empty lists for docking stations, users, and street bikes.
     */
    public void resetSystem(){
        this.listOfUsers = new ArrayList<>();
        this.listOfStations = new ArrayList<>();
        this.listOfStreetBikes = new ArrayList<>();
    }

    /**
     * Assigns a new RegularStation to the VelibSystem with the specified parameters.
     *
     * @param gpsCord            the GPS coordinates of the station
     * @param nSlots             the total number of parking slots at the station
     * @param percentOfVacant    the percentage of vacant slots at the station
     * @param percentOfElectrical the percentage of electrical bikes at the station
     */
    public void assignNewRegularStation(double[] gpsCord,int nSlots, double percentOfVacant, double percentOfElectrical){
        double v = nSlots * percentOfVacant;
        double w = nSlots*percentOfElectrical;
        RegularStation station = new RegularStation(this, gpsCord, nSlots,(int) v,(int) w);
        this.listOfStations.add(station);
    }
    /**
     * Assigns a new PlusStation to the VelibSystem with the specified parameters.
     *
     * @param gpsCord            the GPS coordinates of the station
     * @param nSlots             the total number of parking slots at the station
     * @param percentOfVacant    the percentage of vacant slots at the station
     * @param percentOfElectrical the percentage of electrical bikes at the station
     */
    public void assignNewPlusStation(double[] gpsCord,int nSlots, double percentOfVacant, double percentOfElectrical){
        double v = nSlots * percentOfVacant;
        double w = nSlots*percentOfElectrical;
        PlusStation station = new PlusStation(this, gpsCord, nSlots,(int) v,(int) w);
        this.listOfStations.add(station);
    }

    /**
     * Adds the specified DockingStation to the VelibSystem.
     *
     * @param station the DockingStation to add
     */
    public void addStation(DockingStation station){
        this.listOfStations.add(station);
    }

    /**
     * Adds the specified User to the VelibSystem.
     *
     * @param user the User to add
     */
    public void addUser(User user){
        this.listOfUsers.add(user);
    }

    /**
     * Adds a non-subscriber User with the specified GPS coordinates to the VelibSystem.
     *
     * @param gpsCord the GPS coordinates of the User
     */
    public void addNonSub(double[] gpsCord){
        User user = new User(this, gpsCord);
        NoCard card = new NoCard(user);
        user.setCard(card);
        this.listOfUsers.add(user);
    }

    /**
     * Adds a Vlibre User with the specified GPS coordinates to the VelibSystem.
     *
     * @param gpsCord the GPS coordinates of the User
     */
    public void addVlibreUser(double[] gpsCord){
        User user = new User(this, gpsCord);
        Vlibre card = new Vlibre(user);
        user.setCard(card);
        this.listOfUsers.add(user);
    }

    /**
     * Adds a Vmax User with the specified GPS coordinates to the VelibSystem.
     *
     * @param gpsCord the GPS coordinates of the User
     */
    public void addVmaxUser(double[] gpsCord) {
        User user = new User(this, gpsCord);
        Vmax card = new Vmax(user);
        user.setCard(card);
        this.listOfUsers.add(user);
    }

    /**
     * Generates a map of docking stations with the specified number of regular stations and plus stations,
     * and assigns slots and bikes to each station based on the specified parameters.
     *
     * @param nRegularStations   the number of regular stations to generate
     * @param nPlusStations      the number of plus stations to generate
     * @param nSlotsPerStation   the number of slots per station
     * @param percentOfVacant    the percentage of vacant slots for each station
     * @param percentOfElectrical   the percentage of electrical bikes for each station
     * @param maxX               the maximum value for the x-coordinate
     * @param maxY               the maximum value for the y-coordinate
     */
    public void generateStationMap(int nRegularStations,int nPlusStations, int nSlotsPerStation, double percentOfVacant, double percentOfElectrical, double maxX, double maxY){
        this.listOfStations = new ArrayList<>();
        for (int i=0; i<nRegularStations; i++){
            double[] gps = RandomVectorGenerator.getRandomCord(maxX, maxY);
            assignNewRegularStation(gps, nSlotsPerStation, percentOfVacant, percentOfElectrical);
        }
        for (int i=0; i<nPlusStations; i++){
            double[] gps = RandomVectorGenerator.getRandomCord(maxX, maxY);
            assignNewPlusStation(gps, nSlotsPerStation, percentOfVacant, percentOfElectrical);
        }
    }

    /**
     * Generates a list of users with the specified number of non-subscribers, Vlibre users, and Vmax users.
     * The users are randomly assigned GPS coordinates based on the specified maximum coordinates.
     *
     * @param nNonSub      the number of non-subscribers to generate
     * @param nVlibreUser  the number of Vlibre users to generate
     * @param nVmaxUser    the number of Vmax users to generate
     * @param maxX         the maximum value for the x-coordinate
     * @param maxY         the maximum value for the y-coordinate
     */
    public void generateUserList(int nNonSub, int nVlibreUser, int nVmaxUser, double maxX, double maxY){
        listOfUsers = new ArrayList<>();
        for (int i=0; i<nNonSub; i++){
            double[] gps = RandomVectorGenerator.getRandomCord(maxX, maxY);
            addNonSub(gps);
        }
        for (int i=0; i<nVlibreUser; i++){
            double[] gps = RandomVectorGenerator.getRandomCord(maxX, maxY);
            addVlibreUser(gps);
        }
        for (int i=0; i<nVmaxUser; i++) {
            double[] gps = RandomVectorGenerator.getRandomCord(maxX, maxY);
            addVmaxUser(gps);
        }
    }

    /**
     * Adds a street bike to the Velib system.
     *
     * @param b the bicycle to add
     */
    public void addStreetBike (Bicycle b){
        this.listOfStreetBikes.add(b);
    }

    /**
     * Removes a street bike from the Velib system.
     *
     * @param b the bicycle to remove
     */
    public void removeStreetBike (Bicycle b){
        this.listOfStreetBikes.remove(b);
    }

    /**
     * Creates a use case system with a predefined configuration.
     * The system includes 10 regular stations, 10 plus stations, 10 slots per station,
     * 40% vacant slots, 30% electrical bikes, and a maximum coordinate value of 10.
     *
     * @return the created VelibSystem instance
     */
    public static VelibSystem createUseCaseSystem(){
        VelibSystem vlibSys = new VelibSystem();
        vlibSys.generateStationMap(10, 10,10,0.4,0.3,10,10);
        vlibSys.generateUserList(20,10,10,10,10);
        vlibSys.listOfStreetBikes = new ArrayList<>();
        return vlibSys;
    }

    /**
     * Searches for a user in the Velib system based on the user ID.
     *
     * @param id the ID of the user to search for
     * @return the found user
     * @throws IllegalStateException if no user matching the ID was found
     */
    public User searchUserById(int id){
        for (User user:this.listOfUsers){
            if (user.getId()==id){return user;}
        }
        throw new IllegalStateException("no user Matching this id was found");
    }

    /**
     * Searches for a docking station in the Velib system based on the station ID.
     *
     * @param id the ID of the station to search for
     * @return the found docking station
     * @throws IllegalStateException if no station matching the ID was found
     */
    public DockingStation searchStationById(int id){
        for (DockingStation station:this.listOfStations){
            if (station.getId()==id){return station;}
        }
        throw new IllegalStateException("no Station Matching this id was found");
    }

    /**
     * Searches for a bicycle in the Velib system based on the GPS coordinates.
     * The search is performed on both street bikes and bikes in docking stations.
     *
     * @param gpsCord the GPS coordinates to search for
     * @return the found bicycle
     * @throws IllegalStateException if no bicycle was found at the given coordinates
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
     * Sorts the list of docking stations in ascending order based on the least occupied criteria.
     */
    public void leastOccupiedSort(){
        LeastOccupiedStationComparator comp = new LeastOccupiedStationComparator();
        Collections.sort(this.listOfStations,comp);
    }

    /**
     * Sorts the list of docking stations in descending order based on the most used criteria.
     */
    public void mostUsedSort(){
        MostUsedStationComparator comp = new MostUsedStationComparator();
        Collections.sort(this.listOfStations,comp);
    }

    /**
     * Returns a string representation of the Velib System.
     *
     * @return a string representation of the Velib System
     */
    @Override
    public String toString() {
        return "VelibSystem{" +
                "listOfStations=" + listOfStations +
                ", listOfStreetBikes=" + listOfStreetBikes +
                ", listOfUsers=" + listOfUsers +
                '}';
    }

    /**
     * Returns a summary of the Velib System.
     *
     * @return a summary of the Velib System
     */
    public void systemSummary(){
        System.out.println(this);
    }
}
