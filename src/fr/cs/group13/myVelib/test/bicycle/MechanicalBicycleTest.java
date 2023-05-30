package fr.cs.group13.myVelib.test.bicycle;


import fr.cs.group13.myVelib.core.bicycle.MechanicalBicycle;
import fr.cs.group13.myVelib.core.dockingstation.DockingStation;
import fr.cs.group13.myVelib.core.dockingstation.ParkingSlot;
import fr.cs.group13.myVelib.core.dockingstation.RegularStation;
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