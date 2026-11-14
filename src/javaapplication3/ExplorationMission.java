
package javaapplication3;


public class ExplorationMission extends Missions{
    
    public ExplorationMission(String missionId, String description, int durationUnits) {
        super(missionId, description, durationUnits);
    }

    @Override
    public boolean validateCapabilities(Colony colony) {
        boolean hasScientist = false;
        boolean hasRover = false;

        for (int i = 0; i < getAssignedAgentCount(); i++) {
            Agent agent = getAssignedAgent(i);

            if (agent instanceof Scientist && agent.canPerform(this)) {
                hasScientist = true;
            }

            if (agent instanceof ExplorationRover && agent.canPerform(this)) {
                hasRover = true;
            }
        }

        boolean hasEquipment = colony.hasAvailableEquipment();
        boolean hasBattery = colony.getTotalResource("battery") >= 10;

        if (!hasScientist || !hasRover || !hasEquipment || !hasBattery) {
            System.out.println(
                "Exploration requires a scientist, rover, equipment and battery."
            );
            return false;
        }

        return true;
    }
            
    
    
}
