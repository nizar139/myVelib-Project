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

import javax.print.Doc;
import java.util.ArrayList;
import java.util.Random;
public class VelibSystem {
    private int id;
    private static int count = 0;
    private String name;
    private ArrayList<DockingStation> listOfStations;
    private ArrayList<Bicycle> listOfStreetBikes;
    private ArrayList<User> listOfUsers;
    private static int getNextId(){
        count++;
        return count;
    }

    public VelibSystem() {
        this.id = getNextId();
    }
    public VelibSystem(String name) {
        this.id = getNextId();
        this.name = name;
    }

    public VelibSystem(ArrayList<DockingStation> listOfStations, ArrayList<Bicycle> listOfStreetBikes, ArrayList<User> listOfUsers) {
        this.id = getNextId();
        this.listOfStations = listOfStations;
        this.listOfStreetBikes = listOfStreetBikes;
        this.listOfUsers = listOfUsers;
    }
    public VelibSystem(String name, ArrayList<DockingStation> listOfStations, ArrayList<Bicycle> listOfStreetBikes, ArrayList<User> listOfUsers) {
        this.id = getNextId();
        this.name = name;
        this.listOfStations = listOfStations;
        this.listOfStreetBikes = listOfStreetBikes;
        this.listOfUsers = listOfUsers;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void resetSystem(){

        this.listOfStations = null;
        this.listOfUsers = null;
        this.listOfStreetBikes = null;
    }

    public void assignNewRegularStation(double[] gpsCord,int nSlots, double percentOfVacant, double percentOfElectrical){
        double v = nSlots * percentOfVacant;
        double w = nSlots*percentOfElectrical;
        RegularStation station = new RegularStation(this, gpsCord, nSlots,(int) v,(int) w);
        this.listOfStations.add(station);
    }
    public void assignNewPlusStation(double[] gpsCord,int nSlots, double percentOfVacant, double percentOfElectrical){
        double v = nSlots * percentOfVacant;
        double w = nSlots*percentOfElectrical;
        PlusStation station = new PlusStation(this, gpsCord, nSlots,(int) v,(int) w);
        this.listOfStations.add(station);
    }
    public void addStation(DockingStation station){
        this.listOfStations.add(station);
    }
    public void addUser(User user){
        this.listOfUsers.add(user);
    }
    public void addNonSub(double[] gpsCord){
        User user = new User(this, gpsCord);
        NoCard card = new NoCard(user);
        user.setCard(card);
        this.listOfUsers.add(user);
    }
    public void addVlibreUser(double[] gpsCord){
        User user = new User(this, gpsCord);
        Vlibre card = new Vlibre(user);
        user.setCard(card);
        this.listOfUsers.add(user);
    }
    public void addVmaxUser(double[] gpsCord) {
        User user = new User(this, gpsCord);
        Vmax card = new Vmax(user);
        user.setCard(card);
        this.listOfUsers.add(user);
    }
    public static double[] getRandomCord(double maxX, double maxY){
        Random random = new Random();
        double x = random.nextDouble()*maxX;
        double y = random.nextDouble()*maxY;
        return new double[]{x, y};
    }
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
    public void addStreetBike (Bicycle b){
        this.listOfStreetBikes.add(b);
    }
    public void removeStreetBike (Bicycle b){
        this.listOfStreetBikes.remove(b);
    }
    public static VelibSystem createUseCaseSys(){
        VelibSystem vlibSys = new VelibSystem();
        vlibSys.generateStationMap(10, 10,10,0.4,0.3,10,10);
        vlibSys.generateUserList(20,10,10,10,10);
        vlibSys.listOfStreetBikes = new ArrayList<>();
        return vlibSys;
    }

    public User searchUserById(int id){
        for (User user:this.listOfUsers){
            if (user.getId()==id){return user;}
        }
        throw new IllegalStateException("no user Matching this id was found");
    }
    public DockingStation searchStationById(int id){
        for (DockingStation station:this.listOfStations){
            if (station.getId()==id){return station;}
        }
        throw new IllegalStateException("no user Matching this id was found");
    }
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

    @Override
    public String toString() {
        return "VlibSystem{" +
                "listOfStations=" + listOfStations +
                ", listOfStreetBikes=" + listOfStreetBikes +
                ", listOfUsers=" + listOfUsers +
                '}';
    }
    public void systemSummary(){
        System.out.println(this);
    }
}
