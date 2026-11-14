
package javaapplication3;

public class Main {
    public static void main(String[] args) {
        System.out.println("========== MARS-OPS SIMULATOR ==========");

        // 1. CREATE THE COLONY
        Colony colony = new Colony(5, 5, 5);

        // Resources are stored in facilities, not directly in the colony.
        Resources habitatResources = new Resources();
        habitatResources.addOxygen(700);
        habitatResources.addBattery(30);
        habitatResources.addSpare(4);
        habitatResources.addMedical(4);

        Resources storageResources = new Resources();
        storageResources.addOxygen(300);
        storageResources.addBattery(40);
        storageResources.addSpare(10);
        storageResources.addMedical(10);
        storageResources.addFuel(20);

        // Facilities
        Habitat habitat = new Habitat("Habitat A", 10, habitatResources, true);
        StorageFacility storage = new StorageFacility("Storage", 20, storageResources, true);
        Laboratory laboratory = new Laboratory("Laboratory", 5, new Resources(), true);
        CommunicationStation comms = new CommunicationStation("Communications", 5, new Resources());

        colony.addFacility(habitat);
        colony.addFacility(storage);
        colony.addFacility(laboratory);
        colony.addFacility(comms);

        // Crew members
        Scientist scientist = new Scientist("Alice", "S1", "Habitat A",
                new String[]{"Geology", "Research"});
        Engineer engineer = new Engineer("Bob", "E1", "Habitat A",
                new String[]{"Repair"});
        Medical doctor = new Medical("Charlie", "M1", "Habitat A",
                new String[]{"First Aid"});

        colony.addCrewMember(scientist);
        colony.addCrewMember(engineer);
        colony.addCrewMember(doctor);

        // Robots
        ExplorationRover rover = new ExplorationRover("Habitat A", "Rover-X", "R1");
        RepairRobot repairRobot = new RepairRobot("Habitat A", "Fix-It", "R2");
        Drone drone = new Drone("Habitat A", "Sky-1", "R3");

        colony.addRobot(rover);
        colony.addRobot(repairRobot);
        colony.addRobot(drone);

        System.out.println("\nInitial colony status:");
        colony.printStatus();

        // SCENARIO 1: SUCCESSFUL EXPLORATION
        System.out.println("\n========== SCENARIO 1: SUCCESSFUL EXPLORATION ==========");
        ExplorationMission exploration = new ExplorationMission("M-01", "Explore crater", 2);
        exploration.assignAgent(scientist);
        exploration.assignAgent(rover);

        colony.submitMission(exploration);
        System.out.println("After submission: " + exploration.getState());
        System.out.println("Scientist available: " + scientist.isAvailable());
        System.out.println("Rover available: " + rover.isAvailable());

        colony.advanceTime(1);
        System.out.println("After first time unit: " + exploration.getState());

        colony.advanceTime(1);
        System.out.println("After second time unit: " + exploration.getState());
        System.out.println("Scientist available again: " + scientist.isAvailable());
        System.out.println("Rover available again: " + rover.isAvailable());
        colony.printStatus();

        // SCENARIO 2: MISSION REJECTION (UNSUITABLE AGENT)
        System.out.println("\n========== SCENARIO 2: MISSION REJECTION ==========");
        RepairMission rejected = new RepairMission("M-02", "Repair solar panel", 2);
        rejected.assignAgent(doctor); // A medical officer cannot perform repair missions.
        colony.submitMission(rejected);
        System.out.println("Mission state: " + rejected.getState());
        System.out.println("Failure reason: " + rejected.getFailureReason());
        System.out.println("Doctor still available: " + doctor.isAvailable());
        System.out.println("Active mission: " +
                (colony.getActiveMission() == null ? "None" : colony.getActiveMission().getMissionId()));

        // SCENARIO 3: RESOURCE SHORTAGE
        // A separate small test colony avoids changing the main colony's supplies.
        System.out.println("\n========== SCENARIO 3: RESOURCE SHORTAGE ==========");
        Colony shortageColony = new Colony(1, 1, 1);
        Resources limitedResources = new Resources();
        limitedResources.addOxygen(100);
        limitedResources.addBattery(3); // Exploration needs 10 units.
        Habitat testHabitat = new Habitat("Test Habitat", 3, limitedResources, true);
        shortageColony.addFacility(testHabitat);

        Scientist testScientist = new Scientist("Test Scientist", "S2", "Test Habitat",
                new String[]{"Geology"});
        ExplorationRover testRover = new ExplorationRover("Test Habitat", "Test Rover", "R4");
        shortageColony.addCrewMember(testScientist);
        shortageColony.addRobot(testRover);

        ExplorationMission shortageMission = new ExplorationMission("M-03", "Explore without enough battery", 2);
        shortageMission.assignAgent(testScientist);
        shortageMission.assignAgent(testRover);

        System.out.println("Battery before submission: " + shortageColony.getTotalResource("battery"));
        shortageColony.submitMission(shortageMission);
        System.out.println("Mission state: " + shortageMission.getState());
        System.out.println("Failure reason: " + shortageMission.getFailureReason());
        System.out.println("Battery after rejection: " + shortageColony.getTotalResource("battery"));

        // SCENARIO 4: OXYGEN LEAK EMERGENCY
        System.out.println("\n========== SCENARIO 4: OXYGEN LEAK ==========");
        EmergencyManager emergencyManager = new EmergencyManager();
        OxygenLeak leak = new OxygenLeak(habitat, habitat, 100);

        System.out.println("Habitat oxygen before: " + habitatResources.getOxygen());
        System.out.println("Colony oxygen before: " + colony.getTotalResource("oxygen"));
        boolean repaired = emergencyManager.respond(leak, colony);
        System.out.println("Response successful: " + repaired);
        System.out.println("Emergency resolved: " + leak.isResolved());
        System.out.println("Habitat oxygen after: " + habitatResources.getOxygen());
        System.out.println("Colony oxygen after: " + colony.getTotalResource("oxygen"));
        System.out.println("Engineer available: " + engineer.isAvailable());
        System.out.println("Repair robot available: " + repairRobot.isAvailable());
        colony.printStatus();

        // SCENARIO 5: ENVIRONMENTAL EFFECT (DUST STORM)
        System.out.println("\n========== SCENARIO 5: DUST STORM ==========");
        colony.getEnvironment().setCondition(new DustStorm());
        ExplorationMission stormMission = new ExplorationMission("M-04", "Explore during dust storm", 2);
        stormMission.assignAgent(scientist);
        stormMission.assignAgent(rover);

        int batteryBefore = colony.getTotalResource("battery");
        colony.submitMission(stormMission);
        System.out.println("Mission state: " + stormMission.getState());
        System.out.println("Failure reason: " + stormMission.getFailureReason());
        System.out.println("Battery before: " + batteryBefore);
        System.out.println("Battery after: " + colony.getTotalResource("battery"));

        System.out.println("\n========== END OF SIMULATION ==========");
        colony.printStatus();
    }
}
