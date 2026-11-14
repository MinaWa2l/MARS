
package javaapplication3;

public class ExplorationRover extends Robot {
    public ExplorationRover(String currentLocation, String Name, String ID) {
        super(currentLocation, Name, ID);
    }
    @Override
    public boolean canPerform(Missions mission) {
        //rover must be operational (healthy, has battery, available) to perform a mission
        if (!isOperational()) {
            return false;
        }
        return mission instanceof ExplorationMission || mission instanceof RescueMission;
        //this line returns true if the requested mission is either "Exploration" or "Rescue", and false for anything else
    }
    @Override
    //canHandleEmergency figure out which robots are qualified and available to respond to specific colony emergencies
    public boolean canRespondTo(Emergency emergency) {
        if (!isOperational()) {
            return false;
        }
       return false;
        
    }
}
