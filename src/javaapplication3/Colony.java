
package javaapplication3;


public class Colony {
    private Facility[] facilities;
    private int facilityCount;
    private CrewMember[] crewArray;
    private int crewCount;
    
    private Robot[] robotArray;
    private int robotCount;
    
    public Colony(int maxFacilities, int maxCrew, int maxRobots){
        facilities = new Facility[maxFacilities];
        facilityCount = 0;
        
        crewArray = new CrewMember[maxCrew];
        crewCount = 0;
        
        robotArray = new Robot[maxRobots];
        robotCount = 0;
    }
    
    public void addFacility(Facility facility){
        if(facilityCount<facilities.length){
            facilities[facilityCount]=facility;
            facilityCount++;
        }
    }
    
    public void addCrewMember(CrewMember cm) {
        if (crewCount < crewArray.length) {
            crewArray[crewCount] = cm;
            crewCount++;
        }
    }
    
    public void addRobot(Robot r) {
        if (robotCount < robotArray.length) {
            robotArray[robotCount] = r;
            robotCount++;
        }
    }
    
    public CrewMember[] getCrewArray() {
        return crewArray;
    }

    public Robot[] getRobotArray() {
        return robotArray;
    }

    public Facility[] getFacilities() {
        return facilities;
    }

    public int getFacilityCount() {
        return facilityCount;
    }
    
    
    public int getTotalResource(String type) {
    int total = 0;
    for(int i = 0; i < facilityCount; i++){
        if(facilities[i].isOperational()){
            Resources r = facilities[i].getResources();
            switch(type) {
                case "oxygen": total += r.getOxygen(); break;
                case "water": total += r.getWater(); break;
                case "food": total += r.getFood(); break;
                case "fuel": total += r.getFuel(); break;
                case "battery": total += r.getBattery(); break;
                case "medical": total += r.getMedical(); break;
                case "spare": total += r.getSpare(); break;
            }
        }
    }
    return total;
}

public boolean consumeResource(String type, int needed){
    if(getTotalResource(type) < needed){
        System.out.println("Failed: need " + needed + " " + type + " but only " + getTotalResource(type) + " available.");
        return false;
    }
    
    for(int i = 0; i < facilityCount; i++){
        if(!facilities[i].isOperational()) continue;
        Resources r = facilities[i].getResources();
        int removed = 0;
        
        switch(type) {
            case "oxygen": removed = r.removeOxygen(needed); break;
            case "water": removed = r.removeWater(needed); break;
            case "food": removed = r.removeFood(needed); break;
            case "fuel": removed = r.removeFuel(needed); break;
            case "battery": removed = r.removeBattery(needed); break;
            case "medical": removed = r.removeMedical(needed); break;
            case "spare": removed = r.removeSpare(needed); break;
        }
        
        needed -= removed;
        if(needed == 0) break;
    }
    return true;
}

public boolean hasAvailableEquipment() {
    for (int i = 0; i < facilityCount; i++) {
        if (!facilities[i].isOperational()) continue;
        
        // Check Habitat
        if (facilities[i] instanceof Habitat) {
            Habitat h = (Habitat) facilities[i];
            if (h.hasEquipment()) return true;
        }
        // Check Laboratory
        else if (facilities[i] instanceof Laboratory) {
            Laboratory lab = (Laboratory) facilities[i];
            if (lab.hasEquipment()) return true;
        }
        // Check Storage Facility
        else if (facilities[i] instanceof StorageFacility) {
            StorageFacility storage = (StorageFacility) facilities[i];
            if (storage.hasEquipment()) return true;
        }
    }
    return false; // No equipment found in any operational facility
}


}
