package test.DockingStation;

import core.DockingStation.StationIdGenerator;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class StationIdGeneratorTest {
    @Test
    public void testSingleton(){
        StationIdGenerator instance1 = StationIdGenerator.getInstance();
        StationIdGenerator instance2 = StationIdGenerator.getInstance();

        assertEquals(instance1, instance2, "Instances are not the same. Singleton property violated.");
    }

    @Test
    public void testUniqueIDs() {
        StationIdGenerator instance = StationIdGenerator.getInstance();

        // Generate some IDs
        int id1 = instance.getNextStationID();
        int id2 = instance.getNextStationID();
        int id3 = instance.getNextStationID();

        // Testing that each ID is unique
        assertNotEquals(id1, id2, "IDs are not unique.");
        assertNotEquals(id2, id3, "IDs are not unique.");
        assertNotEquals(id1, id3, "IDs are not unique.");
    }
}