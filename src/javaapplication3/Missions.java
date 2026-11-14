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
    private int assignedAgentCount;
    
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
        if (agent != null && agent.isAvailable() && assignedAgentCount < assignedAgents.length) {
            assignedAgents[assignedAgentCount] = agent;
            assignedAgentCount++;
            agent.setAvailable(false);
        }
    }

    //loop through the array, free the agents, and clear the references
    private void releaseAgents() {
        for (int i = 0; i < assignedAgentCount; i++) {
            if (assignedAgents[i] != null) {
                assignedAgents[i].setAvailable(true);
                assignedAgents[i] = null; // Remove the reference from the array
            }
        }
        assignedAgentCount = 0; // Reset the counter
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
            releaseAgents();
            }
        }
    }

    public void failMission(String reason) {
        this.state = MissionState.FAILED;
        System.out.println("FAILURE: Mission [" + missionId + "] FAILED. Reason: " + reason);
    releaseAgents();
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

    
    public abstract boolean validateCapabilities(Colony colony);
}