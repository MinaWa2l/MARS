package javaapplication3;
import java.util.List;

enum MissionState {
    ASSIGNED, IN_PROGRESS, COMPLETED, FAILED
}
//using an enum (enumeration) for MissionState is a way to represent a fixed set of states that a mission can go through during its lifecycle
public abstract class Missions {
    private String missionId;
    private String description;
    private MissionState state;
    private int durationUnits;//total time
    private int elapsedUnits;//counter
    //durationUnits and elapsedUnits work together to track time and manage how long a mission takes to complete
    
    public Missions(String missionId, String description, int durationUnits) {
        this.missionId = missionId;
        this.description = description;
        this.durationUnits = durationUnits;
        this.state = MissionState.ASSIGNED;
        this.elapsedUnits = 0;
    }
    public void startExecution() {
        if (state == MissionState.ASSIGNED) {
            state = MissionState.IN_PROGRESS;
            System.out.println("Mission [" + missionId + ": " + description + "] has started.");
        }
    }
    public void progressTime() {
        if (state == MissionState.IN_PROGRESS) {
            elapsedUnits++;
            System.out.println("Mission [" + missionId + "] Progress: " + elapsedUnits + "/" + durationUnits + " units.");
            if (elapsedUnits >= durationUnits) {
                state = MissionState.COMPLETED;
                System.out.println("SUCCESS: Mission [" + missionId + "] COMPLETED!");
            }
        }
    }
    public void failMission(String reason) {
        this.state = MissionState.FAILED;
        System.out.println("FAILURE: Mission [" + missionId + "] FAILED. Reason: " + reason);
    }
    public String getMissionId() {
        return missionId; }
    public String getDescription() {
        return description; }
    public MissionState getState() {
        return state; }
    public abstract boolean validateCapabilities(List<CrewMember> crewMemberList, List<Robot> robotList);
    //List<CrewMember>&List<Robot> containers have a collection of all the crew members and robots available in simulation
}
