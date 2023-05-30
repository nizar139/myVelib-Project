package test.Bicycle;

import core.Bicycle.ElectricalBicycle;
import core.Cards.PricingVisitor;
import test.Cards.TestPricingVisitor;
import core.DockingStation.DockingStation;
import core.DockingStation.ParkingSlot;
import core.DockingStation.RegularStation;
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