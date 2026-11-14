package javaapplication3;

/**
 *
 * @author HP
 */
public class EmergencyManager   {
    private Agent findResponder(Agent[] agents,Emergency emergency){
        if(agents == null){
            return null;
        }
        for(Agent agent : agents){
            if(agent != null && agent.isAvailable() && agent.canRespondTo(emergency)){
            return agent;
            }
        }
        return null;
    }
    private void dispatch(Agent agent, Emergency emergency){
        if (emergency.getLocation() instanceof Facility) {
            Facility fac = (Facility) emergency.getLocation();
            agent.setLocation(fac.getLocation());
        }
        agent.setAvailable(false);
        
        System.out.println(agent.getName()+" dispatched to emergency.");
    }
    public boolean respond(Emergency emergency, Colony colony) {
        if (emergency == null || colony == null) return false;

        if (emergency.isResolved() ||
            !emergency.getFailureReason().isEmpty()) {
            System.out.println("Emergency was already handled.");
            return false;
        }

        System.out.println(emergency.getDescription());

        Agent humanResponder =
            findResponder(colony.getCrewArray(), emergency);

        Agent robotResponder =
            findResponder(colony.getRobotArray(), emergency);

        // OXYGEN LEAK
        if (emergency.isOxygenLeak()) {
            OxygenLeak leak = (OxygenLeak) emergency;
            Habitat hab = leak.getAffectedHabitat();
            int loss = (int) leak.getOxygenLoss();

            // Initial oxygen loss: always occurs
            hab.getResources().removeOxygen(loss);

            if (!hab.isOperational() ||
                humanResponder == null || robotResponder == null) {

                hab.getResources().removeOxygen(loss);
                emergency.setFailureReason(
                    "Available engineer and repair robot required."
                );
                System.out.println("UNRESOLVED: " +
                    emergency.getFailureReason());
                return false;
            }

            if (colony.getTotalResource("spare") < 2 ||
                colony.getTotalResource("battery") < 5) {

                hab.getResources().removeOxygen(loss);
                emergency.setFailureReason("Insufficient repair resources.");
                System.out.println("UNRESOLVED: " +
                    emergency.getFailureReason());
                return false;
            }

            colony.consumeResource("spare", 2);
            colony.consumeResource("battery", 5);

            dispatch(humanResponder, emergency);
            dispatch(robotResponder, emergency);

            humanResponder.setAvailable(true);
            robotResponder.setAvailable(true);

            emergency.setResolved(true);
            System.out.println("Oxygen leak repaired.");
            return true;
        }

        // FIRE
        if (emergency.isFire()) {
            Fire fire = (Fire) emergency;
            Laboratory lab = fire.getAffectedLaboratory();

            // Fire damages equipment and disables the laboratory
            lab.setOperational(false);
            lab.equipement = false;

            if (humanResponder == null || robotResponder == null) {
                emergency.setFailureReason("Fire responders unavailable.");
                System.out.println("UNRESOLVED: " +
                    emergency.getFailureReason());
                return false;
            }

            if (colony.getTotalResource("spare") < 2 ||
                colony.getTotalResource("battery") < 5) {

                emergency.setFailureReason("Insufficient fire response resources.");
                System.out.println("UNRESOLVED: " +
                    emergency.getFailureReason());
                return false;
            }

            colony.consumeResource("spare", 2);
            colony.consumeResource("battery", 5);

            dispatch(humanResponder, emergency);
            dispatch(robotResponder, emergency);

            humanResponder.setAvailable(true);
            robotResponder.setAvailable(true);

            emergency.setResolved(true);
            System.out.println("Fire extinguished. Laboratory needs repair.");
            return true;
        }

        // ROVER BREAKDOWN
        if (emergency.isRoverBreakdown()) {
            RoverBreakdown breakdown = (RoverBreakdown) emergency;
            ExplorationRover rover = breakdown.getAffectedRover();

            // The rover is damaged at its current location
            rover.sethealthy(false);

            Missions active = colony.getActiveMission();

            if (active != null) {
                for (int i = 0; i < active.getAssignedAgentCount(); i++) {
                    if (active.getAssignedAgent(i) == rover) {
                        active.failMission("Rover breakdown.");
                        break;
                    }
                }
            }

            if (humanResponder == null || robotResponder == null) {
                emergency.setFailureReason("Repair responders unavailable.");
                System.out.println("UNRESOLVED: " +
                    emergency.getFailureReason());
                return false;
            }

            if (colony.getTotalResource("spare") < 2 ||
                colony.getTotalResource("battery") < 5) {

                emergency.setFailureReason("Insufficient repair resources.");
                System.out.println("UNRESOLVED: " +
                    emergency.getFailureReason());
                return false;
            }

            colony.consumeResource("spare", 2);
            colony.consumeResource("battery", 5);

            dispatch(humanResponder, emergency);
            dispatch(robotResponder, emergency);

            rover.sethealthy(true);

            humanResponder.setAvailable(true);
            robotResponder.setAvailable(true);

            emergency.setResolved(true);
            System.out.println("Rover repaired.");
            return true;
        }

        // MEDICAL EMERGENCY
        if (emergency.isMedicalEmergency()) {
            MedicalEmergency medical = (MedicalEmergency) emergency;

            if (!(emergency.getLocation() instanceof Facility) ||
                !((Facility) emergency.getLocation()).isOperational()) {

                emergency.setFailureReason("Medical facility unavailable.");
                System.out.println("UNRESOLVED: " +
                    emergency.getFailureReason());
                return false;
            }

            if (humanResponder == null) {
                emergency.setFailureReason("No available medical officer.");
                System.out.println("UNRESOLVED: " +
                    emergency.getFailureReason());
                return false;
            }

            if (colony.getTotalResource("medical") < 2 ||
                colony.getTotalResource("oxygen") < 5) {

                emergency.setFailureReason("Insufficient medical resources.");
                System.out.println("UNRESOLVED: " +
                    emergency.getFailureReason());
                return false;
            }

            colony.consumeResource("medical", 2);
            colony.consumeResource("oxygen", 5);

            dispatch(humanResponder, emergency);
            medical.getPatient().heal(20);

            humanResponder.setAvailable(true);
            emergency.setResolved(true);

            System.out.println("Patient treated successfully.");
            return true;
        }

        emergency.setFailureReason("Unknown emergency type.");
        return false;
    }
}