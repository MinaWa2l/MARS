
package javaapplication3;

public interface EnvironmentCondition {
        String getName();
        EnvironmentCheck canStart(Mission mission);
        EnvironmentCheck canContinue(Mission mission);
        EnvironmentCheck canControl(Agent agent);
        
}
