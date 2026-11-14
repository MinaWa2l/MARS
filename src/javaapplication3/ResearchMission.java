
package javaapplication3;


public class ResearchMission extends Missions{
    public ResearchMission(String missionId, String description, int durationUnits) {
        super(missionId, description, durationUnits);
    }

        @Override
    public boolean validateCapabilities(Colony colony) {
        boolean hasScientist = false;
        boolean hasLaboratoryWithEquipment = false;

        for (int i = 0; i < getAssignedAgentCount(); i++) {
            Agent agent = getAssignedAgent(i);

            if (agent instanceof Scientist && agent.canPerform(this)) {
                hasScientist = true;
            }
        }

        for (int i = 0; i < colony.getFacilityCount(); i++) {
            Facility f = colony.getFacilities()[i];

            if (f instanceof Laboratory && f.isOperational()) {
                Laboratory lab = (Laboratory) f;

                if (lab.hasEquipment()) {
                    hasLaboratoryWithEquipment = true;
                    break;
                }
            }
        }

        boolean hasResources = colony.getTotalResource("battery") >= 10;

        if (!hasScientist || !hasLaboratoryWithEquipment || !hasResources) {
            System.out.println(
                "Research requires a scientist, equipped laboratory and battery."
            );
            return false;
        }

        return true;
    }
    
}
