
package javaapplication3;

public interface EnvironmentCondition {
        String getName();
        EnvironmentCheck canStart(Missions mission);
        EnvironmentCheck canContinue(Missions mission);
        EnvironmentCheck canControl(Agent agent);
        
}
