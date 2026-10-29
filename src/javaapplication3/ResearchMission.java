/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaapplication3;

/**
 *
 * @author Mina Wael
 */
public class ResearchMission extends Missions{
    public ResearchMission(String missionId, String description, int durationUnits) {
        super(missionId, description, durationUnits);
    }

    @Override
    public boolean validateCapabilities(Colony colony) {
        boolean hasScientist = false;
        boolean hasLaboratoryWithEquipment = false;
        
       
        if (colony.getCrewArray() != null) {
            for (CrewMember cm : colony.getCrewArray()) {
                if (cm == null) continue;
                if (cm instanceof Scientist && cm.isAvailable() && cm.canPerform(this)) {
                    hasScientist = true;
                    break;
                }
            }
        }

        
        for (int i = 0; i < colony.getFacilityCount(); i++) {
            Facility f = colony.getFacilities()[i];
            if (f != null && f.isOperational() && f instanceof Laboratory) {
                Laboratory lab = (Laboratory) f;
                if (lab.hasEquipment()) {
                    hasLaboratoryWithEquipment = true;
                    break;
                }
            }
        }

       
        boolean hasResources = (colony.getTotalResource("battery") >= 10);

       
        if (!hasScientist || !hasLaboratoryWithEquipment || !hasResources) {
            System.out.println("Validation Failed: Research mission requires a Scientist, an equipped Laboratory, and resources.");
            return false;
        }
        
        return true;
    }
    
}
