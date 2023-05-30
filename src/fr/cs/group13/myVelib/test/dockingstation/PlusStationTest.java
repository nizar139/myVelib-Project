package fr.cs.group13.myVelib.test.dockingstation;

import fr.cs.group13.myVelib.core.cards.PricingVisitor;
import fr.cs.group13.myVelib.test.cards.TestPricingVisitor;
import fr.cs.group13.myVelib.core.dockingstation.PlusStation;
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