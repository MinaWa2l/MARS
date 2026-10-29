
package javaapplication3;

public class CommsDisruption implements EnvironmentCondition{
    @Override
    public String getName(){
        return "Communication Disruption";
    }
    @Override
    public EnvironmentCheck canStart(Missions mission){
        return mission.requiresCommunication()?
            EnvironmentCheck.deny("Communication systems are distrupes, this mission requires communication.")
            :EnvironmentCheck.allow();
    }
    @Override
    public EnvironmentCheck canContinue(Missions mission){
     return mission.requiresCommunication()?
            EnvironmentCheck.deny("Communication was lost during a communication-dependent mission.")
            :EnvironmentCheck.allow();
    }
    @Override
    public EnvironmentCheck canControl(Agent agent)
    {return agent.requiresCommunicationLink()?
            EnvironmentCheck.deny(agent.getName() + "cannot be controlled during a communication disprution.")
            :EnvironmentCheck.allow();
    }
}
