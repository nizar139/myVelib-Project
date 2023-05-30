package test.Cards;

import core.Bicycle.ElectricalBicycle;
import core.Bicycle.MechanicalBicycle;
import core.Cards.Vmax;
import core.DockingStation.PlusStation;
import core.DockingStation.RegularStation;
import core.User.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class VmaxTest {
    private User user;
    private Vmax vmax;

    @BeforeEach
    void setUp() {
        user = new User("John");
        vmax = new Vmax(user);
    }

    @Test
    void testVisitElectricalBicycle() {
        ElectricalBicycle bicycle = new ElectricalBicycle();
        double[] result = vmax.visit(bicycle);

        assertEquals(0, result[0]);
        assertEquals(1, result[1]);
    }

    @Test
    void testVisitMechanicalBicycle() {
        MechanicalBicycle bicycle = new MechanicalBicycle();
        double[] result = vmax.visit(bicycle);

        assertEquals(0, result[0]);
        assertEquals(1, result[1]);
    }

    @Test
    void testVisitRegularStation() {
        RegularStation station = new RegularStation();
        double result = vmax.visit(station);

        assertEquals(0, result);
    }

    @Test
    void testVisitPlusStation() {
        PlusStation station = new PlusStation();
        double result = vmax.visit(station);

        assertEquals(5, result);
    }

    @Test
    void testComputeCharge() {
        MechanicalBicycle bicycle1 = new MechanicalBicycle();
        ElectricalBicycle bicycle2 = new ElectricalBicycle();
        double duration = 240;

        double result1 = vmax.computeCharge(bicycle1, 1, duration);
        double result2 = vmax.computeCharge(bicycle1, 0, duration);
        double result3 = vmax.computeCharge(bicycle2, 1, duration);
        double result4 = vmax.computeCharge(bicycle2, 0, duration);

        assertEquals(3, result1);
        assertEquals(3.3, result2);
        assertEquals(3, result3);
        assertEquals(3.3, result4);
    }

    @Test
    void testApplyBonus() {
        RegularStation station1 = new RegularStation();
        PlusStation station2 = new PlusStation();
        vmax.applyBonus(station1);
        assertEquals(0, user.getTotalTimeCredit());
        vmax.applyBonus(station2);
        assertEquals(5, user.getTotalTimeCredit());
    }
}