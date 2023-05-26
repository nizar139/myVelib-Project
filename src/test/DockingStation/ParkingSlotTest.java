package test.DockingStation;

import fr.cs.Group13.myVelib.Bicycle.Bicycle;
import fr.cs.Group13.myVelib.Bicycle.ElectricalBicycle;
import fr.cs.Group13.myVelib.Bicycle.MechanicalBicycle;
import fr.cs.Group13.myVelib.DockingStation.DockingStation;
import fr.cs.Group13.myVelib.DockingStation.ParkingSlot;
import fr.cs.Group13.myVelib.DockingStation.RegularStation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ParkingSlotTest {
    private DockingStation station;
    private ParkingSlot slot;
    private Bicycle b;

    @BeforeEach
    void setUp() {
        station = new RegularStation();
        b = new ElectricalBicycle();
    }

    @Test
    void testFreeSlot(){
        slot = new ParkingSlot(station,b);
        slot.freeSlot();

        assertEquals(false, slot.isOccupied());
        assertEquals(null,slot.getBicycle());

    }

    @Test
    void testPutBicycle(){
        slot = new ParkingSlot(station);
        slot.putBicycle(b);

        assertEquals(true, slot.isOccupied());
        assertEquals(b,slot.getBicycle());

        Bicycle bike = new MechanicalBicycle();
        IllegalStateException exception = assertThrows(IllegalStateException.class,
                () -> slot.putBicycle(bike));
        assertEquals("Slot is already occupied", exception.getMessage());

    }
}