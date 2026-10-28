/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaapplication3;

/**
 *
 * @author lenovo
 */
public class ExplorationRover extends Robot {
    public ExplorationRover(String currentLocation, String Name, String ID) {
        super(currentLocation, Name, ID);
    }
    @Override
    public boolean canPerformMission(String missionType) {
        //rover must be operational (healthy, has battery, available) to perform a mission
        if (!isOperational()) {
            return false;
        }
        return "Exploration".equalsIgnoreCase(missionType) || "Rescue".equalsIgnoreCase(missionType);
        //this line returns true if the requested mission is either "Exploration" or "Rescue", and false for anything else
    }
    @Override
    //canHandleEmergency figure out which robots are qualified and available to respond to specific colony emergencies
    public boolean canHandleEmergency(String emergencyType) {
        if (!isOperational()) {
            return false;
        }
        return "Rover Breakdown".equalsIgnoreCase(emergencyType);
        //return true if the emergency type is a Rover Breakdown, and false for other emergencies
    }
}
