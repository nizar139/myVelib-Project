package fr.cs.group13.myVelib.test.cards;

import fr.cs.group13.myVelib.core.bicycle.ElectricalBicycle;
import fr.cs.group13.myVelib.core.bicycle.MechanicalBicycle;
import fr.cs.group13.myVelib.core.cards.Vlibre;
import fr.cs.group13.myVelib.core.dockingstation.PlusStation;
import fr.cs.group13.myVelib.core.dockingstation.RegularStation;
import fr.cs.group13.myVelib.core.user.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class VlibreTest {
    private User user;
    private Vlibre vlibre;

    @BeforeEach
    void setUp() {
        user = new User("John");
        vlibre = new Vlibre(user);
    }

    @Test
    void testVisitElectricalBicycle() {
        ElectricalBicycle bicycle = new ElectricalBicycle();
        double[] result = vlibre.visit(bicycle);

        assertEquals(1, result[0]);
        assertEquals(2, result[1]);
    }

    @Test
    void testVisitMechanicalBicycle() {
        MechanicalBicycle bicycle = new MechanicalBicycle();
        double[] result = vlibre.visit(bicycle);

        assertEquals(0, result[0]);
        assertEquals(1, result[1]);
    }

    @Test
    void testVisitRegularStation() {
        RegularStation station = new RegularStation();
        double result = vlibre.visit(station);

        assertEquals(0, result);
    }

    @Test
    void testVisitPlusStation() {
        PlusStation station = new PlusStation();
        double result = vlibre.visit(station);

        assertEquals(5, result);
    }

    @Test
    void testComputeCharge() {
        MechanicalBicycle bicycle1 = new MechanicalBicycle();
        ElectricalBicycle bicycle2 = new ElectricalBicycle();
        double duration = 120;

        double result1 = vlibre.computeCharge(bicycle1, 1, duration);
        double result2 = vlibre.computeCharge(bicycle1, 0, duration);
        double result3 = vlibre.computeCharge(bicycle2, 1, duration);
        double result4 = vlibre.computeCharge(bicycle2, 0, duration);

        assertEquals(1, result1);
        assertEquals(1.1, result2);
        assertEquals(3, result3);
        assertEquals(3.3, result4);
    }

    @Test
    void testApplyBonus() {
        RegularStation station1 = new RegularStation();
        PlusStation station2 = new PlusStation();
        vlibre.applyBonus(station1);
        assertEquals(0, user.getTotalTimeCredit());
        vlibre.applyBonus(station2);
        assertEquals(5, user.getTotalTimeCredit());
    }
}