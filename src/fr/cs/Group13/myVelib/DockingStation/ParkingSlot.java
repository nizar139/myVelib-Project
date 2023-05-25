package fr.cs.Group13.myVelib.DockingStation;

import fr.cs.Group13.myVelib.Bicycle.Bicycle;

public class ParkingSlot {
    private boolean occupied = false ;
    private Bicycle bicycle = null;
    private DockingStation station;

    public ParkingSlot(DockingStation station, Bicycle bicycle) {
        this.bicycle = bicycle;
        this.station = station;
        this.occupied = true;
    }

    public ParkingSlot(DockingStation station) {
        this.station = station;
    }

    public DockingStation getStation() {
        return station;
    }

    public void freeSlot(){
        this.bicycle.setBikeCountFree(this);
        this.occupied = false;
        this.bicycle = null;
    }
    public void putBicycle(Bicycle b){
        if (this.occupied){
            throw new IllegalStateException("Slot is already occupied");
        }
        else {
            b.setBikeCountFill(this);
            this.occupied = true;
            this.bicycle = b;
        }
    }
}
