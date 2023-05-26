package test.DockingStation;

import fr.cs.Group13.myVelib.Cards.PricingVisitor;
import fr.cs.Group13.myVelib.Cards.TestPricingVisitor;
import fr.cs.Group13.myVelib.DockingStation.PlusStation;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PlusStationTest {
    @Test
    void testAccept() {
        PlusStation plusStation = new PlusStation();
        PricingVisitor pricingVisitor = new TestPricingVisitor();

        double result = plusStation.accept(pricingVisitor);

        assertEquals(0, result);
    }

}