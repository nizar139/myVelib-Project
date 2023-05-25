/**
 * This class, implementing the Factory design pattern, is responsible for generating ride planning strategies based on user preference.
 * It contains a factory method that, given a string representing a strategy, returns an instance of a class that implements that strategy.
 */
package fr.cs.Group13.myVelib.Planing;

public class RideType {
    /**
     * This factory method generates an instance of a ride planning strategy class based on the provided strategy string.
     *
     * @param strategy A string representing the user's preferred ride planning strategy. The string should match one of the
     *                 predefined strategy names (case-insensitive), else a null will be returned.
     *                 Available strategies: "MinimalWalkingDistance", "AvoidPlusStation", "ElectricalOnlyRide",
     *                 "MechanicalOnlyRide", "PreferPlusStation", "PreferStreetBike", "PreserveUniformityElectricalRide",
     *                 "PreserveUniformityMechanicalRide", "PreserveUniformityRide".
     * @return An instance of a class that implements the provided strategy. If the strategy string does not match any
     *         predefined strategies, the method returns null.
     */
    public RidesPlaning getYourStrat(String strategy){
        if (strategy.equalsIgnoreCase("MinimalWalkingDistance")){
            return new MinimalWalkingDistance();
        } else if (strategy.equalsIgnoreCase("AvoidPlusStation")) {
            return new AvoidPlusStation();
        } else if (strategy.equalsIgnoreCase("ElectricalOnlyRide")) {
            return new ElectricalOnlyRide();
        } else if (strategy.equalsIgnoreCase("MechanicalOnlyRide")) {
            return new MechanicalOnlyRide();
        } else if (strategy.equalsIgnoreCase("PreferPlusStation")) {
            return new PreferPlusStation();
        } else if (strategy.equalsIgnoreCase("PreferStreetBike")) {
            return new PreferStreetBike();
        } else if (strategy.equalsIgnoreCase("PreserveUniformityElectricalRide")) {
            return new PreserveUniformityElectricalRide();
        } else if (strategy.equalsIgnoreCase("PreserveUniformityMechanicalRide")) {
            return new PreserveUniformityMechanicalRide();
        } else if (strategy.equalsIgnoreCase("PreserveUniformityRide")) {
            return new PreserveUniformityRide();
        }
        return null;
    }
}
