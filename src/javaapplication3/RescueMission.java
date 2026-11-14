package javaapplication3;

public class RescueMission extends Missions {

    public RescueMission(String missionId, String description, int durationUnits) {
        super(missionId, description, durationUnits);
    }

    @Override
    public boolean validateCapabilities(Colony colony) {
        boolean hasPersonnel = false;
        boolean hasVehicle = false;
        boolean hasComms = false;

        if (colony.getCrewArray() != null) {
            for (CrewMember cm : colony.getCrewArray()) {
                if (cm == null) continue;
                if (cm.isAvailable() && cm.canPerform(this)) {
                    hasPersonnel = true;
                    break;
                }
            }
        }

        if (colony.getRobotArray() != null) {
            for (Robot r : colony.getRobotArray()) {
                if (r == null) continue;
                if (r instanceof ExplorationRover && r.isAvailable() && r.canPerform(this)) {
                    hasVehicle = true;
                    break;
                }
            }
        }

        if (colony.getFacilities() != null) {
            for (int i = 0; i < colony.getFacilityCount(); i++) {
                Facility f = colony.getFacilities()[i];
                if (f != null && f.isOperational() && f instanceof CommunicationStation) {
                    hasComms = true;
                    break;
                }
            }
        }

        if (!hasPersonnel || !hasVehicle || !hasComms) {
            System.out.println("Validation Failed: Rescue mission requires available personnel, a rover, and active communications.");
            return false;
        }
        return true;
    }
    
    
    public boolean requiresCommunication() {
        return true;
    }
}