package fr.cs.Group13.myVelib.Planing;

public class RideType {
    public RidesPlaning getYourStrat(String strategy){
        if (strategy.equalsIgnoreCase("CLASSIC")){
            return new Classic();
        }
    }
}
