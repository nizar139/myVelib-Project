package fr.cs.group13.myVelib.test.dockingstation;

import fr.cs.group13.myVelib.core.bicycle.Bicycle;
import fr.cs.group13.myVelib.core.bicycle.ElectricalBicycle;
import fr.cs.group13.myVelib.core.bicycle.MechanicalBicycle;
import fr.cs.group13.myVelib.core.dockingstation.DockingStation;
import fr.cs.group13.myVelib.core.dockingstation.ParkingSlot;
import fr.cs.group13.myVelib.core.dockingstation.RegularStation;
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