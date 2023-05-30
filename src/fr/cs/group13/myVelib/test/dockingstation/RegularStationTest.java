package fr.cs.group13.myVelib.test.dockingstation;

import fr.cs.group13.myVelib.core.cards.PricingVisitor;
import fr.cs.group13.myVelib.test.cards.TestPricingVisitor;
import fr.cs.group13.myVelib.core.dockingstation.RegularStation;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RegularStationTest {
    @Test
    void testAccept() {
        RegularStation regularStation = new RegularStation();
        PricingVisitor pricingVisitor = new TestPricingVisitor();

        double result = regularStation.accept(pricingVisitor);
        regularStation.getBalance();

        assertEquals(0, result);
    }
}