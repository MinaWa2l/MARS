
package javaapplication3;


public class RepairRobot extends Robot {
 public RepairRobot(String currentLocation, String Name, String ID) {
        super(currentLocation, Name, ID);
    }

    @Override
    public boolean canPerform(Missions mission) {
        if (!isOperational()) {
            return false;
        }
        return mission.isRepairMission() || mission.isMaintenanceMission(); 
    }

    @Override
    public boolean canRespondTo(Emergency emergency) {
        if (!isOperational()) {
            return false;
        }
        return emergency.isOxygenLeak() || emergency.isFire();
    }
}
