package fr.cs.group13.myVelib.test.bicycle;

import fr.cs.group13.myVelib.core.bicycle.ElectricalBicycle;
import fr.cs.group13.myVelib.core.cards.PricingVisitor;
import fr.cs.group13.myVelib.test.cards.TestPricingVisitor;
import fr.cs.group13.myVelib.core.dockingstation.DockingStation;
import fr.cs.group13.myVelib.core.dockingstation.ParkingSlot;
import fr.cs.group13.myVelib.core.dockingstation.RegularStation;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;



class ElectricalBicycleTest {
    @Test
    void testAccept() {
        ElectricalBicycle bicycle = new ElectricalBicycle();
        PricingVisitor visitor = new TestPricingVisitor();

        assertEquals(0, bicycle.accept(visitor).length);
    }

    @Test
    void testBikeCountFree() {
        DockingStation station = new RegularStation();
        ParkingSlot slot = new ParkingSlot(station);
        ElectricalBicycle bicycle = new ElectricalBicycle();
        bicycle.setBikeCountFree(slot);

        assertEquals(1, station.getNumberOfVacantSlots());
        assertEquals(-1, station.getNumberOfElectricalBikes());
    }

    @Test
    void testBikeCountFill() {
        DockingStation station = new RegularStation();
        ParkingSlot slot = new ParkingSlot(station);
        ElectricalBicycle bicycle = new ElectricalBicycle();
        bicycle.setBikeCountFill(slot);

        assertEquals(-1, station.getNumberOfVacantSlots());
        assertEquals(1, station.getNumberOfElectricalBikes());
    }

    @Test
    void testRemoveFromSlot(){
        DockingStation station = new RegularStation();
        ElectricalBicycle bicycle = new ElectricalBicycle();
        ParkingSlot slot = new ParkingSlot(station, bicycle);
        bicycle.removeFromSlot();

        assertEquals(null, bicycle.getSlot());
    }

    @Test
    void testAddToSlot(){
        DockingStation station = new RegularStation();
        ElectricalBicycle bicycle = new ElectricalBicycle();
        ParkingSlot slot = new ParkingSlot(station);
        bicycle.addToSlot(slot);

        assertEquals(slot, bicycle.getSlot());
        assertEquals(1, bicycle.getFromAStation());
        assertEquals(station.getGpsCord(), bicycle.getGpsCord());
    }
}