
package javaapplication3;


public class Colony {
    private Facility[] facilities;
    private int facilityCount;
    
    public Colony(int maxSize){
        facilities = new Facility[maxSize];
        facilityCount=0;
    }
    
    public void addFacility(Facility facility){
        if(facilityCount<facilities.length){
            facilities[facilityCount]=facility;
            facilityCount++;
        }
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
}
