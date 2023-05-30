package test.DockingStation;

import core.Cards.PricingVisitor;
import test.Cards.TestPricingVisitor;
import core.DockingStation.PlusStation;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PlusStationTest {
    @Test
    void testAccept() {
        PlusStation plusStation = new PlusStation();
        PricingVisitor pricingVisitor = new TestPricingVisitor();
        System.out.println(plusStation);
        double result = plusStation.accept(pricingVisitor);

        assertEquals(0, result);
    }

}