/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaapplication3;

/**
 *
 * @author lenovo
 */
public class RepairRobot extends Robot {
 public RepairRobot(String currentLocation, String Name, String ID) {
        super(currentLocation, Name, ID);
    }

    @Override
    public boolean canPerformMission(String missionType) {
        if (!isOperational()) {
            return false;
        }
        // repair robots handle maintenance and repair missions
        return "Maintenance".equalsIgnoreCase(missionType) 
            || "Repair".equalsIgnoreCase(missionType);
    }

    @Override
    public boolean canHandleEmergency(String emergencyType) {
        if (!isOperational()) {
            return false;
        }
        // Repair robots used for structural emergencies like Oxygen leaks or fires
        return "Oxygen Leak".equalsIgnoreCase(emergencyType) 
            || "Fire".equalsIgnoreCase(emergencyType);
    }   
}
