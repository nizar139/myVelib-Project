package test.Planing;

import static org.junit.jupiter.api.Assertions.*;
import fr.cs.Group13.myVelib.Bicycle.Bicycle;
import fr.cs.Group13.myVelib.DockingStation.DockingStation;
import fr.cs.Group13.myVelib.DockingStation.PlusStation;
import fr.cs.Group13.myVelib.DockingStation.RegularStation;
import fr.cs.Group13.myVelib.DockingStation.StationStatus;
import fr.cs.Group13.myVelib.Planing.RideType;
import fr.cs.Group13.myVelib.Planing.RidesPlaning;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

class RideTypeTest {

    private RideType rideType;
    private ArrayList<Bicycle> bikes;
    private ArrayList<DockingStation> stations;
    private double[] startingGPS;
    private double[] endingGPS;
    private DockingStation station1;
    private DockingStation station2;
    private DockingStation station3;
    private DockingStation station4;
    private DockingStation station5;
    private DockingStation station6;
    private DockingStation station7;
    private DockingStation station8;

    @BeforeEach
    void setUp() {
        startingGPS = new double[]{0, 0};
        endingGPS = new double[]{10, 9};
        bikes = new ArrayList<Bicycle>();
        stations = new ArrayList<DockingStation>();

        station1 = new PlusStation(null,new double[]{9, 9},5,1,1);
        station2 = new RegularStation(null,new double[]{8, 9},5,3,1);
        station3 = new RegularStation(null,new double[]{1, 2},5,4,0);
        station4 = new PlusStation(null,new double[]{1, 0.5},5,4,0);
        station5 = new PlusStation(null,new double[]{10, 5},5,1,1);
        station6 = new PlusStation(null,new double[]{0, 0},5,3,1);
        station6.setStatus(StationStatus.OFFLINE);
        station7 = new RegularStation(null,new double[]{5, 5},5,3,1);
        station8 = new PlusStation(null,new double[]{8.96, 9},5,3,1);


        stations.add(station1);
        stations.add(station2);
        stations.add(station3);
        stations.add(station4);
        stations.add(station5);
        stations.add(station6);
        stations.add(station7);
        stations.add(station8);

        rideType = new RideType();
    }

    @Test
    void testMinimalWalkingDistance(){
        RidesPlaning plan = rideType.getYourStrat("MinimalWalkingDistance");

        String results = plan.getYourPlan(stations, bikes, startingGPS, endingGPS);

        assertEquals("Nearest starting station: " + station4 + ", Nearest ending station: " + station1, results);
    }

    @Test
    void testAvoidPlusStation(){
        RidesPlaning plan = rideType.getYourStrat("avoidplusstation");

        String results = plan.getYourPlan(stations, bikes, startingGPS, endingGPS);

        assertEquals("Nearest starting station: " + station4 + ", Nearest ending station avoiding PlusStation: " + station2, results);
    }

    @Test
    void testMechanicalOnlyRide(){
        RidesPlaning plan = rideType.getYourStrat("MechanicalOnlyRide");

        String results = plan.getYourPlan(stations, bikes, startingGPS, endingGPS);

        assertEquals("Nearest starting station with at least one Mechanical Bike: " + station4 + ", Nearest ending station: " + station1, results);
    }

    @Test
    void testElectricalOnlyRide(){
        RidesPlaning plan = rideType.getYourStrat("ElectricalOnlyRide");

        String results = plan.getYourPlan(stations, bikes, startingGPS, endingGPS);

        assertEquals("Nearest starting station with at least one Electrical Bike: " + station7 + ", Nearest ending station: " + station1, results);
    }

    @Test
    void testPreferPlusStation(){
        RidesPlaning plan = rideType.getYourStrat("PreferPlusStation");

        String results = plan.getYourPlan(stations, bikes, startingGPS, endingGPS);

        assertEquals("Nearest starting station: " + station4 + ", Nearest ending station prefering PlusStation: " + station1, results);
    }

    @Test
    void PreserveUniformityRide(){
        RidesPlaning plan = rideType.getYourStrat("PreserveUniformityRide");

        String result = plan.getYourPlan(stations, bikes, startingGPS, endingGPS);

        assertEquals("Nearest starting station : " + station4 + ", Nearest ending station: " + station8, result);
    }
}
