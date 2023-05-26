package test.Cards;


import fr.cs.Group13.myVelib.Bicycle.ElectricalBicycle;
import fr.cs.Group13.myVelib.Bicycle.MechanicalBicycle;
import fr.cs.Group13.myVelib.Cards.NoCard;
import fr.cs.Group13.myVelib.DockingStation.PlusStation;
import fr.cs.Group13.myVelib.DockingStation.RegularStation;
import fr.cs.Group13.myVelib.User.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class NoCardTest {
    private User user;
    private NoCard noCard;

    @BeforeEach
    void setUp() {
        user = new User("John");
        noCard = new NoCard(user);
    }

    @Test
    void testVisitElectricalBicycle() {
        ElectricalBicycle bicycle = new ElectricalBicycle();
        double[] result = noCard.visit(bicycle);

        assertEquals(2, result[0]);
        assertEquals(2, result[1]);
    }

    @Test
    void testVisitMechanicalBicycle() {
        MechanicalBicycle bicycle = new MechanicalBicycle();
        double[] result = noCard.visit(bicycle);

        assertEquals(1, result[0]);
        assertEquals(1, result[1]);
    }

    @Test
    void testVisitRegularStation() {
        RegularStation station = new RegularStation();
        double result = noCard.visit(station);

        assertEquals(0, result);
    }

    @Test
    void testVisitPlusStation() {
        PlusStation station = new PlusStation();
        double result = noCard.visit(station);

        assertEquals(0, result);
    }

    @Test
    void testComputeCharge() {
        MechanicalBicycle bicycle1 = new MechanicalBicycle();
        ElectricalBicycle bicycle2 = new ElectricalBicycle();
        double duration = 120;

        double result1 = noCard.computeCharge(bicycle1, 1, duration);
        double result2 = noCard.computeCharge(bicycle1, 0, duration);
        double result3 = noCard.computeCharge(bicycle2, 1, duration);
        double result4 = noCard.computeCharge(bicycle2, 0, duration);

        assertEquals(2, result1);
        assertEquals(2.2, result2);
        assertEquals(4, result3);
        assertEquals(4.4, result4);
    }

    @Test
    void testApplyBonus() {
        RegularStation station1 = new RegularStation();
        PlusStation station2 = new PlusStation();
        noCard.applyBonus(station1);
        assertEquals(0, user.getTotalTimeCredit());
        noCard.applyBonus(station2);
        assertEquals(0, user.getTotalTimeCredit());
    }

}