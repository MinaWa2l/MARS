package javaapplication3;

enum MissionState {
    ASSIGNED, IN_PROGRESS, COMPLETED, FAILED
}
//using an enum (enumeration) for MissionState is a way to represent a fixed set of states that a mission can go through during its lifecycle
public abstract class Missions {
    private String missionId;
    private String description;
    private MissionState state;
    private int durationUnits; // total time
    private int elapsedUnits; // counter
    
    private Agent[] assignedAgents;
    private int assignedAgentCount;//eli gayeen: remember whether the mission has already been submitted, whether its agents were actually reserved, and why it failed.
    private boolean submitted = false;
    private boolean reserved = false;
    private String failureReason = "";
    
    public Missions(String missionId, String description, int durationUnits) {
        this.missionId = missionId;
        this.description = description;
        this.durationUnits = durationUnits;
        this.state = MissionState.ASSIGNED;
        this.elapsedUnits = 0;
        
        this.assignedAgents = new Agent[10];
        this.assignedAgentCount = 0;
    }
//insert into the array and increment the counter
    public void assignAgent(Agent agent) {
    if (submitted || agent == null || !agent.isAvailable() || assignedAgentCount >= assignedAgents.length) {
        return;
    }

    for (int i = 0; i < assignedAgentCount; i++) {
        if (assignedAgents[i] == agent) return;
    }

    assignedAgents[assignedAgentCount] = agent;
    assignedAgentCount++;
    }
    
    public int getAssignedAgentCount() {
    return assignedAgentCount;
}
//for colony:
    public Agent getAssignedAgent(int i) {
        return assignedAgents[i];
    }

    public boolean markSubmitted() {
        if (submitted) return false;
        submitted = true;
        return true;
    }

    public void reserveAgents() {
        reserved = true;
        for (int i = 0; i < assignedAgentCount; i++) {
            assignedAgents[i].setAvailable(false);
        }
    }
//--
    //loop through the array, free the agents, and clear the references
    private void releaseAgents() {
        for (int i = 0; i < assignedAgentCount; i++) {
           if (assignedAgents[i] != null) {
                if (reserved) {
                    assignedAgents[i].setAvailable(true);
                    }
                    assignedAgents[i] = null;
                }
        }
        assignedAgentCount = 0; // Reset the counter
        reserved = false;
    }
    public void startExecution() {
        if (state == MissionState.ASSIGNED && reserved) {
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
            releaseAgents();
            }
        }
    }

    public void failMission(String reason) {
        if (state == MissionState.COMPLETED || state == MissionState.FAILED) {
        return;
    }

    this.state = MissionState.FAILED;
    this.failureReason = reason;
        System.out.println("FAILURE: Mission [" + missionId + "] FAILED. Reason: " + reason);
    releaseAgents();
    }

    public String getFailureReason() {
    return failureReason;
    }
    public String getMissionId() {
        return missionId;
    }

    public String getDescription() {
        return description;
    }

    public MissionState getState() {
        return state;
    }

    
    public boolean requiresCommunication() {
        return false;
    }

    public abstract boolean validateCapabilities(Colony colony);
}