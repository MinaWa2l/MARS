
package javaapplication3;


public class RepairMission extends Missions{
    public RepairMission(String missionId, String description, int durationUnits) {
        super(missionId, description, durationUnits);
    }
    
    @Override
    public boolean validateCapabilities(Colony colony) {
    boolean hasEngineer=false;
    boolean hasRover= false;
    
    if(colony.getCrewArray()!=null){
        for (Robot r : colony.getRobotArray()){
            if(cm==null)continue;
            if(cm instanceof Engineer && cm.isAvailable()&& cm.canPerform(this)){
                hasEngineer=true;
                break;
            }
        }
    }
    
    if (colony.getRobotArray() != null) {
            for (Robot r : colony.getRobotArray()) {
                if (r == null) continue;
               if (r instanceof RepairRobot && r.isAvailable() && r.canPerform("Repair")) {
                    hasRover = true;
                    break;
                }
            }
        }
    
    boolean hasSpare = (colony.getTotalResource("spare")>=2);
    
    if (!hasEngineer || !hasRover || !hasSpare) {
            System.out.println("Validation Failed: Repair mission requires an available Engineer, Repair Robot and Spare Parts.");
            return false;
        }
        return true;
    
    
    
    
    }
    
    
}
