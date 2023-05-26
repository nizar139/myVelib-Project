package test.Bicycle;

import fr.cs.Group13.myVelib.Bicycle.ElectricalBicycle;
import fr.cs.Group13.myVelib.Cards.PricingVisitor;
import fr.cs.Group13.myVelib.Cards.TestPricingVisitor;
import fr.cs.Group13.myVelib.DockingStation.DockingStation;
import fr.cs.Group13.myVelib.DockingStation.ParkingSlot;
import fr.cs.Group13.myVelib.DockingStation.RegularStation;
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