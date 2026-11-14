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
        agent.setLocation(emergency.getLocation()); //setlocation ma3moula string m4 object esmo location w dah 3amel error
        agent.setAvaible(false);
        
        System.out.println(agent.getName()+" dispatched to emergency.");
    }
    public boolean respond(Emergency emergency,Agent[] humans,Agent[] robots){
        System.out.println(emergency.getDescription());
        Agent humanResponder = findResponder(humans , emergency);
        Agent robotResponder = findResponder(robots, emergency);
        if(emergency.isOxygenLeak()){
         if (humanResponder == null || robotResponder == null) {

                System.out.println(
                    "UNRESOLVED: Human and repair robot are required."
                );

                return false;
        }
        dispatch(humanResponder, emergency);
        dispatch(robotResponder, emergency);

        return true;
    }
    if (emergency.isFire()) {

            if (humanResponder == null) {

                System.out.println(
                    "UNRESOLVED: No available fire responder."
                );

                return false;
            }

            dispatch(humanResponder, emergency);
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
            return true;
        }

        return false;
    
    }
}