
package javaapplication3;


public class ExplorationMission extends Missions{
    
    public ExplorationMission(String missionId, String description, int durationUnits) {
        super(missionId, description, durationUnits);
    }
    @Override
    public boolean validateCapabilities(Colony colony) {
    boolean hasScientist=false;
    boolean hasRover= false;
    
    if(colony.getCrewArray()!=null){
       for(CrewMember cm: colony.getCrewArray()){
            if(cm==null)continue;
            if(cm instanceof Scientist && cm.isAvailable()&& cm.canPerform(this)){
                hasScientist=true;
                break;
            }
        }
    }
    
    if (colony.getRobotArray() != null) {
            for (Robot r : colony.getRobotArray()) {
                if (r == null) continue;
                if (r instanceof ExplorationRover && r.isAvailable() && r.canPerform("Exploration")) {
                    hasRover = true;
                    break;
                }
            }
        }
    
    boolean hasEquipment = (colony.hasAvailableEquipment());
    
    if (!hasScientist || !hasRover || !hasEquipment) {
            System.out.println("Validation Failed: Exploration mission requires an available Scientist, Exploration Rover and Equipment.");
            return false;
        }
        return true;
    
    
    
    
    }
            
    
    
}
