package javaapplication3;

public class Main {
    public static void main(String[] args) {
        System.out.println("--- MARS-OPS INITIALIZATION ---\n");
        
        // ==========================================
        // 1. SETUP COLONY, FACILITIES, CREW, & ROBOTS
        // ==========================================
        Colony colony = new Colony(5, 5, 5);
        
        // Setup Resources
        Resources habitatRes = new Resources();
        habitatRes.addOxygen(1000);
        habitatRes.addBattery(50);
        habitatRes.addSpare(10);
        
        // Setup Facilities
        Habitat habitatA = new Habitat("Sector 1", 10, habitatRes, true);
        CommunicationStation comms = new CommunicationStation("Sector 2", 5, new Resources());
        
        colony.addFacility(habitatA);
        colony.addFacility(comms);
        
        // Setup Crew (assuming you added the skills array to their constructors)
        String[] basicSkills = {"First Aid"};
        Scientist scientist1 = new Scientist("Alice", "S1", "Sector 1", basicSkills);
        Engineer engineer1 = new Engineer("Bob", "E1", "Sector 1", basicSkills);
        Medical medical1 = new Medical("Charlie", "M1", "Sector 1", basicSkills);
        
        colony.addCrewMember(scientist1);
        colony.addCrewMember(engineer1);
        colony.addCrewMember(medical1);
        
        // Setup Robots
        ExplorationRover rover1 = new ExplorationRover("Sector 1", "Rover-X", "R1");
        RepairRobot repairBot1 = new RepairRobot("Sector 1", "Fix-It", "R2");
        
        colony.addRobot(rover1);
        colony.addRobot(repairBot1);

        // ==========================================
        // SCENARIO 1: Successful Exploration
        // ==========================================
        System.out.println("=== SCENARIO 1: Successful Exploration ===");
        ExplorationMission expMission = new ExplorationMission("M-01", "Explore Crater", 2);
        
        if (expMission.validateCapabilities(colony)) {
            expMission.assignAgent(scientist1);
            expMission.assignAgent(rover1);
            expMission.startExecution();
            
            // Advance time until complete
            while(expMission.getState() == MissionState.IN_PROGRESS) {
                expMission.progressTime();
            }
        }
        System.out.println();

        // ==========================================
        // SCENARIO 2: Mission Rejection (Bad Agent)
        // ==========================================
        System.out.println("=== SCENARIO 2: Mission Rejection ===");
        RepairMission repMission = new RepairMission("M-02", "Fix Solar Panel", 2);
        System.out.println("Attempting to assign Medical Officer to a Repair Mission...");
        
        // Medical officer cannot perform repair missions
        if (!medical1.canPerform(repMission)) {
            repMission.failMission("Agent does not possess the required capability.");
        }
        System.out.println();

        // ==========================================
        // SCENARIO 3: Resource Shortage
        // ==========================================
        System.out.println("=== SCENARIO 3: Resource Shortage ===");
        System.out.println("Attempting operation requiring 5000 spare parts...");
        if (!colony.consumeResource("spare", 5000)) {
            System.out.println("Operation Rejected: Insufficient resources to proceed.");
        }
        System.out.println();

        // ==========================================
        // SCENARIO 4: Emergency Handling (Oxygen Leak)
        // ==========================================
        System.out.println("=== SCENARIO 4: Emergency Handling ===");
        // Since you haven't fully written OxygenLeak yet, this simulates the logic you need
        System.out.println("Emergency Detected: Oxygen Leak at " + habitatA.getLocation());
        System.out.println("Initial Oxygen Lost. Remaining: " + habitatRes.getOxygen());
        
        // Identify responders
        if (engineer1.canRespondTo(new OxygenLeak()) && repairBot1.canRespondTo(new OxygenLeak())) {
            System.out.println("Dispatching Responders: " + engineer1.getName() + " & " + repairBot1.getName());
            
            // Fix the leak by consuming spare parts
            if (colony.consumeResource("spare", 2)) { 
                System.out.println("Leak repaired successfully. Habitat state updated.");
            }
        }
        System.out.println();

        // ==========================================
        // SCENARIO 5: Environmental Effect (Dust Storm)
        // ==========================================
        System.out.println("=== SCENARIO 5: Environmental Effect ===");
        DustStorm storm = new DustStorm();
        ExplorationMission stormMission = new ExplorationMission("M-03", "Surface Scan", 2);
        
        // Check if the storm blocks the mission
        EnvironmentCheck check = storm.canStart(stormMission); 
        if (!check.isAllowed()) { // Assuming your EnvironmentCheck class has an isAllowed() boolean
            stormMission.failMission(check.getReason());
        }
    }
}