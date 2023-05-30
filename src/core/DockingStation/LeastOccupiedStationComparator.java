package core.DockingStation;

import java.util.Comparator;

/**
 * Comparator class for comparing DockingStation objects based on their occupancy.
 * The least occupied station will be prioritized.
 */
public class LeastOccupiedStationComparator implements Comparator<DockingStation> {

    @Override
    public int compare(DockingStation s1, DockingStation s2) {
        int s2occupation = s2.getTotalRents()-s2.getTotalReturns();
        int s1occupation = s1.getTotalRents()-s1.getTotalReturns();
        return s2occupation-s1occupation;
    }
}
