package test.System;

import fr.cs.Group13.myVelib.Bicycle.Bicycle;
import fr.cs.Group13.myVelib.Bicycle.ElectricalBicycle;
import fr.cs.Group13.myVelib.Cards.NoCard;
import fr.cs.Group13.myVelib.Cards.Vlibre;
import fr.cs.Group13.myVelib.Cards.Vmax;
import fr.cs.Group13.myVelib.DockingStation.DockingStation;
import fr.cs.Group13.myVelib.DockingStation.ParkingSlot;
import fr.cs.Group13.myVelib.DockingStation.PlusStation;
import fr.cs.Group13.myVelib.DockingStation.RegularStation;
import fr.cs.Group13.myVelib.System.RandomVectorGenerator;
import fr.cs.Group13.myVelib.System.VelibSystem;
import fr.cs.Group13.myVelib.User.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class VelibSystemTest {

    private VelibSystem velibSystem;
    private ArrayList<DockingStation> listOfStations;
    private ArrayList<Bicycle> listOfStreetBikes;
    private ArrayList<User> listOfUsers;

    @BeforeEach
    void setUp() {
        listOfStations = new ArrayList<>();
        listOfStreetBikes = new ArrayList<>();
        listOfUsers = new ArrayList<>();
        velibSystem = new VelibSystem(listOfStations, listOfStreetBikes, listOfUsers);
    }

    @Test
    void getListOfStations() {
        assertEquals(listOfStations, velibSystem.getListOfStations());
    }

    @Test
    void getListOfStreetBikes() {
        assertEquals(listOfStreetBikes, velibSystem.getListOfStreetBikes());
    }

    @Test
    void getListOfUsers() {
        assertEquals(listOfUsers, velibSystem.getListOfUsers());
    }

    @Test
    void resetSystem() {
        velibSystem.resetSystem();
        assertNull(velibSystem.getListOfStations());
        assertNull(velibSystem.getListOfStreetBikes());
        assertNull(velibSystem.getListOfUsers());
    }

    @Test
    void assignNewRegularStation() {
        double[] gpsCord = {1.0, 2.0};
        int nSlots = 10;
        double percentOfVacant = 0.5;
        double percentOfElectrical = 0.3;
        velibSystem.assignNewRegularStation(gpsCord, nSlots, percentOfVacant, percentOfElectrical);
        assertEquals(1, velibSystem.getListOfStations().size());
        DockingStation station = velibSystem.getListOfStations().get(0);
        assertEquals(gpsCord, station.getGpsCord());
        assertEquals(nSlots, station.getNumberOfSlots());
        assertEquals((int) (nSlots * percentOfVacant), station.getNumberOfVacantSlots());
        assertEquals((int) (nSlots * percentOfElectrical), station.getNumberOfElectricalBikes());
    }

    @Test
    void assignNewPlusStation() {
        double[] gpsCord = {1.0, 2.0};
        int nSlots = 10;
        double percentOfVacant = 0.5;
        double percentOfElectrical = 0.3;
        velibSystem.assignNewPlusStation(gpsCord, nSlots, percentOfVacant, percentOfElectrical);
        assertEquals(1, velibSystem.getListOfStations().size());
        DockingStation station = velibSystem.getListOfStations().get(0);
        assertEquals(gpsCord, station.getGpsCord());
        assertEquals(nSlots, station.getNumberOfSlots());
        assertEquals((int) (nSlots * percentOfVacant), station.getNumberOfVacantSlots());
        assertEquals((int) (nSlots * percentOfElectrical), station.getNumberOfElectricalBikes());
    }

    @Test
    void addNonSub() {
        double[] gpsCord = {1.0, 2.0};
        velibSystem.addNonSub(gpsCord);
        assertEquals(1, velibSystem.getListOfUsers().size());
        ArrayList<User> users = velibSystem.getListOfUsers();
        User user = users.get(0);
        assertEquals(gpsCord, user.getGpsCord());
        assertTrue(user.getCard() instanceof NoCard);
    }
    @Test
    void addVlibreUser() {
        double[] gpsCord = {1.0, 2.0};
        velibSystem.addVlibreUser(gpsCord);
        assertEquals(1, velibSystem.getListOfUsers().size());
        User user = velibSystem.getListOfUsers().get(0);
        assertEquals(gpsCord, user.getGpsCord());
        assertTrue(user.getCard() instanceof Vlibre);
    }

    @Test
    void addVmaxUser() {
        double[] gpsCord = {1.0, 2.0};
        velibSystem.addVmaxUser(gpsCord);
        assertEquals(1, velibSystem.getListOfUsers().size());
        User user = velibSystem.getListOfUsers().get(0);
        assertEquals(gpsCord, user.getGpsCord());
        assertTrue(user.getCard() instanceof Vmax);
    }

    @Test
    void getRandomCord() {
        double maxX = 10.0;
        double maxY = 10.0;
        double[] cord = RandomVectorGenerator.getRandomCord(maxX, maxY);
        assertNotNull(cord);
        assertTrue(cord[0] >= 0 && cord[0] <= maxX);
        assertTrue(cord[1] >= 0 && cord[1] <= maxY);
    }

    @Test
    void generateStationMap() {
        int nRegularStations = 10;
        int nPlusStations = 10;
        int nSlotsPerStation = 10;
        double percentOfVacant = 0.4;
        double percentOfElectrical = 0.3;
        double maxX = 10.0;
        double maxY = 10.0;
        velibSystem.generateStationMap(nRegularStations, nPlusStations, nSlotsPerStation, percentOfVacant, percentOfElectrical, maxX, maxY);
        assertEquals(nRegularStations + nPlusStations, velibSystem.getListOfStations().size());
        for (DockingStation station : velibSystem.getListOfStations()) {
            assertEquals(nSlotsPerStation, station.getNumberOfSlots());
            assertEquals((int) (nSlotsPerStation * percentOfVacant), station.getNumberOfVacantSlots());
            assertEquals((int) (nSlotsPerStation * percentOfElectrical), station.getNumberOfElectricalBikes());
        }
    }

    @Test
    void generateUserList() {
        int nNonSub = 20;
        int nVlibreUser = 10;
        int nVmaxUser = 10;
        double maxX = 10.0;
        double maxY = 10.0;
        velibSystem.generateUserList(nNonSub, nVlibreUser, nVmaxUser, maxX, maxY);
        assertEquals(nNonSub + nVlibreUser + nVmaxUser, velibSystem.getListOfUsers().size());
        for (User user : velibSystem.getListOfUsers()) {
            assertNotNull(user.getGpsCord());
            assertNotNull(user.getCard());
        }
    }

    @Test
    void addStreetBike() {
        Bicycle bike = new ElectricalBicycle();
        velibSystem.addStreetBike(bike);
        assertTrue(velibSystem.getListOfStreetBikes().contains(bike));
    }

    @Test
    void removeStreetBike() {
        Bicycle bike = new ElectricalBicycle();
        velibSystem.getListOfStreetBikes().add(bike);
        velibSystem.removeStreetBike(bike);
        assertFalse(velibSystem.getListOfStreetBikes().contains(bike));
    }

    @Test
    void createUseCaseSys() {
        VelibSystem velibSys = VelibSystem.createUseCaseSystem();
        assertNotNull(velibSys.getListOfStations());
        assertNotNull(velibSys.getListOfStreetBikes());
        assertNotNull(velibSys.getListOfUsers());
    }

    @Test
    void searchUserById() {
        User user1 = new User(velibSystem, new double[]{1.0, 2.0});
        User user2 = new User(velibSystem, new double[]{3.0, 4.0});
        User user3 = new User(velibSystem, new double[]{5.0, 6.0});
        velibSystem.getListOfUsers().add(user1);
        velibSystem.getListOfUsers().add(user2);
        velibSystem.getListOfUsers().add(user3);
        assertEquals(user2, velibSystem.searchUserById(user2.getId()));
        assertThrows(IllegalStateException.class, () -> velibSystem.searchUserById(100));
    }

    @Test
    void searchStationById() {
        DockingStation station1 = new RegularStation(velibSystem, new double[]{1.0, 2.0}, 10, 5, 3);
        DockingStation station2 = new PlusStation(velibSystem, new double[]{3.0, 4.0}, 10, 7, 4);
        DockingStation station3 = new RegularStation(velibSystem, new double[]{5.0, 6.0}, 10, 8, 2);
        velibSystem.getListOfStations().add(station1);
        velibSystem.getListOfStations().add(station2);
        velibSystem.getListOfStations().add(station3);
        assertEquals(station2, velibSystem.searchStationById(station2.getId()));
        assertThrows(IllegalStateException.class, () -> velibSystem.searchStationById(100));
    }

    @Test
    void searchBicycleByGPS() {
        double[] gpsCord1 = {1.0, 2.0};
        double[] gpsCord2 = {3.0, 4.0};
        double[] gpsCord3 = {5.0, 6.0};
        Bicycle bike1 = new ElectricalBicycle();
        Bicycle bike2 = new ElectricalBicycle();
        bike2.setGpsCord(gpsCord2);
        Bicycle bike3 = new ElectricalBicycle();
        bike3.setGpsCord(gpsCord3);
        velibSystem.getListOfStreetBikes().add(bike1);
        velibSystem.getListOfStreetBikes().add(bike2);
        velibSystem.getListOfStreetBikes().add(bike3);
        assertEquals(bike2, velibSystem.searchBicycleByGPS(gpsCord2));
        assertThrows(IllegalStateException.class, () -> velibSystem.searchBicycleByGPS(gpsCord1));
        DockingStation station = new RegularStation(velibSystem, gpsCord1, 10, 5, 3);
        ParkingSlot slot = new ParkingSlot(station);
        slot.putBicycle(bike1);
        assertEquals(bike1, velibSystem.searchBicycleByGPS(gpsCord1));
    }

    @Test
    void toStringTest() {
        String expected = "VlibSystem{" +
                "listOfStations=" + listOfStations +
                ", listOfStreetBikes=" + listOfStreetBikes +
                ", listOfUsers=" + listOfUsers +
                '}';
        assertEquals(expected, velibSystem.toString());
    }

    @Test
    void systemSummary() {
        // Just test if the method runs without errors
        velibSystem.systemSummary();
    }

}
