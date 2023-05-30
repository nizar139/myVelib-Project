package test.Bicycle;

import core.Bicycle.BicycleIdGenerator;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BicycleIdGeneratorTest {
    @Test
    public void testSingleton() {
        BicycleIdGenerator instance1 = BicycleIdGenerator.getInstance();
        BicycleIdGenerator instance2 = BicycleIdGenerator.getInstance();

        assertEquals(instance1, instance2, "Instances are not the same. Singleton property violated.");
    }

    @Test
    public void testUniqueIDs() {
        BicycleIdGenerator instance = BicycleIdGenerator.getInstance();

        // Generate a few IDs
        int id1 = instance.getNextBicycleID();
        int id2 = instance.getNextBicycleID();
        int id3 = instance.getNextBicycleID();

        // Testing that each ID is unique
        assertNotEquals(id1, id2, "IDs are not unique.");
        assertNotEquals(id2, id3, "IDs are not unique.");
        assertNotEquals(id1, id3, "IDs are not unique.");
    }

}