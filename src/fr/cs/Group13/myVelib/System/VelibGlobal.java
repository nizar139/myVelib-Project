package fr.cs.Group13.myVelib.System;

import fr.cs.Group13.myVelib.Cards.NoCard;
import fr.cs.Group13.myVelib.Cards.Vlibre;
import fr.cs.Group13.myVelib.Cards.Vmax;
import fr.cs.Group13.myVelib.DockingStation.DockingStation;
import fr.cs.Group13.myVelib.DockingStation.RegularStation;
import fr.cs.Group13.myVelib.DockingStation.StationStatus;
import fr.cs.Group13.myVelib.User.User;

import java.util.ArrayList;
import java.util.Random;

public class VelibGlobal {
    private ArrayList<VelibSystem> listOfVelibSystems;
    private static VelibGlobal instance;
    private VelibGlobal(){
        this.listOfVelibSystems = new ArrayList<>();
    }

    public static VelibGlobal getInstance(){
        if (instance == null){
            instance = new VelibGlobal();
            return instance;
        }
        return instance;
    }
    public static VelibSystem searchSystemByName(String name){
        for (VelibSystem velibSys: instance.listOfVelibSystems){
            if (name.equalsIgnoreCase(velibSys.getName())){
                return velibSys;
            }
        }
        throw new RuntimeException("No station matching the given name was found");
    }
//    public VelibSystem setupVelib(String name){
//        VelibSystem velibSys = new VelibSystem(name);
//        velibSys.generateStationMap(5,5,10, 0.25, 0.375,4,4);
//        return velibSys;
//    }
    public VelibSystem setupVelib(String name, int nstations, int nslots, double s, int nbikes){
        int totalBikes = nbikes;
        VelibSystem velibSys = new VelibSystem(name);
        Random random = new Random();
        int nregular = random.nextInt(nstations+1);
        int nplus = nstations - nregular;
        for (int i=0; i<nregular; i++) {
            double[] gpsCord = VelibSystem.getRandomCord(s, s);
            int stationNbrOfBikes = random.nextInt(totalBikes + 1);
            int stationNbrOfElectrical = random.nextInt(stationNbrOfBikes + 1);
            RegularStation station = new RegularStation(velibSys, gpsCord, nslots, nslots - stationNbrOfBikes, stationNbrOfElectrical);
            velibSys.addStation(station);
            totalBikes = totalBikes - stationNbrOfBikes;
        }
        for (int i=0; i<nplus; i++) {
            double[] gpsCord = VelibSystem.getRandomCord(s, s);
            int stationNbrOfBikes = random.nextInt(totalBikes + 1);
            int stationNbrOfElectrical = random.nextInt(stationNbrOfBikes + 1);
            RegularStation station = new RegularStation(velibSys, gpsCord, nslots, nslots - stationNbrOfBikes, stationNbrOfElectrical);
            velibSys.addStation(station);
            totalBikes = totalBikes - stationNbrOfBikes;
        }
        this.listOfVelibSystems.add(velibSys);
        return velibSys;
    }

    public void setOffline(String networkName, int stationId){
        VelibSystem velibSys = searchSystemByName(networkName);
        DockingStation station = velibSys.searchStationById(stationId);
        station.setStatus(StationStatus.OFFLINE);
    }
    public void setOnline(String networkName, int stationId){
        VelibSystem velibSys = searchSystemByName(networkName);
        DockingStation station = velibSys.searchStationById(stationId);
        station.setStatus(StationStatus.ONSERVICE);
    }
    public void addUserNoCard(String name, String networkName){
        VelibSystem velibSys = searchSystemByName(networkName);
        User user = new User(name);
        NoCard card = new NoCard(user);
        user.setCard(card);
    }
    public void addUserVlibre(String name, String networkName){
        VelibSystem velibSys = searchSystemByName(networkName);
        User user = new User(name);
        Vlibre card = new Vlibre(user);
        user.setCard(card);
    }
    public void addUserVmax(String name, String networkName){
        VelibSystem velibSys = searchSystemByName(networkName);
        User user = new User(name);
        Vmax card = new Vmax(user);
        user.setCard(card);
    }
}