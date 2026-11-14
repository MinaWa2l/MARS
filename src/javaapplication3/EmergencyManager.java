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
    public boolean respond(Emergency emergency,Colony colony){
        System.out.println(emergency.getDescription());
        Agent humanResponder = findResponder(colony.getCrewArray() , emergency);
        Agent robotResponder = findResponder(colony.getRobotArray(), emergency);
       
        
        
        if(emergency.isOxygenLeak()){
            OxygenLeak leak = (OxygenLeak) emergency;
            Habitat hab = leak.getAffectedHabitat();
            
            // 1. Initial penalty 
            hab.getResources().removeOxygen((int) leak.getOxygenLoss());
            
            if (humanResponder == null || robotResponder == null) {
                System.out.println("UNRESOLVED: Human and repair robot are required.");
                hab.getResources().removeOxygen((int) leak.getOxygenLoss()); // 2. Second penalty for failing
                return false;
            }
            
            // 3. Consume resources to fix
            if (colony.consumeResource("spare", 2)) {
                dispatch(humanResponder, emergency);
                dispatch(robotResponder, emergency);
                
                // Free agents after fixing
                humanResponder.setAvailable(true);
                robotResponder.setAvailable(true);
                return true;
            } else {
                System.out.println("UNRESOLVED: Insufficient spare parts.");
                hab.getResources().removeOxygen((int) leak.getOxygenLoss()); // Penalty for failing
                return false;
            }
        }
    if (emergency.isFire()) {

            if (humanResponder == null) {

                System.out.println(
                    "UNRESOLVED: No available fire responder."
                );

                return false;
            }

            dispatch(humanResponder, emergency);
            humanResponder.setAvailable(true);
            return true;
        }

    
    if (emergency.isRoverBreakdown()) {

            if (robotResponder == null) {

                System.out.println(
                    "UNRESOLVED: No available repair robot."
                );

                return false;
            }

            dispatch(robotResponder, emergency);
            robotResponder.setAvailable(true);
            return true;
    }


        // Medical Emergency needs a medical responder
        if (emergency.isMedicalEmergency()) {

            if (humanResponder == null) {

                System.out.println(
                    "UNRESOLVED: No available medical officer."
                );

                return false;
            }

            dispatch(humanResponder, emergency);
            humanResponder.setAvailable(true);
            return true;
        }

        return false;
    
    }
}