package javaapplication3;

public class RescueMission extends Missions {
    private Agent strandedAgent;
    private String safeLocation;

    public void setRescueTarget(Agent agent, String location) {
        strandedAgent = agent;
        safeLocation = location;
    }

    public RescueMission(String missionId, String description, int durationUnits) {
        super(missionId, description, durationUnits);
    }

    @Override
   public boolean validateCapabilities(Colony colony) {
       boolean hasPersonnel = false;
       boolean hasVehicle = false;
       boolean hasComms = false;

       for (int i = 0; i < getAssignedAgentCount(); i++) {
           Agent agent = getAssignedAgent(i);

           if (agent instanceof CrewMember && agent.canPerform(this)) {
               hasPersonnel = true;
           }

           if (agent instanceof ExplorationRover && agent.canPerform(this)) {
               hasVehicle = true;
           }
       }

       for (int i = 0; i < colony.getFacilityCount(); i++) {
           Facility f = colony.getFacilities()[i];

           if (f instanceof CommunicationStation && f.isOperational()) {
               hasComms = true;
               break;
           }
       }

       boolean hasResources =
           colony.getTotalResource("battery") >= 5 &&
           colony.getTotalResource("fuel") >= 5;

       if (!hasPersonnel || !hasVehicle || !hasComms || !hasResources) {
           System.out.println(
               "Rescue requires personnel, a rover, communications and resources."
           );
           return false;
       }
        if (strandedAgent == null || safeLocation == null) {
            System.out.println("Rescue target and safe location are required.");
            return false;
        }
       return true;
   }
    
    
    public boolean requiresCommunication() {
        return true;
    }
    
    @Override
    public void progressTime() {
        if (getState() != MissionState.IN_PROGRESS) return;

        super.progressTime();

        if (getState() == MissionState.COMPLETED) {
            strandedAgent.setLocation(safeLocation);
            strandedAgent.setAvailable(true);

            System.out.println("Stranded agent rescued.");
        }
    }
}