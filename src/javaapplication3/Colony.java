
package javaapplication3;


public class Colony {
    private Facility[] facilities;
    private int facilityCount;
    private CrewMember[] crewArray;
    private int crewCount;
    
    private Robot[] robotArray;
    private int robotCount;
    
    private Missions activeMission;
    private EnvironmentManager environment = new EnvironmentManager();
    
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
    //only one is active:
    public void submitMission(Missions mission) {
    if (mission == null || !mission.markSubmitted()) {
        System.out.println("Mission was already submitted or is null.");
        return;
    }

    if (getActiveMission() != null) {
        mission.failMission("Another mission is already active.");
        return;
    }

    if (mission.getAssignedAgentCount() == 0) {
        mission.failMission("No agents assigned.");
        return;
    }

    // Check the agents selected for THIS mission
    for (int i = 0; i < mission.getAssignedAgentCount(); i++) {
        Agent agent = mission.getAssignedAgent(i);

        if (!agent.isAvailable() || !agent.canPerform(mission)) {
            mission.failMission(
                "Agent " + agent.getName()
                + " is unavailable or lacks the required capability."
            );
            return;
        }

        EnvironmentCheck control = environment.canControl(agent);
        if (!control.isAllowed()) {
            mission.failMission(control.getReason());
            return;
        }
    }

        // Check the environment
        EnvironmentCheck check = environment.canStart(mission);

        if (!check.isAllowed()) {
            mission.failMission(check.getReason());
            return;
        }

        // Simple resource costs for each mission
        int battery = 0;
        int spare = 0;
        int fuel = 0;

        if (mission instanceof ExplorationMission) battery = 10;
        if (mission instanceof RepairMission) {
            battery = 5;
            spare = 2;
        }
        if (mission instanceof ResearchMission) battery = 10;
        if (mission instanceof RescueMission) {
            battery = 5;
            fuel = 5;
        }

        // Check ALL resources before consuming any
        if (getTotalResource("battery") < battery ||
            getTotalResource("spare") < spare ||
            getTotalResource("fuel") < fuel) {

            mission.failMission("Insufficient mission resources.");
            return;
        }

        if (!mission.validateCapabilities(this)) {
            mission.failMission("Mission requirements not satisfied.");
            return;
        }

        // Everything is valid; consume resources and accept
        consumeResource("battery", battery);
        consumeResource("spare", spare);
        consumeResource("fuel", fuel);

        mission.reserveAgents();
        activeMission = mission;

        System.out.println("Mission assigned successfully.");
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
    //prevent invalid resource consumption
    if (needed < 0 || type == null ||
    !(type.equals("oxygen") || type.equals("water") ||
      type.equals("food") || type.equals("fuel") ||
      type.equals("battery") || type.equals("medical") ||
      type.equals("spare"))) {

    System.out.println("Invalid resource request.");
    return false;
}
    //---
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

    public EnvironmentManager getEnvironment() {
        return environment;
    }

    public Missions getActiveMission() {
        if (activeMission == null) return null;

        if (activeMission.getState() == MissionState.COMPLETED ||
            activeMission.getState() == MissionState.FAILED) {
            return null;
        }

        return activeMission;
    }

    public void advanceTime(int units) {
        if (units < 0) return;

        for (int t = 0; t < units; t++) {
            if (getActiveMission() == null) {
                activeMission = null;
                return;
            }

            EnvironmentCheck check = environment.canContinue(activeMission);

            if (!check.isAllowed()) {
                activeMission.failMission(check.getReason());
                activeMission = null;
                return;
            }

            if (activeMission.getState() == MissionState.ASSIGNED) {
                activeMission.startExecution();

                if (activeMission instanceof ExplorationMission) {
                    for (int i = 0; i < activeMission.getAssignedAgentCount(); i++) {
                        Agent agent = activeMission.getAssignedAgent(i);
                        if (agent instanceof ExplorationRover) {
                            agent.setLocation("Martian Surface");
                        }
                    }
                }
            }

            // Check robot condition before advancing
            boolean failure = false;

            for (int i = 0; i < activeMission.getAssignedAgentCount(); i++) {
                Agent agent = activeMission.getAssignedAgent(i);

                if (agent instanceof Robot) {
                    Robot robot = (Robot) agent;

                    if (!robot.ishealthy() || robot.getBatteryLevel() < 5) {
                        activeMission.failMission(
                            "Robot damaged or insufficient battery."
                        );
                        failure = true;
                        break;
                    }
                }
            }

            if (failure) {
                activeMission = null;
                return;
            }

            // Each active robot consumes 5 battery units per time unit
            for (int i = 0; i < activeMission.getAssignedAgentCount(); i++) {
                Agent agent = activeMission.getAssignedAgent(i);

                if (agent instanceof Robot) {
                    ((Robot) agent).consumeBattery(5);
                }
            }

            activeMission.progressTime();
        }

        if (getActiveMission() == null) {
            activeMission = null;
        }
    }
    
        public void printStatus() {
        System.out.println("\n--- COLONY STATUS ---");

        System.out.println("Facilities:");
        for (int i = 0; i < facilityCount; i++) {
            System.out.println(
                facilities[i].getLocation() + " - " +
                (facilities[i].isOperational() ? "Operational" : "Unavailable")
            );
        }

        System.out.println("Crew:");
        for (int i = 0; i < crewCount; i++) {
            System.out.println(
                crewArray[i].getName() + " - " +
                crewArray[i].getLocation()
            );
        }

        System.out.println("Robots:");
        for (int i = 0; i < robotCount; i++) {
            System.out.println(
                robotArray[i].getName() + " - Battery: " +
                robotArray[i].getBatteryLevel()
            );
        }

        String[] types = {
            "oxygen", "water", "food", "fuel",
            "battery", "medical", "spare"
        };

        System.out.println("Colony resources:");
        for (String type : types) {
            System.out.println(type + ": " + getTotalResource(type));
        }

        System.out.println("Active mission: " +
            (getActiveMission() == null ?
                "None" : getActiveMission().getMissionId()));
    }
}
