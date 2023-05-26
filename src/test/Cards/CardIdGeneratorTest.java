package test.Cards;

import fr.cs.Group13.myVelib.Cards.CardIdGenerator;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CardIdGeneratorTest {

    @Test
    public void testSingleton(){
        CardIdGenerator instance1 = CardIdGenerator.getInstance();
        CardIdGenerator instance2 = CardIdGenerator.getInstance();

        assertEquals(instance1, instance2, "Instances are not the same. Singleton property violated.");
    }

    @Test
    public void testUniqueIDs() {
        CardIdGenerator instance = CardIdGenerator.getInstance();

        // Generate some IDs
        int id1 = instance.getNextCardId();
        int id2 = instance.getNextCardId();
        int id3 = instance.getNextCardId();

        // Testing that each ID is unique
        assertNotEquals(id1, id2, "IDs are not unique.");
        assertNotEquals(id2, id3, "IDs are not unique.");
        assertNotEquals(id1, id3, "IDs are not unique.");
    }
}