package fr.cs.Group13.myVelib.System;

import fr.cs.Group13.myVelib.Bicycle.Bicycle;
import fr.cs.Group13.myVelib.Cards.NoCard;
import fr.cs.Group13.myVelib.Cards.Vlibre;
import fr.cs.Group13.myVelib.Cards.Vmax;
import fr.cs.Group13.myVelib.DockingStation.DockingStation;
import fr.cs.Group13.myVelib.DockingStation.PlusStation;
import fr.cs.Group13.myVelib.DockingStation.RegularStation;
import fr.cs.Group13.myVelib.User.User;

import java.util.ArrayList;
import java.util.Random;
public class VlibSystem {
    private ArrayList<DockingStation> listOfStations;
    private ArrayList<Bicycle> listOfStreetBikes;
    private ArrayList<User> listOfUsers;

    private VlibSystem() {}

    public VlibSystem(ArrayList<DockingStation> listOfStations, ArrayList<Bicycle> listOfStreetBikes, ArrayList<User> listOfUsers) {
        this.listOfStations = listOfStations;
        this.listOfStreetBikes = listOfStreetBikes;
        this.listOfUsers = listOfUsers;
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
    public double[] getRandomCord(double maxX, double maxY){
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
    public static VlibSystem createUseCaseSys(){
        VlibSystem vlibSys = new VlibSystem();
        vlibSys.generateStationMap(10, 10,10,0.4,0.3,10,10);
        vlibSys.generateUserList(20,10,10,10,10);
        vlibSys.listOfStreetBikes = new ArrayList<>();
        return vlibSys;
    }

}
