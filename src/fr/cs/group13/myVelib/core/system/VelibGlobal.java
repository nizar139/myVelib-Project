/**
 * The VelibGlobal class represents the global management system for Velib networks.
 * It manages the creation and retrieval of Velib systems and provides various operations
 * to interact with the systems and their components.
 *
 * @author Oussama and Nizar
 */
package fr.cs.group13.myVelib.core.system;

import fr.cs.group13.myVelib.core.cards.NoCard;
import fr.cs.group13.myVelib.core.cards.Vlibre;
import fr.cs.group13.myVelib.core.cards.Vmax;
import fr.cs.group13.myVelib.core.dockingstation.DockingStation;
import fr.cs.group13.myVelib.core.dockingstation.PlusStation;
import fr.cs.group13.myVelib.core.dockingstation.RegularStation;
import fr.cs.group13.myVelib.core.dockingstation.StationStatus;
import fr.cs.group13.myVelib.core.user.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class VelibGlobal {
    private ArrayList<VelibSystem> listOfVelibSystems;
    private static VelibGlobal instance;

    /**
     * Private constructor to prevent direct instantiation of the class.
     * Initializes the list of Velib systems.
     */
    private VelibGlobal(){
        this.listOfVelibSystems = new ArrayList<>();
    }

    /**
     * method to add Velib System to the list of systems in VelibGlobal.
     *
     * @param system Velib system to be add
     */
    public void addVelibSystem(VelibSystem system){
        this.listOfVelibSystems.add(system);
    }

    /**
     * Returns the list of Velib systems.
     *
     * @return the list of Velib systems
     */
    public ArrayList<VelibSystem> getListOfVelibSystems() {
        return listOfVelibSystems;
    }

    /**
     * Returns the singleton instance of the VelibGlobal class.
     *
     * @return the singleton instance of VelibGlobal
     */
    public static VelibGlobal getInstance(){
        if (instance == null){
            instance = new VelibGlobal();
            return instance;
        }
        return instance;
    }

    /**
     * Returns the Velib system with the specified ID.
     *
     * @param id the ID of the Velib system
     * @return the Velib system with the specified ID
     * @throws RuntimeException if no Velib system is found with the given ID
     */
    public VelibSystem searchSystemById(int id){
        for (VelibSystem s:this.listOfVelibSystems){
            if (id == s.getId()){
                return s;
            }
        }
        throw new RuntimeException("there is no Velib System with this id");
    }

    /**
     * Searches for a Velib system with the given name.
     *
     * @param name the name of the Velib system to search for
     * @return the Velib system with the given name
     * @throws RuntimeException if no Velib system is found with the given name
     */
    public VelibSystem searchSystemByName(String name){
        for (VelibSystem velibSys: instance.listOfVelibSystems){
            if (name.equalsIgnoreCase(velibSys.getName())){
                return velibSys;
            }
        }
        throw new RuntimeException("No Velib Network matching the given name was found");
    }

    /**
     * Searches for a user with the given ID in all Velib systems.
     *
     * @param userId the ID of the user to search for
     * @return an array containing the Velib system and the user if found
     * @throws RuntimeException if no user is found with the given ID in any Velib network
     */
    public Object[] searchSystemByUserId(int userId){
        VelibSystem velibSystem = null;
        User user = null;
        int i = 0;
        int max_i = this.listOfVelibSystems.size();
        while (i<max_i && user==null){
            try {
                velibSystem = this.listOfVelibSystems.get(i);
                user = velibSystem.searchUserById(userId);
                max_i++;
            }
            catch (Exception e){
                max_i ++;
            }
        }
        if (user == null){
            throw new RuntimeException("no user matching this Id was found in any velibNetwork");
        }
        return new Object[]{velibSystem, user};
    }

    /**
     * Searches for a docking station with the given ID in all Velib systems.
     *
     * @param stationId the ID of the docking station to search for
     * @return an array containing the Velib system and the docking station if found
     * @throws RuntimeException if no docking station is found with the given ID in any Velib network
     */
    public Object[] searchSystembyStationId(int stationId){
        VelibSystem velibSystem = null;
        DockingStation station = null;
        int i = 0;
        int maxI = this.listOfVelibSystems.size();
        while (i<maxI && station==null){
            try {
                velibSystem = this.listOfVelibSystems.get(i);
                station = velibSystem.searchStationById(stationId);
                i++;
            }
            catch (Exception e){
                i++;
            }
        }
        if (station == null){
            throw new RuntimeException("no station matching this Id was found in any velibNetwork");
        }
        return new Object[]{velibSystem, station};
    }

    /**
     * Sets up a Velib network with the specified parameters.
     *
     * @param name       the name of the Velib network
     * @param nstations  the number of stations in the network
     * @param nslots     the number of slots per station
     * @param s          the size of the network area
     * @param nbikes     the total number of bikes in the network
     * @return the created VelibSystem object
     * @throws RuntimeException if the total number of bikes is higher than the total number of parking slots
     */
    public VelibSystem setupVelib(String name, int nstations, int nslots, double s, int nbikes){
        if (nstations*nslots<nbikes){
            throw new RuntimeException("The total number of bicycle is higher than the total number of parking slots!");
        }
        List<Integer> nbikesVector = RandomVectorGenerator.generateVector(nstations, nslots, nbikes);
        VelibSystem velibSys = new VelibSystem(name);
        Random random = new Random();
        int nregular = random.nextInt(nstations+1);
        int nplus = nstations - nregular;
        for (int i=0; i<nregular; i++) {
            double[] gpsCord = RandomVectorGenerator.getRandomCord(s, s);
            int stationNbrOfBikes = nbikesVector.get(i);
            int stationNbrOfElectrical = random.nextInt(stationNbrOfBikes + 1);
            RegularStation station = new RegularStation(velibSys, gpsCord, nslots, nslots - stationNbrOfBikes, stationNbrOfElectrical);
            velibSys.addStation(station);
        }
        for (int i=nregular; i<nregular+nplus; i++) {
            double[] gpsCord = RandomVectorGenerator.getRandomCord(s, s);
            int stationNbrOfBikes = nbikesVector.get(i);
            int stationNbrOfElectrical = random.nextInt(stationNbrOfBikes + 1);
            PlusStation station = new PlusStation(velibSys, gpsCord, nslots, nslots - stationNbrOfBikes, stationNbrOfElectrical);
            velibSys.addStation(station);
        }
        this.listOfVelibSystems.add(velibSys);
        return velibSys;
    }

    /**
     * Sets a docking station in the specified Velib network to offline status.
     *
     * @param networkName the name of the Velib network
     * @param stationId   the ID of the docking station
     * @throws RuntimeException if no Velib network is found with the given name or no docking station is found with the given ID
     */
    public void setOffline(String networkName, int stationId){
        VelibSystem velibSys = searchSystemByName(networkName);
        DockingStation station = velibSys.searchStationById(stationId);
        station.setStatus(StationStatus.OFFLINE);
    }

    /**
     * Sets a docking station in the specified Velib network to online status.
     *
     * @param networkName the name of the Velib network
     * @param stationId   the ID of the docking station
     * @throws RuntimeException if no Velib network is found with the given name or no docking station is found with the given ID
     */
    public void setOnline(String networkName, int stationId){
        VelibSystem velibSys = searchSystemByName(networkName);
        DockingStation station = velibSys.searchStationById(stationId);
        station.setStatus(StationStatus.ONSERVICE);
    }

    /**
     * Adds a user with a NoCard to the specified Velib network.
     *
     * @param name        the name of the user
     * @param networkName the name of the Velib network
     * @throws RuntimeException if no Velib network is found with the given name
     */
    public void addUserNoCard(String name, String networkName){
        VelibSystem velibSys = searchSystemByName(networkName);
        User user = new User(name);
        NoCard card = new NoCard(user);
        user.setCard(card);
        velibSys.addUser(user);
    }

    /**
     * Adds a user with a Vlibre card to the specified Velib network.
     *
     * @param name        the name of the user
     * @param networkName the name of the Velib network
     * @throws RuntimeException if no Velib network is found with the given name
     */
    public void addUserVlibre(String name, String networkName){
        VelibSystem velibSys = searchSystemByName(networkName);
        User user = new User(name);
        Vlibre card = new Vlibre(user);
        user.setCard(card);
        velibSys.addUser(user);
    }

    /**
     * Adds a user with a Vmax card to the specified Velib network.
     *
     * @param name        the name of the user
     * @param networkName the name of the Velib network
     */
    public void addUserVmax(String name, String networkName){
        VelibSystem velibSys = searchSystemByName(networkName);
        User user = new User(name);
        Vmax card = new Vmax(user);
        user.setCard(card);
        velibSys.addUser(user);
    }
}
