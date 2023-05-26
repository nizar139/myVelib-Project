package test.DockingStation;

import fr.cs.Group13.myVelib.Cards.PricingVisitor;
import fr.cs.Group13.myVelib.Cards.TestPricingVisitor;
import fr.cs.Group13.myVelib.DockingStation.RegularStation;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RegularStationTest {
    @Test
    void testAccept() {
        RegularStation regularStation = new RegularStation();
        PricingVisitor pricingVisitor = new TestPricingVisitor();

        double result = regularStation.accept(pricingVisitor);

        assertEquals(0, result);
    }
}