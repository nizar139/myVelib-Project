package fr.cs.Group13.myVelib.DockingStation;

import java.util.Comparator;

public class leastOccupiedStationComparator implements Comparator<DockingStation> {

    @Override
    public int compare(DockingStation s1, DockingStation s2) {
        int s2occupation = s2.getTotalRents()-s2.getTotalReturns();
        int s1occupation = s1.getTotalRents()-s1.getTotalReturns();
        return s2occupation-s1occupation;
    }
}
