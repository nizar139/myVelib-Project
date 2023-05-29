package fr.cs.Group13.myVelib.DockingStation;

import java.util.Comparator;
import fr.cs.Group13.myVelib.DockingStation.DockingStation;

/**
 * Comparator class for comparing DockingStation objects based on their usage.
 * The most used station will be prioritized.
 */
public class MostUsedStationComparator implements Comparator<DockingStation>{
    @Override
    public int compare(DockingStation s1, DockingStation s2) {
        int s2total = s2.getTotalRents()+s2.getTotalReturns();
        int s1total = s1.getTotalRents()+s1.getTotalReturns();
        return s1total-s2total;
    }
}