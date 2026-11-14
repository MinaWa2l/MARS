package javaapplication3;

public class Drone extends Robot {
    protected boolean commsActive;

    public Drone(String currentLocation, String Name, String ID) {
        super(currentLocation, Name, ID);
        this.commsActive = true; 
    }
    public boolean isCommsActive() { return commsActive; }
    public void setCommsActive(boolean commsActive) { this.commsActive = commsActive; }

    @Override
    public boolean isOperational() {
        return super.isOperational() && isCommsActive();
    }
    @Override
    public boolean canPerform(Missions mission) {
        if (!isOperational()) return false;
        return mission instanceof ExplorationMission;
    }
    @Override
    public boolean canRespondTo(Emergency emergency) {
        return false; 
    }
}