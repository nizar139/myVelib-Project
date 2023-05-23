package fr.cs.Group13.myVelib.DockingStation;

import fr.cs.Group13.myVelib.Bicycle.Bicycle;

public class ParkingSlot {
    private boolean occupied ;
    private Bicycle bicycle ;
    private DockingStation station;
    public void freeSlot(){
        this.occupied = false;
        this.bicycle = null;
    }
    public void putBicycle(Bicycle b){
        if (this.occupied){
            throw new IllegalStateException("Slot is already occupied");
        }
        else {
            this.occupied = true;
            this.bicycle = b;
        }
    }
}
