package test.User;

import core.Bicycle.Bicycle;
import core.Bicycle.ElectricalBicycle;
import core.Cards.CreditCard;
import core.Cards.Vmax;
import core.DockingStation.PlusStation;
import core.System.VelibSystem;
import core.User.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UserTest {

    private User user;
    private Bicycle bicycle;
    private VelibSystem velibSystem;

    @BeforeEach
    public void setUp() {
        Vmax card = new Vmax();
        CreditCard creditCard = new CreditCard();
        user = new User("John", "Doe", card, creditCard);
        card.setOwner(user);
        bicycle = new ElectricalBicycle();
        velibSystem = new VelibSystem();
    }

    @Test
    public void rentBicycle_UserCanRentBicycle() {
        assertNull(user.getCurrentBicycle());

        user.rentBicycle(bicycle);

        assertNotNull(user.getCurrentBicycle());
        assertEquals(bicycle, user.getCurrentBicycle());
    }

    @Test
    public void rentBicycle_UserCannotRentMultipleBicycles() {
        assertNull(user.getCurrentBicycle());

        user.rentBicycle(bicycle);

        assertThrows(IllegalStateException.class, () -> user.rentBicycle(bicycle));
    }

    @Test
    public void returnBicycle_UserCanReturnBicycle() {
        user.rentBicycle(bicycle);

        assertNotNull(user.getCurrentBicycle());

        user.returnBicycle();

        assertNull(user.getCurrentBicycle());
    }

    @Test
    public void returnBicycle_WithSlot_UserCanReturnBicycleToSpecificSlot() {
        user.rentBicycle(bicycle);

        PlusStation station = new PlusStation(null,new double[]{1, 0.5},5,5,0);

        user.returnBicycle(station.getParkingSlotArraylist().get(0));

        assertNull(user.getCurrentBicycle());
        assertEquals(bicycle, station.getParkingSlotArraylist().get(0).getBicycle());
    }

    @Test
    public void returnBicycle_WithoutSlot_UserCanReturnBicycleToRandomPlace() {
        user.rentBicycle(bicycle);

        user.returnBicycle();

        assertNull(user.getCurrentBicycle());
    }


    @Test
    public void userBalance_PrintsBalanceInformation() {
        user.userBalance();
    }

}
