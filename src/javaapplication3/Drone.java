/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaapplication3;

/**
 *
 * @author lenovo
 */
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
    public boolean canPerformMission(String missionType) {
        if (!isOperational()) {
            return false;
        }
        //drones handle reconnaissance or aerial survey missions
        return "Reconnaissance".equalsIgnoreCase(missionType) 
            || "Aerial Survey".equalsIgnoreCase(missionType);
    }

    @Override
    public boolean canHandleEmergency(String emergencyType) {
        if (!isOperational()) {
            return false;
        }
        return "Reconnaissance".equalsIgnoreCase(emergencyType);
    }
    
}
