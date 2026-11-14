package javaapplication3;

public class Drone extends Robot {

    protected boolean commsActive; //to track whether communication link is active or not

    public Drone(String currentLocation, String Name, String ID) {
        super(currentLocation, Name, ID);
        this.commsActive = true; //drone communications start active by default
    }

    public boolean isCommsActive() {
        return commsActive;
    }

    public void setCommsActive(boolean commsActive) {
        this.commsActive = commsActive;
    }

    @Override
    public boolean isOperational() {
        //drone requires general robot operational status + an active communication link
        //healthy + availability + battery level + comm = active
        return super.isOperational() && isCommsActive();
    }

    @Override
    public boolean canPerform(Missions mission) {
        if (!isOperational()) {
            return false;
        }
        // Drones handle exploration missions that might require aerial survey
        return mission instanceof ExplorationMission; 
    }
    
    // THE EXTRA '}' THAT WAS HERE HAS BEEN REMOVED

    @Override
    public boolean canRespondTo(Emergency emergency) {
        if (!isOperational()) {
            return false;
        }
        // If you don't have a specific Reconnaissance emergency class, return false 
        // or check for a relevant emergency type here.
        return false; 
    }
}