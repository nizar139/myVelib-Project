package test.Planing;

import static org.junit.jupiter.api.Assertions.*;
import fr.cs.Group13.myVelib.Bicycle.Bicycle;
import fr.cs.Group13.myVelib.DockingStation.DockingStation;
import fr.cs.Group13.myVelib.DockingStation.PlusStation;
import fr.cs.Group13.myVelib.DockingStation.RegularStation;
import fr.cs.Group13.myVelib.Planing.RideType;
import fr.cs.Group13.myVelib.Planing.RidesPlaning;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.print.Doc;
import java.util.ArrayList;

class RideTypeTest {

    @BeforeEach
    void setUp() {
        double[] startingGPS = new double[]{0, 0};
        double[] endingGPS = new double[]{10, 9};
        ArrayList<Bicycle> bikes = new ArrayList<Bicycle>();
        ArrayList<DockingStation> stations = new ArrayList<DockingStation>();

        DockingStation station1 = new PlusStation();
        station1.setGpsCord(new double[]{10, 9});
        DockingStation station2 = new RegularStation();
        station2.setGpsCord(new double[]{8, 9});
        DockingStation station3 = new RegularStation();
        station3.setGpsCord(new double[]{1, 2});
        DockingStation station4 = new PlusStation();
        station4.setGpsCord(new double[]{1, 0.5});
        DockingStation station5 = new PlusStation();
        station5.setGpsCord(new double[]{10, 5});

        stations.add(station1);
        stations.add(station2);
        stations.add(station3);
        stations.add(station4);
        stations.add(station5);

        RideType type = new RideType();
    }
    @Test
    void testMinimalWalkingDistance(){
        RidesPlaning plan = type.getYourStrat("MinimalWalkingDistance");

        String results = plan.getYourPlan(stations, bikes, startingGPS, endingGPS);

        assertEquals("Nearest starting station: " + station4 + ", Nearest ending station: " + station1,results);
    }
}