
package javaapplication3;


public class RepairMission extends Missions{
    private Facility targetFacility;

    public void setTargetFacility(Facility target) {
        targetFacility = target;
    }
    public RepairMission(String missionId, String description, int durationUnits) {
        super(missionId, description, durationUnits);
    }
    
    @Override
    public boolean validateCapabilities(Colony colony) {
        boolean hasEngineer = false;
        boolean hasRepairRobot = false;

        for (int i = 0; i < getAssignedAgentCount(); i++) {
            Agent agent = getAssignedAgent(i);

            if (agent instanceof Engineer && agent.canPerform(this)) {
                hasEngineer = true;
            }

            if (agent instanceof RepairRobot && agent.canPerform(this)) {
                hasRepairRobot = true;
            }
        }

        boolean hasSpare = colony.getTotalResource("spare") >= 2;
        boolean hasBattery = colony.getTotalResource("battery") >= 5;

        if (!hasEngineer || !hasRepairRobot || !hasSpare || !hasBattery) {
            System.out.println(
                "Repair requires an engineer, repair robot and resources."
            );
            return false;
        }
        if (targetFacility == null) {
            System.out.println("Repair mission requires a target facility.");
            return false;
        }

        if (targetFacility.isOperational() &&
            !(targetFacility instanceof Laboratory &&
              !((Laboratory) targetFacility).hasEquipment())) {

            System.out.println("Target facility does not need repair.");
            return false;
        }
        return true;
    }
    
        @Override
    public void progressTime() {
        if (getState() != MissionState.IN_PROGRESS) return;

        super.progressTime();

        if (getState() == MissionState.COMPLETED) {
            targetFacility.setOperational(true);

            if (targetFacility instanceof Laboratory) {
                ((Laboratory) targetFacility).equipement = true;
            }

            System.out.println("Target facility repaired.");
        }
    }
    
}
