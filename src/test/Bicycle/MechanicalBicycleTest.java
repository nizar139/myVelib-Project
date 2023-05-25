package test.Bicycle;


import fr.cs.Group13.myVelib.Bicycle.MechanicalBicycle;
import fr.cs.Group13.myVelib.DockingStation.DockingStation;
import fr.cs.Group13.myVelib.DockingStation.ParkingSlot;
import fr.cs.Group13.myVelib.DockingStation.RegularStation;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class MechanicalBicycleTest {
    @Test
    void testBikeCountFree() {
        DockingStation station = new RegularStation();
        ParkingSlot slot = new ParkingSlot(station);
        MechanicalBicycle bicycle = new MechanicalBicycle();
        bicycle.setBikeCountFree(slot);

        assertEquals(1, station.getNumberOfVacantSlots());
        assertEquals(-1, station.getNumberOfMechanicalBikes());
    }

    @Test
    void testBikeCountFill() {
        DockingStation station = new RegularStation();
        ParkingSlot slot = new ParkingSlot(station);
        MechanicalBicycle bicycle = new MechanicalBicycle();
        bicycle.setBikeCountFill(slot);

        assertEquals(-1, station.getNumberOfVacantSlots());
        assertEquals(1, station.getNumberOfMechanicalBikes());
    }
}