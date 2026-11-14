
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
        //drones handle reconnaissance or aerial survey missions
        return mission.isReconnaissanceMission() || mission.isAerialSurveyMission();
    }
    }

    @Override
    public boolean canRespondTo(Emergency emergency) {
        if (!isOperational()) {
            return false;
        }
        return emergency.isReconnaissanceEmergency();
    }
    
}
